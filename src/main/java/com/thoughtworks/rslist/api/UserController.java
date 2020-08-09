package com.thoughtworks.rslist.api;

import domain.Users;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.rslist.utils.ListOperation;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class UserController {
    public static List<Users> usersList = new ArrayList<>();
    private Users user = new Users("Bob",50,"female","Bob@1.com","16666666666");

    public UserController(){
        usersList = new ArrayList<>();
        usersList.add(user);

    }


    @PostMapping("/users")
    public static ResponseEntity register(@RequestBody @Valid Users user){
        for (Users u : usersList) {
            if (u.getName() == user.getName())
                return ResponseEntity.created(null).build();
        }
        usersList.add(user);
        String headers = String.valueOf(usersList.indexOf(user));
        return ResponseEntity.created(URI.create(headers)).build();
    }
    @GetMapping("/users")
    public static ResponseEntity<List> addUsers(@RequestParam( required = false) Integer formIndex,
                                                @RequestParam ( required = false) Integer toIndex){
        return ListOperation.getListByIndex(formIndex,toIndex,usersList);
    }

}
