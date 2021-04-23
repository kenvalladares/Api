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
@Table(name = "Clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private String name;
    private int    Telefono;
    private int DNI;
    private String Correo;
    private Date FechaNacimiento;
}

