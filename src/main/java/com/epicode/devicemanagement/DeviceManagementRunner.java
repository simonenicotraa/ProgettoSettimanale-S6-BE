package com.epicode.devicemanagement;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.epicode.devicemanagement.devices.Device;
import com.epicode.devicemanagement.devices.DeviceDto;
import com.epicode.devicemanagement.devices.DeviceService;
import com.epicode.devicemanagement.security.auth.roles.ERole;
import com.epicode.devicemanagement.security.auth.roles.Role;
import com.epicode.devicemanagement.security.auth.roles.RoleRepository;
import com.epicode.devicemanagement.security.auth.users.User;
import com.epicode.devicemanagement.security.auth.users.UserDto;
import com.epicode.devicemanagement.security.auth.users.UserService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class DeviceManagementRunner implements ApplicationRunner {
	
	RoleRepository roleRepository;
	DeviceService deviceService;
	UserService userService;
	
	@Qualifier("user1") UserDto user1;
	@Qualifier("user2") UserDto user2;
	
	@Qualifier("dev1") DeviceDto dev1;
	@Qualifier("dev2") DeviceDto dev2;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Device de1 = deviceService.save(dev1);
		Device de2 = deviceService.save(dev2);
		
		userService.save(user1);
		userService.addRole((long) 1, admin);
		userService.addDevice((long) 1, de1);
		User u = userService.findById((long) 1);
		
		userService.save(user2);
		userService.addRole((long) 2, user);
		userService.addDevice((long) 2, de2);
		User u1 = userService.findById((long) 2);
	System.out.println( u);
	System.out.println( u1);
	
	}

	

}
