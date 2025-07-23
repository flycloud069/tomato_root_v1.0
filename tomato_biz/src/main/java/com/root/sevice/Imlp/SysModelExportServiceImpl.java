package com.root.sevice.Imlp;

import cn.hutool.json.JSONObject;
import com.root.dto.SysServiceDto;
import com.root.sevice.SysFilesService;
import com.root.sevice.SysModelExportService;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SysModelExportServiceImpl implements SysModelExportService {
    @Autowired
    SysFilesService sysFilesService;

    public void wordModelExport(HttpServletResponse httpServletResponse, String fileName, String sys_files_value, JSONObject jsonObject) {
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sysFilesService.getPath(sys_files_value) + "\\" + fileName));
            //处理T1的单列属性文本替换。
            JSONObject jsonObjectT1 = jsonObject.getJSONObject("T1");
            for (String key : jsonObjectT1.keySet()) {
                getParagraphsReplace(document.getParagraphs(), key, jsonObjectT1.getStr(key));
                // 遍历文档中的所有表格
                for (XWPFTable table : document.getTables()) {
                    System.out.println("Table found");
                    for (XWPFTableRow row : table.getRows()) {
                        rowRepact(row,key,jsonObjectT1.getStr(key));
                    }
                }
            }
                //T2以下的列表类型的表格或者列表的数据替换
                jsonObject.keySet().stream().skip(1).forEach(listKey -> {
                    for (XWPFTable table : document.getTables()) {
                        for(int i=0;i<table.getRows().size();i++){
                            XWPFTableRow row = table.getRow(i);
                            if (row.getCell(0).getText().contains("#{"+listKey+".")){
                                jsonObject.getJSONArray(listKey).stream().skip(1).forEach(listValue -> {
                                    XWPFTableRow newRow=cloneRowWithoutCTTr(table,row);
                                    for (String key:((JSONObject)listValue).keySet()) {
                                        rowRepact(newRow,listKey+"."+key,((JSONObject)listValue).getStr(key));
                                    }
                                });
                                JSONObject jsonObjectTn = jsonObject.getJSONArray(listKey).getJSONObject(0);
                                for (String key:jsonObjectTn.keySet()) {
                                    rowRepact(row, listKey+"."+key, jsonObjectTn.getStr(key));
                                }
                                break;
                            }
                        }
                    }
                    for(Integer i=0;i<document.getParagraphs().size();i++){
                    XWPFParagraph paragraph = document.getParagraphs().get(i);
                        if (paragraph.getText().contains("#{"+listKey+".")){
                            AtomicInteger index = new AtomicInteger(i);

                            jsonObject.getJSONArray(listKey).stream().limit(jsonObject.getJSONArray(listKey).size()-1).forEach(listValue -> {
                                XWPFParagraph paragraphNew = document.insertNewParagraph(paragraph.getCTP().newCursor());
                                cloneParagraph(paragraph, paragraphNew);
                                JSONObject jsonObjectTn = jsonObject.getJSONArray(listKey).getJSONObject(0);
                                for (String key:jsonObjectTn.keySet()) {
                                    getParagraphReplace(paragraphNew, listKey+"."+key, ((JSONObject)listValue).getStr(key));
                                }
                            });
                            JSONObject jsonObjectTn = jsonObject.getJSONArray(listKey).getJSONObject(jsonObject.getJSONArray(listKey).size()-1);
                            for (String key:jsonObjectTn.keySet()) {
                                getParagraphReplace(paragraph, listKey+"."+key, jsonObjectTn.getStr(key));
                            }
                            break;
                        }
                    }
                });



            // 遍历文档中的所有段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                // 遍历段落中的所有运行（Runs）
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0); // 获取运行中的文本
                    System.out.println(text);
                }
            }

            // 遍历文档中的所有表格
            for (XWPFTable table : document.getTables()) {
                System.out.println("Table found");
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        // 获取单元格中的文本内容
                        String text = cell.getText();
                        System.out.println(text);
                    }
                }
            }
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" +
            URLEncoder.encode( fileName,"UTF-8") );
            httpServletResponse.setContentType(new MimetypesFileTypeMap().getContentType(fileName));
            document.write(httpServletResponse.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("调用word模版导出功能");
    }
    private  void rowRepact(XWPFTableRow row,String pramaKey,String newStr  ){
        for (XWPFTableCell cell : row.getTableCells()) {
            if (cell != null) {
                getParagraphsReplace(cell.getParagraphs(), pramaKey, newStr);
            }
        }
    }
    public void pptModelExport(HttpServletResponse httpServletResponse, String fileName, String sys_files_value, JSONObject jsonObject){
        return;
    }

    /**
     * 不使用CTTr类的表格行克隆方法
     */
    private static XWPFTableRow  cloneRowWithoutCTTr(XWPFTable table, XWPFTableRow sourceRow) {
        // 创建新行
        XWPFTableRow newRow = table.createRow();

        for (int i = 0; i < newRow.getTableCells().size(); i++) {
            cloneParagraph(sourceRow.getCell(i).getParagraphs().get(0), newRow.getCell(i).getParagraphs().get(0));
        }
        return newRow;
    }
    private static void cloneParagraph(XWPFParagraph source, XWPFParagraph target) {
        cloneParagraphProperties(source, target); // 克隆段落属性
         XWPFRun targetRun = target.createRun();
        cloneRunProperties(source.getRuns().get(0),targetRun); // 克隆段落属性
    }
    private static void getParagraphsReplace(List<XWPFParagraph>  paragraphs, String oldStrParamKey, String newStr) {
        for (XWPFParagraph paragraph : paragraphs) {
            getParagraphReplace(paragraph, oldStrParamKey, newStr);
        }
    }

        private static void getParagraphReplace( XWPFParagraph paragraph, String oldStrParamKey, String newStr) {
        // 遍历段落中的所有运行（Runs）
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0); // 获取运行中的文本
            if (text != null) {
                String replacedText = text.replace("#{" + oldStrParamKey + "}", newStr); // 替换文本
                run.setText(replacedText, 0); // 设置新的文本
            }
        }
    }
    /**
     * 克隆一个 XWPFParagraph 的所有属性到目标段落
     * @param source 源段落
     * @param target 目标段落
     */
    public static void cloneParagraphProperties(XWPFParagraph source, XWPFParagraph target) {
        if (source == null || target == null) return;

        System.out.println(source.getNumFmt());;
        target.setNumID(source.getNumID());
        target.setNumILvl(source.getNumIlvl());
        // 2. 克隆段落样式
        target.setStyle(source.getStyleID());

        // 3. 克隆段落对齐方式
        target.setAlignment(source.getAlignment());
        target.setVerticalAlignment(source.getVerticalAlignment());

//        // 4. 克隆缩进设置
        target.setIndentationFirstLine(source.getIndentationFirstLine());
        target.setIndentationLeft(source.getIndentationLeft());
        target.setIndentationRight(source.getIndentationRight());

        // 5. 克隆间距设置
        target.setSpacingAfter(source.getSpacingAfter());
        target.setSpacingAfterLines(source.getSpacingAfterLines());
        target.setSpacingBefore(source.getSpacingBefore());
        target.setSpacingBeforeLines(source.getSpacingBeforeLines());


        // 9. 克隆边框和底纹（如果需要）
        // 注意：边框和底纹的复制较复杂，需单独处理每个边框属性
    }
    /**
     * 克隆 XWPFRun 的所有属性
     */
    private static void cloneRunProperties(XWPFRun source, XWPFRun target) {
        if (source == null || target == null) return;

        // 克隆文本内容
        target.setText(source.getText(0));

        // 克隆字体属性
        target.setFontFamily(source.getFontFamily());
//        target.setFontSize(source.getFontSize());
        target.setBold(source.isBold());
        target.setItalic(source.isItalic());
        target.setUnderline(source.getUnderline());
        target.setColor(source.getColor());
        target.setStrike(source.isStrike());
        target.setSubscript(source.getSubscript());

        // 克隆其他属性
        target.setCharacterSpacing(source.getCharacterSpacing());
        target.setKerning(source.getKerning());
        target.setEmbossed(source.isEmbossed());
        target.setImprinted(source.isImprinted());

    }




    public static void main(String[] args) {
        try {
            // 加载Word文档
            String filePath = "工作总结_付云翔_2025.07.04.docx";
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fis);
            // 遍历文档中的所有表格
            for (XWPFTable table : document.getTables()) {
                System.out.println("Table found");
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        // 获取单元格中的文本内容
                        String text = cell.getText();
                        System.out.println(text);
                    }
                }
            }
            List<XWPFTable> tables = document.getTables();
            XWPFTable table = tables.get(0); // 获取第一个表格
            XWPFTableRow row = table.createRow(); // 创建新行
            row.setHeight(300);
            // 创建单元格并设置内容
            for (int i = 0; i < table.getRow(0).getTableCells().size(); i++) { // 假设与第一行的单元格数相同
                XWPFTableCell cell = row.getCell(i); // 创建单元格
                cell.setText("New Cell Content"); // 设置单元格内容
            }
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out); // 写入修改后的文档
            }
            fis.close();
            System.out.println("加载ppt模板");
            // 加载Word文档
            String filePptPath = "周报模板.pptx";
            FileInputStream fisppt = new FileInputStream(filePptPath);
            XMLSlideShow ppt = new XMLSlideShow(fisppt);
            // 遍历所有幻灯片
            for (XSLFSlide slide : ppt.getSlides()) {
                System.out.println("--- Slide: " + slide.getSlideNumber() + " ---");
                // 遍历幻灯片中的形状（文本框、图片等）
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        // 提取文本及样式
                        for (XSLFTextParagraph para : textShape.getTextParagraphs()) {
                            for (XSLFTextRun run : para.getTextRuns()) {
                                System.out.println("Text: " + run.getRawText());
                                System.out.println("Font: " + run.getFontFamily() + ", Size: " + run.getFontSize());
                                System.out.println("Color: " + run.getFontColor());
                            }
                        }
                    } else if (shape instanceof XSLFPictureShape) {
                        XSLFPictureShape pic = (XSLFPictureShape) shape;
                        // 提取图片信息
                        System.out.println("Image: " + pic.getPictureData().getFileName());
                        System.out.println("Size: " + pic.getAnchor().getWidth() + "x" + pic.getAnchor().getHeight());
                        // 保存图片：pic.getPictureData().getData() 获取字节数组
                    }
                }
            }

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
