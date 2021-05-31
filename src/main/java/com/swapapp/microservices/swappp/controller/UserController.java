package com.swapapp.microservices.swappp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapapp.microservices.swappp.dto.UserDto;
import com.swapapp.microservices.swappp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/users/{id}")
	public UserDto getUsers(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id) {
		return new ResponseEntity<>(userService.updateUser(id), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/users")
	public ResponseEntity addUser(@RequestBody UserDto user) {
		try {
			userService.addUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
