package com.swapapp.microservices.swappp.service;

import java.util.List;

import com.swapapp.microservices.swappp.dto.UserDto;
import com.swapapp.microservices.swappp.entity.User;

public interface UserService {

	List<UserDto> getUsers();

	UserDto getUser(Integer id);

	UserDto updateUser(Integer id);

	void deleteUser(Integer id);

	User addUser(UserDto id);

}
