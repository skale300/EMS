package com.swapapp.microservices.swappp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
		// if table is empty ?
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUsers(@PathVariable Integer id) {
		// All ok
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
		return new ResponseEntity(userService.addUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/userspage")
	public ResponseEntity<List<UserDto>> getUserDetails(Pageable pageable) {
		
		
//		http://localhost:8080/userspage?page=0&size=2&sort=firstName 
		
		List<UserDto> usersPage = userService.getUsersPage(pageable);
		return new ResponseEntity<List<UserDto>>(usersPage, HttpStatus.OK);
	}

}
