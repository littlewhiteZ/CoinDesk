package com.example.demo.converter;

import com.example.demo.controller.dto.CurrencyMappingReqDto;
import com.example.demo.controller.dto.CurrencyMappingRspDto;
import com.example.demo.entity.CurrencyMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyServiceConverter {

    CurrencyMapping toEntity(CurrencyMappingReqDto dto);

    @Mapping(target = "id", expression = "java(entity.getId())")
    @Mapping(target = "code", expression = "java(entity.getCode())")
    @Mapping(target = "nameZh", expression = "java(entity.getNameZh())")
    CurrencyMappingRspDto toRspDto(CurrencyMapping entity);
}
