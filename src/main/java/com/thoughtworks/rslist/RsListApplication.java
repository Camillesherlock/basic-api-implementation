package com.thoughtworks.rslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RsListApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsListApplication.class, args);

    }
    private List<String> rsList = Arrays.asList("第一条事件", "第二条事件", "第三条事件");

    @GetMapping("/re/ls")
    public String getRsList(){
        return rsList.toString();
    }

}
