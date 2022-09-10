package com.epicode.devicemanagement.devices;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.epicode.devicemanagement.security.auth.users.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Device {
	@Id
	private String productCode;
	private DeviceType deviceType;
	private DeviceStatus deviceStatus;
	@ManyToOne
	private User user;
}
