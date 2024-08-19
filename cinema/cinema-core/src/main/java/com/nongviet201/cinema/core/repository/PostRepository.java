package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByStatusOrderByCreatedAt(boolean status);

    Page<Post> findByTypeAndStatusOrderByPublishedAt(PostType type, Boolean status, Pageable pageable);
}
