package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.ServiceMaster;
import net.javaguides.springboot.repository.ServiceMasterRepository;

@Service
public class ServiceMasterServiceImpl implements ServiceMasterSRV{

    @Autowired
    private ServiceMasterRepository serviceMTRepository;

    @Override
    public List<ServiceMaster> getAllService() {
        return serviceMTRepository.findAll();
         
    }

    
}
