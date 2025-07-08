package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class CurrencyMappingRspDto {

    private Long id;

    private String code;

    private String nameZh;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNameZh() { return nameZh; }
    public void setNameZh(String nameZh) { this.nameZh = nameZh; }
}
