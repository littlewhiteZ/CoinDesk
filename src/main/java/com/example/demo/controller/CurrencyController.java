package com.example.demo.controller;

import com.example.demo.controller.dto.CurrencyMappingReqDto;
import com.example.demo.controller.dto.CurrencyMappingRspDto;
import com.example.demo.service.CurrencyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public List<CurrencyMappingRspDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CurrencyMappingRspDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CurrencyMappingRspDto create(@Valid @RequestBody CurrencyMappingReqDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CurrencyMappingRspDto update(@PathVariable Long id, @Valid @RequestBody CurrencyMappingReqDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
