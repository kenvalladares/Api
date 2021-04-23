package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private String name;
    private double    Precio;
    private String Descripcion;
    private double Descuento;
}