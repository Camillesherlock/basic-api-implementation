package com.thoughtworks.rslist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.api.UserController;
import domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UsersTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldRegisterUsers() throws Exception {
        Users user = new Users("Tom",36,"male","tom@1.com","18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userTom = objectMapper.writeValueAsString(objectMapper);
        mockMvc.perform(post("users").content(userTom).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        mockMvc.perform(get("users"));
        assertEquals(1, UserController.usersList.size());
    }
}
