package com.root.websocket.controller;

import com.root.dto.DownDTO;
import com.root.dto.ReturnMessage;
import com.root.dto.SysFileUploadDTO;
import com.root.sevice.SysFileService;
import com.root.sevice.SysWordFileService;
import com.root.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@RequestMapping("/SysFile")
public class SysFileController {
    @Autowired
    SysWordFileService sysWordFileService;
    @Autowired
    SysFileService sysImageFileServiceImpl;
    /**
     * 文件上传
     *
     * @param id
     * @return
     */
    @PostMapping("/WordUpload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public ReturnMessage Upload(@RequestParam MultipartFile file, String sys_files_vale) throws IOException {
        //SysWord
        return ResponseUtil.success( sysWordFileService.Save(file,sys_files_vale));
    }

    /**
     * 文件批量上传
     *
     *
     * @return
     */
    @GetMapping("/WordDown")
    @ApiOperation(value = "下载", notes = "文件批量下载")
    public void Uploads(HttpServletResponse httpServletResponse, DownDTO downDTO) throws IOException {
        sysWordFileService.Down(httpServletResponse,downDTO);
        return ;
    }

    /**
     *
     * @param file
     * @param sys_files_vale
     * @return
     * @throws IOException
     */
    @PostMapping("/ImageUpload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public ReturnMessage ImageUpload(@RequestBody SysFileUploadDTO sysFileUploadDTO) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(sysFileUploadDTO.getFile().split(",")[1]);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        MultipartFile file = new MockMultipartFile("file", sysFileUploadDTO.getFileName(), sysFileUploadDTO.getFileType(), imageBytes);
        return ResponseUtil.success(sysImageFileServiceImpl.Save(file,sysFileUploadDTO.getSys_files_vale()));
    }

    /**
     * 文件批量上传
     *
     * @param id
     * @return
     */
    @GetMapping("/ImageDown")
    @ApiOperation(value = "下载", notes = "文件批量下载")
    public void ImageDown(HttpServletResponse httpServletResponse, DownDTO downDTO) throws IOException {
        sysImageFileServiceImpl.Down(httpServletResponse,downDTO);
        return ;
    }
}
