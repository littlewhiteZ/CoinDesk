package com.example.demo.service.impl;

import com.example.demo.controller.dto.CurrencyMappingReqDto;
import com.example.demo.controller.dto.CurrencyMappingRspDto;
import com.example.demo.converter.CurrencyServiceConverter;
import com.example.demo.entity.CurrencyMapping;
import com.example.demo.repository.CurrencyMappingRepository;
import com.example.demo.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyMappingRepository repository;

    private final CurrencyServiceConverter converter;

    public CurrencyServiceImpl(CurrencyMappingRepository repository, CurrencyServiceConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<CurrencyMappingRspDto> findAll() {
        return repository.findAll().stream()
            .map(converter::toRspDto)
            .collect(Collectors.toList());
    }

    @Override
    public CurrencyMappingRspDto findById(Long id) {
        return repository.findById(id).map(converter::toRspDto)
            .orElseThrow(() -> new RuntimeException("找不到資料"));
    }

    @Override
    public Map<String, String> findNameMapByCodes(Set<String> codes) {
        List<CurrencyMapping> currencyMappings =  repository.findByCodes(codes);
        return currencyMappings.stream()
            .collect(Collectors.toMap(CurrencyMapping::getCode, CurrencyMapping::getNameZh));
    }

    @Override
    public CurrencyMappingRspDto create(CurrencyMappingReqDto dto) {
        CurrencyMapping entity = converter.toEntity(dto);
        return converter.toRspDto(repository.save(entity));
    }

    @Override
    public CurrencyMappingRspDto update(Long id, CurrencyMappingReqDto dto) {
        CurrencyMapping existing = repository.findById(id).orElseThrow(() -> new RuntimeException("找不到資料"));
        existing.setCode(dto.getCode());
        existing.setNameZh(dto.getNameZh());
        return converter.toRspDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Currency not found: id = " + id);
        }
        repository.deleteById(id);
    }
}
