package com.example.demo.service;

import com.example.demo.controller.dto.CurrencyMappingReqDto;
import com.example.demo.controller.dto.CurrencyMappingRspDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CurrencyService {

    List<CurrencyMappingRspDto> findAll();

    CurrencyMappingRspDto findById(Long id);

    Map<String, String> findNameMapByCodes(Set<String> codes);

    CurrencyMappingRspDto create(CurrencyMappingReqDto mapping);

    CurrencyMappingRspDto update(Long id, CurrencyMappingReqDto mapping);

    void delete(Long id);
}
