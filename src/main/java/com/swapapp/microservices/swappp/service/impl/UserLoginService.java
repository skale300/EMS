package com.swapapp.microservices.swappp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swapapp.microservices.swapapp.security.UserPrincipal;
import com.swapapp.microservices.swappp.entity.UserLogin;
import com.swapapp.microservices.swappp.repository.UserLoginRepository;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	private UserLoginRepository userLoginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserLogin user = userLoginRepo.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("404 not found");
		}
		return new UserPrincipal(user);
	}

}
