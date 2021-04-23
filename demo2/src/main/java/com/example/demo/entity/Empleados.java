package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long     id;
    private String  name;
    private String  Puesto;
    private double  Sueldo;
    private int     DNI;
    private int     Telefono;
}