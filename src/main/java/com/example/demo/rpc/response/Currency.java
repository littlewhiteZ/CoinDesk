package com.example.demo.rpc.response;

import lombok.Data;

@Data
public class Currency {

    private String code;
    private String symbol;
    private String rate;
    private String description;
    private double rate_float;

    public String getCode() {
        return code;
    }

    public String getRate() {
        return rate;
    }
}
