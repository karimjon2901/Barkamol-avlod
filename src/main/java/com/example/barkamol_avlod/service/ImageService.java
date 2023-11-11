package com.example.barkamol_avlod.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
@Component
public class ImageService {

    public String filePath(String folder, String ext) {
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        return file.getPath() + "\\" + uuid + ext;
    }

    public String saveFile(MultipartFile image) {
        String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        try {
            String filePath;
            Files.copy(image.getInputStream(), Path.of(filePath = filePath("src/main/resources/images", ext)));
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }
}