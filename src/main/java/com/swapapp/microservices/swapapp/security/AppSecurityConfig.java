package com.swapapp.microservices.swapapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swapapp.microservices.swappp.service.impl.UserLoginService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserLoginService userLoginService;
	
	@Bean(name="myPasswordEncoder")
	public PasswordEncoder getPasswordEncoder() {
	        DelegatingPasswordEncoder delPasswordEncoder=  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        BCryptPasswordEncoder bcryptPasswordEncoder =new BCryptPasswordEncoder();
	    delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
	    return delPasswordEncoder;      
	}
	
	
	@Bean
    @Autowired  
    public DaoAuthenticationProvider getDaoAuthenticationProvider(@Qualifier("myPasswordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userLoginService);
        return daoAuthenticationProvider;
    }
	
	/*@Bean
	public AuthenticationProvider authProvider() {
		AuthenticationManagerBuilder  provider = new AuthenticationManagerBuilder(objectPostProcessor);
		provider.setUserDetailsService(userLoginService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}*/
	
	
}
