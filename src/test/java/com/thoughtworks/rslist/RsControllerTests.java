package com.thoughtworks.rslist;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.RsEvent;
import domain.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.collection.IsMapContaining.hasKey;
//import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//import static java.util.Collections.get;

@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetRsList() throws Exception{
        //Users user1 = new Users("test1", 18, "male", "test1@1.com", "10123456789");
                mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                .andExpect(jsonPath("$[0]", hasKey("users")))
                .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                .andExpect(jsonPath("$[1]", hasKey("users")))
                        .andExpect(jsonPath("$[2].keyWord").value("无分类"))
                        .andExpect(jsonPath("$[2].eventName").value("第三条事件"))
                        .andExpect(jsonPath("$[2]", hasKey("users")))
                .andExpect(status().isOk());





    }

}
