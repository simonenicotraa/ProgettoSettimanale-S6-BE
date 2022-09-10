package com.epicode.devicemanagement.devices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfiguration {

	@Bean("dev1")
	public DeviceDto dev1() {
		return new DeviceDto("123ABC", DeviceType.TABLET, DeviceStatus.ASSIGNED);
	}
	@Bean("dev2")
	public DeviceDto dev2() {
		return new DeviceDto("456DEF", DeviceType.LAPTOP, DeviceStatus.AVAILABLE);
	}

}
