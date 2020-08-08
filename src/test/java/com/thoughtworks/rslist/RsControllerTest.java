package com.thoughtworks.rslist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.api.RsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    void shouldGetOneRsEvent() throws Exception{
        mockMvc.perform(get("/rs/list/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventName").value("第一条事件"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventName").value("第二条事件"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.keyWord").value("无分类"))
                .andExpect(status().isOk());

    }
@Test
    void shouldGetRsListBetween() throws Exception {
        mockMvc.perform(get("/rs/list?start=1&end=2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                .andExpect(status().isOk());

}
@Test
    void shouldAddRsEvent() throws Exception {
        RsEvent rsEvent = new RsEvent("第四条事件","无分类");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(rsEvent);
    mockMvc.perform(put("/rs/list").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    mockMvc.perform(get("/rs/list/4"))
            .andExpect(jsonPath("$.eventName").value("第四条事件"))
            .andExpect(status().isOk());

}
@Test
    void shouldModifyOneRsEvent() throws Exception {
    RsEvent rsEvent = new RsEvent("第四条事件","无分类");
    ObjectMapper objectMapper = new ObjectMapper();
    String body = objectMapper.writeValueAsString(rsEvent);
    mockMvc.perform(put("/rs/list/1").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    mockMvc.perform(get("/rs/list/1"))
            .andExpect(jsonPath("$.eventName").value("第四条事件"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteOneRsEvent() throws Exception {
        mockMvc.perform(delete("/rs/list/1")).andExpect(status().isOk());
        mockMvc.perform(get("/rs/list/1"))
                .andExpect(jsonPath("$.eventName").value("第二条事件"))
                .andExpect(status().isOk());
    }
}
