package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiangxl
 * @description 文件处理服务
 * @date 2018-06-05 15:11
 **/
public interface IFileService {
    public String upload(MultipartFile file, String path);
}
