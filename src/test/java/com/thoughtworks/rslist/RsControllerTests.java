package com.thoughtworks.rslist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//import static sun.plugin.util.ProgressMonitor.get;

@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldGetRsList() throws Exception{
        mockMvc.perform(get("/rs/ls"))
                .andExpect(content().string("[第一条事件, 第二条事件， 第三条事件]"))
                .andExpect(status().isOk())
                .andReturn();
    }


}
