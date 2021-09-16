package com.swapapp.microservices.ems.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
 
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
