package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.root.common.SysfileCommon;
import com.root.dto.DownDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysFileWordModel;
import com.root.entity.outside.SysFilesModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysFileService;
import com.root.sevice.SysFilesService;
import com.root.sevice.SysWordFileService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;
//提供word文件类的文件操作
@Service
public class SysWordFileServiceImpl implements SysWordFileService, SysFileService {
    @Autowired
    SysFilesService sysFilesService;
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public String Save(MultipartFile multipartFile, String sys_files_value) {
        String path="";
        try {
            XWPFDocument document = new XWPFDocument(multipartFile.getInputStream());
            path = sysFilesService.getPath(sys_files_value) + "\\" + multipartFile.getOriginalFilename();
            File file = new File(path);
            SysfileCommon.creatMkdir(file);
            if (file.exists()) {
                file.delete();
            }
            multipartFile.transferTo(file);
            SysFileWordModel sysFileWordModel = new SysFileWordModel(sysFilesService.getid(sys_files_value), multipartFile.getOriginalFilename(), UUID.randomUUID().toString(), Convert.toStr(multipartFile.getSize()), Convert.toStr(document.getProperties().getExtendedProperties().getPages()));
            sysBaseServiceService.getBaseServiceList(SysFilesModel.class, new SysBaseServiceDTO("DelSysWordFile", BeanUtil.beanToMap(sysFileWordModel)));

            sysBaseServiceService.getBaseServiceList(SysFilesModel.class, new SysBaseServiceDTO("insertSysWordFile", BeanUtil.beanToMap(sysFileWordModel)));

        } catch (Exception e) {
            System.out.println(e);
        }
        return path;
    }



    public void Down(HttpServletResponse httpServletResponse,  DownDTO downDTO) {
        try {
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode( downDTO.getFileName(),"UTF-8") );
            httpServletResponse.setContentType(new MimetypesFileTypeMap().getContentType(downDTO.getFileName()));

            XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(sysFilesService.getPath(downDTO.getSys_files_value()) + "\\" + downDTO.getFileName()));
            xwpfDocument.write(httpServletResponse.getOutputStream());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
