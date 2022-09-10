package com.epicode.devicemanagement.devices;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	public Page<Device> findAll(Pageable pageable) {
		return deviceRepository.findAll(pageable);
	}
	
	public Device findById(String id) {
		if(!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		
		return deviceRepository.findById(id).get();
	}
	
	public void delete(String id) {
		if(!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		deviceRepository.deleteById(id);
	}
	
	public Device save(DeviceDto dto) {

		if(deviceRepository.existsById(  dto.getProductCode()  )) {
			throw new EntityExistsException("Device Existing");
		}
		Device d = new Device();
		BeanUtils.copyProperties(dto, d);
		
		deviceRepository.save(d);
		return d;
	}
	
	public Device refresh(String id,DeviceDto dto) {
		if(!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		Device d = deviceRepository.findById(id).get();
		BeanUtils.copyProperties(dto, d);
		deviceRepository.save(d);
		return d;
	}
	

}
