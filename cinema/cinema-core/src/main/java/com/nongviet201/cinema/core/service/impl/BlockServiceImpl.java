package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.cinema.Block;
import com.nongviet201.cinema.core.repository.BlockRepository;
import com.nongviet201.cinema.core.service.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;

    @Override
    public List<Block> getAllBlockByAuditoriumIdOrderByPosition(
        int auditoriumId
    ) {
        return blockRepository.findByAuditorium_IdOrderByPositionsAsc(auditoriumId);
    }

    @Override
    public Block getBlockById(int id) {
        return blockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy khối với id: "+id));
    }

    @Override
    public void save(Block block) {
        blockRepository.save(block);
    }

    @Override
    public void deleteById(
        int blockId
    ) {
        blockRepository.deleteById(blockId);
    }
}
