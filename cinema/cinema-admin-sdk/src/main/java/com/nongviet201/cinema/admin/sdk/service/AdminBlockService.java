package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertBlockRequest;
import com.nongviet201.cinema.core.entity.cinema.Block;
import com.nongviet201.cinema.core.service.AuditoriumService;
import com.nongviet201.cinema.core.service.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminBlockService {

    private final BlockService blockService;
    private final AuditoriumService auditoriumService;

    public List<Block> getAllBlockByAuditoriumId(
        int audId
    ) {
        return blockService.getAllBlockByAuditoriumIdOrderByPosition(audId);
    }

    public Block findById(
        int id
    ) {
        return blockService.getBlockById(id);
    }

    public void deleteBlockByListId(
        List<Integer> ids
    ) {
        ids.forEach(this::deleteById);
    }

    private void deleteById(
        Integer id
    ) {
        blockService.deleteById(id);
    }

    public void createBlock(
        List<UpsertBlockRequest> request
    ) {
        request.forEach(e -> {
            blockService.save(
                Block.builder()
                    .auditorium(auditoriumService.getAuditoriumById(e.getAudId()))
                    .seatRow(e.getSeatRow())
                    .startColumn(e.getStartColumn())
                    .endColumn(e.getEndColumn())
                    .positions(e.getPositions())
                    .build()
            );
        });
    }

    public void updateBlock(
        List<UpsertBlockRequest> request
    ) {
        request.forEach(e -> {
           Block block = findById(e.getBlockId());
           block.setAuditorium(auditoriumService.getAuditoriumById(e.getAudId()));
           block.setSeatRow(e.getSeatRow());
           block.setStartColumn(e.getStartColumn());
           block.setEndColumn(e.getEndColumn());
           block.setPositions(e.getPositions());
           blockService.save(block);
        });
    }
}
