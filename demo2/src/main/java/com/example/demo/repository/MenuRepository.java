package com.example.demo.repository;
import com.example.demo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByName(String name);
}
