package com.example.demo.rpc.response;

import lombok.Data;

import java.util.Map;

@Data
public class CoinDeskRpcRsp {

    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Currency> bpi;
}