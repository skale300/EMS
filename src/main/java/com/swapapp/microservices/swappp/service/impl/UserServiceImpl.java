package com.swapapp.microservices.swappp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swapapp.microservices.swappp.dto.UserDto;
import com.swapapp.microservices.swappp.entity.User;
import com.swapapp.microservices.swappp.exception.UserNotFoundException;
import com.swapapp.microservices.swappp.repository.UserRepository;
import com.swapapp.microservices.swappp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public int add(Integer a, Integer b) {
		return a + b;
	}

	@Override
	public List<UserDto> getUsers() {
		LOGGER.info("inside get method: user details");
		List<User> users = userRepository.findAll();

		List<UserDto> userDto = users.stream().map(t -> modelMapper.map(t, UserDto.class)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public UserDto getUser(Integer id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		User userEntity = null;
		if(userOptional.isPresent()) {
			userEntity = userOptional.get();
		} else {
			throw new UserNotFoundException();
		}
		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User userUpdate = user.get();
			// take input from request object
			userUpdate.setFirstName(userDto.getFirstName());
			userUpdate.setLastName(userDto.getLastName());
			return modelMapper.map(userRepository.save(userUpdate), UserDto.class);
		} else {
			throw new UserNotFoundException();
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
	
	@Override
	public List<UserDto> getUsersPage(Pageable pageable) {

//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> usersPage = userRepository.findAll(pageable);
		List<User> users = usersPage.getContent();

		List<UserDto> userDto = users.stream().map(t -> modelMapper.map(t, UserDto.class)).collect(Collectors.toList());
		return userDto;
	}

}