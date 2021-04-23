package com.example.demo.repository;
import com.example.demo.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    Optional<Empleados> findByName(String name);
}