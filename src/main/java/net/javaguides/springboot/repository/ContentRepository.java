package net.javaguides.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{
    Content findById(long id);

@Query(
  value = "SELECT * FROM db_example.content ct WHERE ct.id_service = ?1", 
  nativeQuery = true)
    List<Content> getContentByIdService(Long id);
}