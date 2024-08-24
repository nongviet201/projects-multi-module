package com.nongviet201.cinema.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage (MultipartFile file);
}
