package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Block;

import java.util.List;

public interface BlockService {
    List<Block> getAllBlockByAuditoriumIdOrderByPosition(int auditoriumId);

    Block getBlockById(int id);

    void save(Block block);

    void deleteById(int blockId);
}
