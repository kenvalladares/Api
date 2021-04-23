package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long    id;
    private int    cliente;
    private int    empleado;
    private int    CAI;
    private double Total;
    private Date   Fecha;
}