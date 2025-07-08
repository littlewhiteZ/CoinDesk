package com.example.demo.rpc;

import com.example.demo.rpc.response.CoinDeskRpcRsp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "coindeskClient", url = "https://kengp3.github.io")
public interface CoinDeskClient {

    @GetMapping("/blog/coindesk.json")
    CoinDeskRpcRsp getCoinDeskData();
}
