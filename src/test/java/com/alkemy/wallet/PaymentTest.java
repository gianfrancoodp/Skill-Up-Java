package com.alkemy.wallet;


import com.alkemy.wallet.controller.TransactionController;
import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.util.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value= TransactionController.class)
@WithMockUser
public class PaymentTest {

    @Autowired
    private MockMvc mockMvc;

    String examplePayment = "{\"amount\":\"150\",\"description\":\"hola\",\"accountId\":\"1\",\"userId\":\"1\"}";
    @Test
    public void createTransaction() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transactions/payment")
                .accept(MediaType.APPLICATION_JSON).content(examplePayment)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }
}