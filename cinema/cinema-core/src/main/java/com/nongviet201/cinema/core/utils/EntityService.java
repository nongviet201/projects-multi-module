package com.nongviet201.cinema.core.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntityService {

    public <T> List<T> getEntitiesByIds(
        Function<Integer, T> serviceMethod,
        List<Integer> ids
    ) {
        return ids.stream()
            .map(serviceMethod)
            .collect(Collectors.toList());
    }
}
