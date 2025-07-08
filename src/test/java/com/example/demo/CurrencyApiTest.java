package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/api/currency"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void testCreate() throws Exception {
        String requestBody = "{"
            + "\"code\": \"JPY\","
            + "\"nameZh\": \"日圓\""
            + "}";

        mockMvc.perform(post("/api/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void testUpdate() throws Exception {
        String requestBody = "{"
            + "\"code\": \"BTC\","
            + "\"nameZh\": \"比特幣\""
            + "}";

        Long id = 1L;
        mockMvc.perform(put("/api/currency/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 4L;

        mockMvc.perform(delete("/api/currency/{id}", id))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
