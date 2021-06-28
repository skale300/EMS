package com.swapapp.microservices.swappp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Test
	public void add() {
		assertEquals(10, userService.add(5, 5));
		assertEquals(-20, userService.add(-10, -10));
		assertEquals(20000, userService.add(10000, 10000));
	}

}
