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
@Table(name = "Orden")
public class Orden {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long     id;
    private int      cliente;
    private String   Descripcion;
    private Date     Fecha;
    private int      Empleado;
}
