package com.example.demo.repository;
import com.example.demo.entity.Factura;
import com.example.demo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FacturaRepository extends JpaRepository<Factura, Long> {

}