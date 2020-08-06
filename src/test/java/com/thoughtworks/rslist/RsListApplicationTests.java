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

import javax.print.attribute.standard.Media;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RsListApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetRsList() throws Exception {

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                .andExpect(jsonPath("$[0]", not(hasKey("users"))))
                .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                .andExpect(jsonPath("$[1]", not(hasKey("users"))))
                .andExpect(jsonPath("$[2].keyWord").value("无分类"))
                .andExpect(jsonPath("$[2].eventName").value("第三条事件"))
                .andExpect(jsonPath("$[2]", not(hasKey("users"))))
                .andExpect(status().isOk());

    }

    @Test
    void shouldAddOneRsEvent() throws Exception{
        Users user4 = new Users("test1", 18, "male", "test1@1.com", "10123456789");
        String requestJson = "{\"eventName\":\"第四条事件\", \"keyWord\":\"无分类\", \"user\":{\"name\":\"test4\",\"age\":\"20\",\"gender\":\"male\",\"emil\":\"test4@1.com\",\"phone\":\"1324567890\"}}";
        mockMvc.perform(post("/rs/event").content(requestJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                .andExpect(jsonPath("$[0]", not(hasKey("users"))))
                .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                .andExpect(jsonPath("$[1]", not(hasKey("users"))))
                .andExpect(jsonPath("$[2].keyWord").value("无分类"))
                .andExpect(jsonPath("$[2].eventName").value("第三条事件"))
                .andExpect(jsonPath("$[2]", not(hasKey("users"))))
                .andExpect(jsonPath("$[3].keyWord").value("无分类"))
                .andExpect(jsonPath("$[3].eventName").value("第四条事件"))
                .andExpect(jsonPath("$[3]", not(hasKey("users"))))
                .andExpect(status().isOk());

    }

    @Test
    void shouldGetOneEvent() throws Exception {
        mockMvc.perform(get("/rs/list/1"))
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventName").value("第一条事件"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list/2"))
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventName").value("第二条事件"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list/3"))
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventName").value("第三条事件"))
                .andExpect(status().isOk());
    }

  @Test
      void shouldGetRsEventBetween() throws Exception {
          mockMvc.perform(get("/rs/list?start=1&end=2"))
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                  .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                  .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                  .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                  .andExpect(status().isOk());
          mockMvc.perform(get("/rs/list?start=2&end=3"))
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].eventName").value("第二条事件"))
                  .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                  .andExpect(jsonPath("$[1].eventName").value("第三条事件"))
                  .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                  .andExpect(status().isOk());
          mockMvc.perform(get("/rs/list?start=1&end=3"))
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$[0].eventName").value("第一条事件"))
                  .andExpect(jsonPath("$[0].keyWord").value("无分类"))
                  .andExpect(jsonPath("$[1].eventName").value("第二条事件"))
                  .andExpect(jsonPath("$[1].keyWord").value("无分类"))
                  .andExpect(jsonPath("$[2].eventName").value("第三条事件"))
                  .andExpect(jsonPath("$[2].keyWord").value("无分类"))
                  .andExpect(status().isOk());

      }

  }

}