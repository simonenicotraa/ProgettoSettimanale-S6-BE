package com.epicode.devicemanagement.security.auth.users;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.devicemanagement.devices.Device;
import com.epicode.devicemanagement.security.auth.roles.Role;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<UserResponse> getAllUsersBasicInformations() {
		return userRepository.findAll().stream()
				.map(user -> UserResponse.builder().userName(user.getUsername())
						.role(user.getRoles().stream().findFirst().get().getRoleName().name()).build())
				.collect(Collectors.toList());
	}

	public UserResponse getUserBasicInformations(String userName) {
		User user = userRepository.findByUsername(userName).get();

		return UserResponse.builder().userName(userName)
				.role(user.getRoles().stream().findFirst().get().getRoleName().name()).build();

	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User Not Found");
		}
		return userRepository.findById(id).get();
	}

	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User Not Found");
		}
		userRepository.deleteById(id);
	}

	public User save(UserDto dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new EntityExistsException("User existing");
		}
		User u = new User();
		BeanUtils.copyProperties(dto, u);
		userRepository.save(u);
		return u;
	}

	public User refresh(Long id, User user) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User Not Found");
		}
		return userRepository.save(user);
	}

	public void addDevice(Long id, Device device) {
		User u = findById(id);
		u.addDevice(device);
		refresh(u.getId(), u);
	}

	public void addRole(Long id, Role role) {
		User u = findById(id);
		u.addRole(role);
		refresh(u.getId(), u);
	}

}
