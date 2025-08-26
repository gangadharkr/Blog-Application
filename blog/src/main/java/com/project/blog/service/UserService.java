package com.project.blog.service;

import com.project.blog.model.User;
import com.project.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
 UserDto createUser(UserDto user);
 UserDto updateUser(UserDto user,Integer userId);
 UserDto getUserById(Integer userId);
 List<UserDto> getAllUsers();
 void deleteUser(Integer userId);
}
