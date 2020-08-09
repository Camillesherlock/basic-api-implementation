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
import java.util.ArrayList;
import java.util.List;

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
         UserController.usersList.clear();

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
        Users user = new Users("Jim", 20, null, "liuke@163.com", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void ageShouldNotLess18() throws Exception {
        Users user = new Users("Jim", 17, "male", "liuke@163.com", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void ageShouldNotMoreThan100() throws Exception {
        Users user = new Users("Jim", 101, "male", "liuke@163.com", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void emailShouldInLine() throws Exception {
        Users user = new Users("Jim", 19, "male", "liuke", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void phoneNumberShouldValid() throws Exception {
        Users user = new Users("Jim", 19, "male", "liuke@163.com", "1383232366232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void allPostRequestShouldReturn201InUserController() throws Exception {
        Users user = new Users("Jim", 20, "male", "liuke@163.com", "13832323232");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }
    @Test
    void shouldReturnAllUsers() throws Exception {
        List<Users> usersList = new ArrayList<>();
        Users user = new Users("pop", 38, "female", "tom@gmail.com", "15800000000");
        usersList.add(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String usersSerialization1 = objectMapper.writeValueAsString(user);
        String usersSerialization2 = objectMapper.writeValueAsString(usersList);
        mockMvc.perform(post("/users").content(usersSerialization1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        mockMvc.perform(get("/users"))
                .andExpect(content().string(usersSerialization2))
                .andExpect(status().isOk());

    }


}
