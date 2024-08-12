package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Combo;
import com.nongviet201.cinema.core.repository.ComboRepository;
import com.nongviet201.cinema.core.service.ComboService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComboServiceImpl implements ComboService {
    private final ComboRepository comboRepository;
    @Override
    public List<Combo> getAllCombo() {
        return comboRepository.findAll();
    }
}
