package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByStatusOrderByCreatedAt(boolean status);
}
