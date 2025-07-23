package com.root.util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public class ExcelExporterUtil {

    public static void exportToExcel(HttpServletResponse response, List<String> headers, List<List<String>> data) throws IOException {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建工作表sheet
        Sheet sheet = workbook.createSheet("Example Sheet");

        // 创建行（Row）和单元格（Cell）
        Row row = sheet.createRow(0); // 创建第一行
        Cell cell = row.createCell(0); // 在第一行创建第一个单元格
        cell.setCellValue("Hello, World!"); // 设置单元格的值

        // 调整列宽以适应内容
        sheet.autoSizeColumn(0);
//        // 写入文件到磁盘
//        try (FileOutputStream outputStream = new FileOutputStream("example.xlsx")) {
//            workbook.write(outputStream);
//            workbook.close(); // 关闭工作簿以释放资源
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 响应到浏览器，保存文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=example.xlsx");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            workbook.close(); // 关闭workbook很重要，否则文件会损坏
        }

    }
    public static void main(String[] args) {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建工作表sheet
        Sheet sheet = workbook.createSheet("Example Sheet");

        // 创建行（Row）和单元格（Cell）
        Row row = sheet.createRow(0); // 创建第一行
        Cell cell = row.createCell(0); // 在第一行创建第一个单元格
        cell.setCellValue("Hello, World!"); // 设置单元格的值

        // 调整列宽以适应内容
        sheet.autoSizeColumn(0);

        // 写入文件到磁盘
        try (FileOutputStream outputStream = new FileOutputStream("example.xlsx")) {
            workbook.write(outputStream);
            workbook.close(); // 关闭工作簿以释放资源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
