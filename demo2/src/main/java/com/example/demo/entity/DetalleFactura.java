package com.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private int productoid;
    private int    Cliente;
    private double Total;
    private double Descuento;
    private int Empleado;
}
