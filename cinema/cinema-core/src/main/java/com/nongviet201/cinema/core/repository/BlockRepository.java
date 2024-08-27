package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.cinema.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {
    List<Block> findByAuditorium_IdOrderByPositionsAsc(int audId);
}
