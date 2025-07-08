package com.example.demo.converter;

import com.example.demo.controller.response.ConvertedResponse;
import com.example.demo.controller.response.CurrencyDto;
import com.example.demo.rpc.response.CoinDeskRpcRsp;
import com.example.demo.rpc.response.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CoinDeskControllerConverter {

    @Named("formatTime")
    default String formatTime(String iso) {
        if (iso == null || iso.length() < 19) {
            return null;
        }
        return iso.replace("T", " ").substring(0, 19);
    }

    @Mapping(target = "updatedTime", expression = "java(formatTime(raw.getTime().getUpdatedISO()))")
    ConvertedResponse toConvertedResponse(CoinDeskRpcRsp raw);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "rate", expression = "java(currency.getRate())")
    @Mapping(target = "code", expression = "java(currency.getCode())")
    CurrencyDto toCurrencyDto(Currency currency, String name);
}
