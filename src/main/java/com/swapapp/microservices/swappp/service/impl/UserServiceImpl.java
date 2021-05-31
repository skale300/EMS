package com.swapapp.microservices.swappp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapapp.microservices.swappp.dto.UserDto;
import com.swapapp.microservices.swappp.entity.User;
import com.swapapp.microservices.swappp.repository.UserRepository;
import com.swapapp.microservices.swappp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> getUsers() {
		LOGGER.info("inside get method: user details");
		List<User> users = userRepository.findAll();
		
		List<UserDto> userDto = users.stream().map
				(t -> modelMapper.map(t, UserDto.class)).collect(Collectors.toList());
		return userDto;
	}
	
	@Override
	public UserDto getUser(Integer id) {
		return modelMapper.map(userRepository.getById(id), UserDto.class);
	}

	@Override
	public UserDto updateUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User userUpdate = user.get();
			//take input from request object  
			userUpdate.setFirstName("Swapnil bhai");
			userUpdate.setLastName("Kale Patil");
			return modelMapper.map(userRepository.save(userUpdate), UserDto.class);
		} else {
			//throw exception user not exist
			return null;
		}
	}

	@Override
	public void deleteUser(Integer id) {
			userRepository.deleteById(id);
	}

	@Override
	public User addUser(UserDto user) {
		return userRepository.save(modelMapper.map(user, User.class));
	}
}