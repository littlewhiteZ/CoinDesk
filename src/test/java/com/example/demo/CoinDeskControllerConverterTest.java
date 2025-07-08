package com.example.demo;

import com.example.demo.controller.response.ConvertedResponse;
import com.example.demo.controller.response.CurrencyDto;
import com.example.demo.converter.CoinDeskControllerConverter;
import com.example.demo.rpc.response.CoinDeskRpcRsp;
import com.example.demo.rpc.response.Currency;
import com.example.demo.rpc.response.Time;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinDeskControllerConverterTest {

    CoinDeskControllerConverter converter = Mappers.getMapper(CoinDeskControllerConverter.class);

    @Test
    void testNormalConversion() {
        Time time = new Time();
        time.setUpdatedISO("2025-07-04T10:15:30Z");

        CoinDeskRpcRsp raw = new CoinDeskRpcRsp();
        raw.setTime(time);

        ConvertedResponse response = converter.toConvertedResponse(raw);
        assertEquals("2025-07-04 10:15:30", response.getUpdatedTime());
    }

    @Test
    void testNullUpdatedISO() {
        Time time = new Time();
        time.setUpdatedISO(null);

        CoinDeskRpcRsp raw = new CoinDeskRpcRsp();
        raw.setTime(time);

        ConvertedResponse response = converter.toConvertedResponse(raw);

        assertNull(response.getUpdatedTime());
    }

    @Test
    void testShortISOString() {
        // 字串太短，substring(0,19) 會報錯
        Time time = new Time();
        time.setUpdatedISO("2025-07-04T10"); // 不夠長

        CoinDeskRpcRsp raw = new CoinDeskRpcRsp();
        raw.setTime(time);

        ConvertedResponse response = converter.toConvertedResponse(raw);

        assertNull(response.getUpdatedTime());
    }

    @Test
    void testISOWithoutT() {
        // 沒有 T 的字串，replace 沒問題，但 substring(0,19) 會失敗
        Time time = new Time();
        time.setUpdatedISO("20250704101530Z");

        CoinDeskRpcRsp raw = new CoinDeskRpcRsp();
        raw.setTime(time);

        ConvertedResponse response = converter.toConvertedResponse(raw);

        assertNull(response.getUpdatedTime());
    }

    @Test
    void testCurrencyDtoWithNullCurrency() {
        assertThrows(NullPointerException.class, () -> {
            converter.toCurrencyDto(null, "美金");
        });
    }

    @Test
    void testCurrencyDtoWithNullName() {
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setRate(String.valueOf(new BigDecimal("1234.56")));

        CurrencyDto dto = converter.toCurrencyDto(currency, null);

        assertNull(dto.getName());
        assertEquals("USD", dto.getCode());
        assertEquals("1234.56", dto.getRate());
    }
}
