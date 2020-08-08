package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.RsEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {
  private List<RsEvent> rsList = initRsEvent();
  private List<RsEvent>initRsEvent(){
    List<RsEvent> result = new ArrayList<>();
    result.add(new RsEvent("第一条事件","无分类"));
    result.add(new RsEvent("第二条事件","无分类"));
    result.add(new RsEvent("第三条事件","无分类"));
    return result;
  }

@GetMapping("/rs/list/{index}")
  public RsEvent getOneRsEvent(@PathVariable int index){
  return rsList.get(index-1);
  }
}
