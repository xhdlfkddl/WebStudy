package com.example.demo.service.implementation;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

@Service
public class FileServiceImplements implements FileService {
    
    @Value("${file.path}")
    private String FILE_PATH;
    @Value("${file.url}")
    private String FILE_URL;

    // 파일 업로드
    public String upload(MultipartFile file) {

        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String uuid = UUID.randomUUID().toString();

        String saveName = uuid + extension;
        String savePath = FILE_PATH + saveName;

        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = FILE_URL + saveName;
        return url;

    }

    // 파일 다운로드 부문은 아직 쓰지 않을 것.
    public Resource getFile(String fileName) {

        Resource resource = null;

        try {
            resource = new UrlResource("file:" + FILE_PATH + fileName);
        } catch(Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;
    }

}
