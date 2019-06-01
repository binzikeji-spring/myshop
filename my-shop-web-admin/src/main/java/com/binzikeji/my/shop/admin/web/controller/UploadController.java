package com.binzikeji.my.shop.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 图片上传控制器
 * @author: Chundekepa
 * @create: 2019-03-11 20:48
 **/
@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * 文件上传
     * @param dropzFile
     * @param editorFiles
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile[] editorFiles, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        // Dropzone 上传
        if (dropzFile != null){
            result.put("fileName" , writeFile(dropzFile, request));
        }
        // wangEditor 上传
        if (editorFiles != null && editorFiles.length > 0){
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile editorFile : editorFiles) {
                fileNames.add(writeFile(editorFile, request));
            }
            result.put("errno", 0);
            result.put("data", fileNames);
        }
        return result;
    }

    /**
     * 将图片写入指定的目录
     * @param multipartFile
     * @param request
     * @return
     */
    public String writeFile(MultipartFile multipartFile, HttpServletRequest request){
        // 获取文件后缀
        String fileNmae = multipartFile.getOriginalFilename();
        String fileSuffix = fileNmae.substring(fileNmae.lastIndexOf("."));
        // 文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        // 判断路径是否存在，不存在则创建文件夹
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        // 将文件写入目标
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            // 写入文件
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回文件完整路径
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return serverPath + UPLOAD_PATH + file.getName();
    }

}
