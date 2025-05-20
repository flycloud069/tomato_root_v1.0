package com.root.sevice.Imlp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.root.dto.DownDTO;
import com.root.dto.SysBaseServiceDTO;
import com.root.entity.outside.SysFileWordModel;
import com.root.entity.outside.SysFilesModel;
import com.root.entity.outside.sys_file_imageModel;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysFileService;
import com.root.sevice.SysFilesService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

import static com.root.common.SysfileCommon.creatMkdir;
import static javax.swing.UIManager.getDimension;
@Service
public class SysImageFileServiceImpl implements SysFileService {
    @Autowired
    SysFilesService sysFilesService;
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    public String Save(MultipartFile multipartFile, String sys_files_value){
        String path="";
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());

            if (image != null) {//如果image=null 表示上传的不是图片格式

                int width = image.getWidth();//获取图片宽度，单位px

                int height = image.getHeight();//获取图片高度，单位px

                path = sysFilesService.getPath(sys_files_value) + "\\" + multipartFile.getOriginalFilename();
                File file = new File(path);
                creatMkdir(file);
                if (file.exists()) {
                    file.delete();
                }
                multipartFile.transferTo(file);

                sys_file_imageModel sysFileWordModel = new sys_file_imageModel(sysFilesService.getid(sys_files_value), multipartFile.getOriginalFilename(), UUID.randomUUID().toString(), Convert.toStr(multipartFile.getSize()), Convert.toStr(width), Convert.toStr(height));
                sysBaseServiceService.getBaseServiceList(SysFilesModel.class, new SysBaseServiceDTO("DelSysImageFile", BeanUtil.beanToMap(sysFileWordModel)));
                sysBaseServiceService.getBaseServiceList(SysFilesModel.class, new SysBaseServiceDTO("insertSysImageFile", BeanUtil.beanToMap(sysFileWordModel)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return path;
    };
    public void Down(HttpServletResponse httpServletResponse, DownDTO downDTO) {
        try {
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode( downDTO.getFileName(),"UTF-8") );
            httpServletResponse.setContentType(new MimetypesFileTypeMap().getContentType(downDTO.getFileName()));

            BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(sysFilesService.getPath(downDTO.getSys_files_value()) + "\\" + downDTO.getFileName())));
            String formatName = downDTO.getFileName().contains("png")? "png":"jpg";
            // 将BufferedImage对象输出为PNG格式的图片
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(image, formatName, outputStream);
                httpServletResponse.getOutputStream().write(outputStream.toByteArray());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    };


}
