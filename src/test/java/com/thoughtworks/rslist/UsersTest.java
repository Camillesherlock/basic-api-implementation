package com.thoughtworks.rslist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.api.UserController;
import domain.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UsersTest {
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        // UserController.userList.clear();

    }
    @Test
    void shouldRegisterUsers() throws Exception {
        Users user = new Users("Tom",36,"male","tom@1.com","18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userTom = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userTom).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        mockMvc.perform(get("/users"));
        assertEquals(2, UserController.usersList.size());
    }
    @Test
    void genderShouldNotNull() throws Exception {
        Users user = new Users("Jim", 20, null, "qijinhaoup@163.com", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
