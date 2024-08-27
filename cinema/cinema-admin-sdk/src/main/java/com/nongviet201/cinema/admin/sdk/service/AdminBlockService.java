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

    public void deleteBlockById(
        int blockId
    ) {
        blockService.deleteById(blockId);
    }

    public void createBlock(
        List<UpsertBlockRequest> request
    ) {
        request.forEach(e -> {
            blockService.saveBlock(
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
}
