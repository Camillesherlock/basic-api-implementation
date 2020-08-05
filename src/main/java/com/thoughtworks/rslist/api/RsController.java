package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.RsEvent;
import domain.Users;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {
  private List<RsEvent> rsList = initRsEvent();

  private List<RsEvent> initRsEvent() {
    Users user1 = new Users("test1", 18, "male", "test1@1.com", "10123456789");
    Users user2 = new Users("test2", 19, "male", "test2@1.com", "11123456789");
    Users user3 = new Users("test1", 20, "male", "test3@1.com", "12123456789");
    List<RsEvent> result = new ArrayList<>();
    result.add(new RsEvent("第一条事件", "无分类", user1));
    result.add(new RsEvent("第二条事件", "无分类", user2));
    result.add(new RsEvent("第三条事件", "无分类", user3));
    return result;
  }

  @GetMapping("/rs/list")
  public List<RsEvent> getList() {
    return rsList;

  }
  @PostMapping("/rs/event")
  public ResponseEntity getOneRsEvent(@RequestBody @Valid RsEvent rsEvent) throws JsonProcessingException{
    rsList.add(rsEvent);
    return ResponseEntity.created(null).build();
  }
  @GetMapping("/rs/list/{index}")
  public ResponseEntity <RsEvent> getOneRsEvent(@PathVariable int index){
    return ResponseEntity.ok(rsList.get(index - 1));
  }

  @PutMapping("rs/list/{index}")
  public  ResponseEntity modifyOneRsEvent(@PathVariable int index, @RequestBody RsEvent rsEvent) {
    rsList.set(index - 1, rsEvent);
    return ResponseEntity.created(null).build();
  }
  @DeleteMapping("rs/list/{index}")
  public  ResponseEntity deleteOneRsEvent(@PathVariable int index) {
    return ResponseEntity.created(null).build();
  }


}