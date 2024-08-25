package com.nongviet201.cinema.core.utils;

import com.nongviet201.cinema.core.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EnumService {

    public <E extends Enum<E>> List<E> getEnumListByNames(
        Function<String, E> valueOfFunction,
        List<String> names, String paramName
    ) {
        return names.stream()
            .map(name -> getEnumValueByName(valueOfFunction, name, paramName))
            .collect(Collectors.toList());
    }

    public <E extends Enum<E>> E getEnumValueByName(
        Function<String, E> valueOfFunction,
        String name,
        String paramName
    ) {
        try {
            return valueOfFunction.apply(name);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Có lỗi xảy ra với tham số '" + paramName + "': " + name);
        }
    }
}
