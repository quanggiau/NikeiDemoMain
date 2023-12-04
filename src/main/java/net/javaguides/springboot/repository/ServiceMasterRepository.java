package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.ServiceMaster;

@Repository
public interface ServiceMasterRepository extends JpaRepository<ServiceMaster, Long> {
    
}
