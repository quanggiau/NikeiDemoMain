package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.ServiceIT;

@Repository
public interface ServiceITRepository extends JpaRepository<ServiceIT, Long> {
    ServiceIT findById(long id);

}
