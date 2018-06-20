package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-05 15:12
 **/
@Service("iFileService")
@Slf4j
public class FileServiceImpl implements IFileService {

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + extensionName;
        log.info("开始上传文件，上传你文件的文件名:{},上传的路径：{}，新文件名：{}", fileName, path, uploadFileName);
        File fileDir= new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile =new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            // 文件上传ftp
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //todo 删除upload文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
        }
        return targetFile.getName();
    }
}
