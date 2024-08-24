package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.media.Image;
import com.nongviet201.cinema.core.repository.ImageRepository;
import com.nongviet201.cinema.core.service.ImageService;
import com.nongviet201.cinema.core.utils.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileStorageService fileStorageService;
    private final ImageRepository imageRepository;

    @Override
    public String uploadImage(
        MultipartFile file
    ) {

        Image image = Image.builder()
            .path(fileStorageService.storeFile(file))
            .build();
        imageRepository.save(image);

        return image.getPath();
    }

}
