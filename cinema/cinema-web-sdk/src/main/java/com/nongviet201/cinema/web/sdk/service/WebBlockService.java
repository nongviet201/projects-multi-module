package com.nongviet201.cinema.web.sdk.service;

import com.nongviet201.cinema.core.entity.cinema.Block;
import com.nongviet201.cinema.core.service.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WebBlockService {

    private final BlockService blockService;

    public List<Block> getBlocksByAuditoriumId(
            Integer auditoriumId
    ) {
        return blockService.getAllBlockByAuditoriumIdOrderByPosition(auditoriumId);
    }
}
