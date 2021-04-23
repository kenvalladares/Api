package com.example.demo.repository;
import com.example.demo.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Long> {
    Optional<Proveedores> findByName(String name);
}
