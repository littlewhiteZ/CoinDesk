package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CoinDeskApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRaw_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/coindesk/raw"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.bpi").exists())
            .andDo(print());
    }

    @Test
    void testGetConverted_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/coindesk/converted"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.currencies").isArray())
            .andDo(print());
    }
}
