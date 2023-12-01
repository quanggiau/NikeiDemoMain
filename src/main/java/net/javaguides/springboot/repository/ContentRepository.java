package net.javaguides.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{
    Content findById(long id);
}
