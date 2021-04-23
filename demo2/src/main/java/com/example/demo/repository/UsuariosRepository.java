package com.example.demo.repository;
import com.example.demo.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByName(String name);
}
