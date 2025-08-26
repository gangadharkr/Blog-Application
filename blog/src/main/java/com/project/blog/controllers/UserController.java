package com.project.blog.controllers;

import com.project.blog.payloads.ApiResponse;
import com.project.blog.payloads.UserDto;
import com.project.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
//    POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto=this.userService.createUser(userDto);
       return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

//    PUT-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId) {
        UserDto updatedUser=this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }
//    DELETE - delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        this.userService.deleteUser(uid);
       return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
//    GET - read user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
       return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity <UserDto> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
