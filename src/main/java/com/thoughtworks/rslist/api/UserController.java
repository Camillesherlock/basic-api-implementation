package com.thoughtworks.rslist.api;

import domain.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    public static List<Users> usersList = new ArrayList<>();
    private Users user = new Users("Bob",50,"female","Bob@1.com","16666666666");

    public UserController(){
        usersList = new ArrayList<>();
        usersList.add(user);

    }

    @PostMapping("users")
    public static ResponseEntity register(@RequestBody @Valid Users user ){
        for(Users u : usersList){
            if(u.getName()==user.getName())
                return ResponseEntity.created(null).build();
        }
        usersList.add(user);
        String headers = String.valueOf(usersList.indexOf(user));
        return ResponseEntity.created(URI.create(headers)).build();
    }
}
