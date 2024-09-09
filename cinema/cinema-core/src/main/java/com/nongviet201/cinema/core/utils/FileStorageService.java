package com.nongviet201.cinema.core.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FileStorageService {

    private final Path fileStorageLocation = Path.of("cinema-core/src/main/resources/static/media");

    public String storeFile(MultipartFile file) {
        if (!isImageFile(file)) {
            throw new RuntimeException("File không phải là hình ảnh");
        }

        // Chuẩn hóa tên file
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Kiểm tra tên file có hợp lệ hay không
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/api/v1/files/media/" + fileName;

        } catch (IOException ex) {
            throw new RuntimeException("Không thể tải lên file" + fileName + ". Vui lòng thử lại sau", ex);
        }
    }

    public Path loadFileAsResource(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

    public static boolean isImageFile(MultipartFile file) {
        String fileName = file.getOriginalFilename().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
            fileName.endsWith(".png") || fileName.endsWith(".gif") ||
            fileName.endsWith(".bmp") || fileName.endsWith(".tiff");
    }
}
