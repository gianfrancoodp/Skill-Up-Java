package com.alkemy.wallet;


import com.alkemy.wallet.controller.TransactionController;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.security.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value= TransactionController.class)
@WithMockUser
public class PaymentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtils jwtUtil;

    String examplePayment = "{\"amount\":\"150\",\"description\":\"hola\",\"accountId\":\"1\",\"userId\":\"1\"}";
    @Test
    public void createTransaction() throws Exception {
        UserEntity dummy = new UserEntity();
        SecurityContext secContext = Mockito.mock(SecurityContext.class);
        Mockito.when(secContext.getAuthentication().getPrincipal()).thenReturn(dummy);
        String jwtToken= jwtUtil.generateToken(dummy);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transactions/payment").header("Authorization","Bearer" + jwtToken)
                .accept(MediaType.APPLICATION_JSON).content(examplePayment)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    @Test
    public void createTransactionMissingBody() throws Exception {
        UserEntity dummy = new UserEntity();
        SecurityContext secContext = Mockito.mock(SecurityContext.class);
        Mockito.when(secContext.getAuthentication().getPrincipal()).thenReturn(dummy);
        String jwtToken= jwtUtil.generateToken(dummy);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transactions/payment").header("Authorization","Bearer" + jwtToken);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        int response = result.getResponse().getStatus();

        System.out.print(response);


    }

}