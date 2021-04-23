package com.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Proveedores")
public class Proveedores {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private String name;
    private String    Producto;
    private Date FechaExp;
    private Date FechaElaboracion;
    private double Precio;
}