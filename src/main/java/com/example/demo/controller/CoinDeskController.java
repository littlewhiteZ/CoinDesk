package com.example.demo.controller;

import com.example.demo.controller.response.ConvertedResponse;
import com.example.demo.controller.response.CurrencyDto;
import com.example.demo.converter.CoinDeskControllerConverter;
import com.example.demo.rpc.response.CoinDeskRpcRsp;
import com.example.demo.rpc.response.Currency;
import com.example.demo.service.CoinDeskRpcService;
import com.example.demo.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coindesk")
public class CoinDeskController {

    private final CoinDeskRpcService coinDeskRpcService;

    private final CurrencyService currencyService;

    private final CoinDeskControllerConverter converter;

    public CoinDeskController(CoinDeskRpcService coinDeskRpcService, CurrencyService currencyService, CoinDeskControllerConverter converter) {
        this.coinDeskRpcService = coinDeskRpcService;
        this.currencyService = currencyService;
        this.converter = converter;
    }

    @GetMapping("raw")
    public CoinDeskRpcRsp getRaw() {
        return coinDeskRpcService.getCoinDeskData();
    }

    @GetMapping("converted")
    public ConvertedResponse getConverted() {
        CoinDeskRpcRsp raw = coinDeskRpcService.getCoinDeskData();
        ConvertedResponse response = converter.toConvertedResponse(raw);
        Set<String> codes = raw.getBpi().values().stream().map(Currency::getCode).collect(Collectors.toSet());
        Map<String, String> nameMap = currencyService.findNameMapByCodes(codes);

        List<CurrencyDto> list = raw.getBpi().values().stream()
            .map(currency -> {
                CurrencyDto dto = new CurrencyDto();
                dto.setCode(currency.getCode());
                dto.setRate(currency.getRate());

                String name = nameMap.getOrDefault(currency.getCode(), "未知幣別");
                dto.setName(name);

                return dto;
            })
            .collect(Collectors.toList());

        response.setCurrencies(list);
        return response;
    }
}
