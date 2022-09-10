package com.epicode.devicemanagement.devices;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

	private String productCode;
	private DeviceType deviceType;
	private DeviceStatus deviceStatus;
	
}
