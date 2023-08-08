package com.userservice.project.controller;

import com.userservice.project.entity.User;
import com.userservice.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = service.createUser(user);
        return new ResponseEntity<>(user1,HttpStatus.CREATED);
    }

    @PutMapping("/update/{userid}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("userid") String userId){
        User user1 = service.updateUser(user, userId);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User> >getAllUsers(){
        List<User> allUser = service.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User user = service.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId){
        service.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteAllUsers(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
