package com.thoughtworks.rslist;

import com.thoughtworks.rslist.api.RsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RsListApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RsController rsController;

    @Test
    void shouldGetOneRsEvent() throws Exception {
     mockMvc.perform(get("/rs/1"))
             .andExpect(content().string("第一条事件"))
             .andExpect(status().isOk());
     mockMvc.perform(get("/rs/2"))
                .andExpect(content().string("第二条事件"))
                .andExpect(status().isOk());
     mockMvc.perform(get("/rs/3"))
                .andExpect(content().string("第三条事件"))
                .andExpect(status().isOk());
    }

}
