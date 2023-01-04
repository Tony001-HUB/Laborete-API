package com.laborete.LaboreteAPI.shared.common;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static String getFileExtension(MultipartFile file) {
        String fileType = file.getContentType();
        if (fileType != null && !fileType.isEmpty()) {
            return "." + fileType.substring(fileType.indexOf("/") + 1);
        } else {
            return ".png";
        }
    }
}
