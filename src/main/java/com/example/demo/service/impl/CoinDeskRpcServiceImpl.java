package com.example.demo.service.impl;

import com.example.demo.rpc.response.CoinDeskRpcRsp;
import com.example.demo.rpc.CoinDeskClient;
import com.example.demo.service.CoinDeskRpcService;
import org.springframework.stereotype.Service;

@Service
public class CoinDeskRpcServiceImpl implements CoinDeskRpcService {

    private final CoinDeskClient coinDeskClient;

    public CoinDeskRpcServiceImpl(CoinDeskClient coinDeskClient) {
        this.coinDeskClient = coinDeskClient;
    }

    @Override
    public CoinDeskRpcRsp getCoinDeskData() {
        return coinDeskClient.getCoinDeskData();
    }
}
