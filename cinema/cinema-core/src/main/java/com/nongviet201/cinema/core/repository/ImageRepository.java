package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.media.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
