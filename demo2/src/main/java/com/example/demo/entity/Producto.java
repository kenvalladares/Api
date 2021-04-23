package com.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private String name;
    private int    Proveedor;
    private int Cantidad;
    private double Precio;
}