package com.swapapp.microservices.swappp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
// Junit test cases 
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
