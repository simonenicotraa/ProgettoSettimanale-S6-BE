package com.epicode.devicemanagement.security.auth.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
}
