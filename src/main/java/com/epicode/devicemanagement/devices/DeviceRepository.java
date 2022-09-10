package com.epicode.devicemanagement.devices;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends PagingAndSortingRepository<Device, String>{

}
