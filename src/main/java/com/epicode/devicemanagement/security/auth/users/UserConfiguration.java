package com.epicode.devicemanagement.security.auth.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

	@Bean("user1")
	public UserDto user1() {
		return new UserDto("Gino", "123456", "Gino", "Form", "form.gino@gmail.com");
			//	
	}
	@Bean("user2")
	public UserDto user2() {
		return new UserDto("giudi", "111222", "Giuditta", "Maleducata", "giudi@gmail.com");
	}

}
