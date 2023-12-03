package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.ServiceIT;
import net.javaguides.springboot.repository.ServiceITRepository;

@Service
public class ServiceITServiceImpl implements ServiceITService {
    @Autowired
    private ServiceITRepository serviceITRepository;

    public ServiceITServiceImpl(ServiceITRepository serviceITRepository){
        this.serviceITRepository = serviceITRepository;
    }

     @Override
    public List<ServiceIT> getAllServices() {
       return serviceITRepository.findAll();

    }

    @Override
    public void saveService(ServiceIT service) {
        this.serviceITRepository.save(service);
    }

    @Override
    public ServiceIT getServiceById(long id) {
        return serviceITRepository.findById(id);
    }

    @Override
    public ServiceIT updateService(ServiceIT service) {
        return serviceITRepository.save(service);
    }
    
}
