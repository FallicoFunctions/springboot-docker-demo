package com.nickfallico.springboot_docker_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nickfallico.springboot_docker_demo.controller.TransactionController;
import com.nickfallico.springboot_docker_demo.exception.GlobalExceptionHandler;
import com.nickfallico.springboot_docker_demo.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = TransactionController.class)
@Import(GlobalExceptionHandler.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransactionRepository repo;

    @Test
    void shouldRejectSingleTransactionAbove10000() throws Exception {
        var tx = new com.nickfallico.springboot_docker_demo.model.Transaction();
        tx.setUserId("u1");
        tx.setAmount(BigDecimal.valueOf(15000));
        tx.setCurrency("USD");

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tx)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Single transaction exceeds limit of 10,000"));
    }
}