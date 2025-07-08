package com.example.demo.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CurrencyMappingReqDto {

    @NotBlank(message = "幣別代碼不可為空")
    @Size(max = 10, message = "幣別代碼長度不可超過10字元")
    private String code;

    @NotBlank(message = "中文名稱不可為空")
    private String nameZh;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNameZh() { return nameZh; }
    public void setNameZh(String nameZh) { this.nameZh = nameZh; }
}
