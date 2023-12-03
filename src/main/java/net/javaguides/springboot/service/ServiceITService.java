package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.ServiceIT;

public interface ServiceITService {
    
    List<ServiceIT> getAllServices();
    void saveService(ServiceIT service);
    ServiceIT getServiceById(long id);
    ServiceIT updateService(ServiceIT service);
}
