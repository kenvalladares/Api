package com.example.demo.repository;
import com.example.demo.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findByName(String name);
}
