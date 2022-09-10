package com.epicode.devicemanagement.security.auth.users;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.devicemanagement.devices.Device;
import com.epicode.devicemanagement.security.auth.roles.Role;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping("/basicInformation")
	@PreAuthorize("isAutenticated()")
	public ResponseEntity<List<UserResponse>> getAllUsersBasicInformations() {
		return ResponseEntity.ok(userService.getAllUsersBasicInformations());
	}

	@GetMapping("/{userName}")
	@PreAuthorize("isAutenticated()")
	public ResponseEntity<UserResponse> getUsersBasicInformations(String userName) {
		return ResponseEntity.ok(userService.getUserBasicInformations(userName));
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok("User Deleted");
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> save(@RequestBody UserDto dto) {
		return ResponseEntity.ok(userService.save(dto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> refresh(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.ok(userService.refresh(id, user));
	}

	@PutMapping("/addDevice/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> refreshAddDevice(@PathVariable Long id, @RequestBody Device dev) {
		userService.addDevice(id, dev);
		return ResponseEntity.ok("Device has been Added");
	}

	@PutMapping("/addRole/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> refreshAddRole(@PathVariable Long id, @RequestBody Role role) {
		userService.addRole(id, role);
		return ResponseEntity.ok("Device has been Added");
	}
}
