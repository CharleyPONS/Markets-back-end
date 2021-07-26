package com.info.markets.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilsMapping {
    private final ModelMapper modelMapper;

    @Autowired
    public UtilsMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, T> List<T> convertListEntityToDTO(@NotNull List<S> source, @NotNull Class<T> target) {
        return source.stream()
                .map(element -> this.modelMapper.map(element, target))
                .collect(Collectors.toList());
    }

    public <S, T> T  convertObjectEntityToDTO(@NotNull S source, @NotNull Class<T> target) {
        return this.modelMapper.map(source, target);
    }
}
