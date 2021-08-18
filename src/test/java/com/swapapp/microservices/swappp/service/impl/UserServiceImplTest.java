package com.swapapp.microservices.swappp.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.swapapp.microservices.swappp.dto.UserDto;
import com.swapapp.microservices.swappp.service.UserService;

@SpringBootTest
public class UserServiceImplTest {
	
	@Mock
	private UserService userService;
	
	@BeforeAll
	static void printBeforeAll() {
		System.out.println("Before all--------");
	}
	
	@BeforeEach
	void printBefore() {
		System.out.println("Before-----------");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	final void testGetUser() {
		UserDto user = new UserDto();
		user.setFirstName("Eva");
		user.setId(1);
		user.setLastName("Marry");
		user.setMobile("7777");
		when(userService.getUser(anyInt())).thenReturn(user);
	}
	
	@AfterAll
	static void printAfterAll() {
		System.out.println("After All---------");
	}
	
	@AfterEach
	void printAfter() {
		System.out.println("After-----------");
	}

}
