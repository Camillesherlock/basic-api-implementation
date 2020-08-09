package com.thoughtworks.rslist.api;

import domain.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    public static Set<Users> usersSet = new HashSet();

    @PostMapping("/users")
    public static void register(@RequestBody @Valid Users user){
        if(!usersSet.contains(user))
            usersSet.add(user);

    }

}
