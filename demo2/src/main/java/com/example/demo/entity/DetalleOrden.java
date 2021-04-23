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
@Table(name = "DetalleOrden")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private int productoid;
    private Date Fecha;
    private double Total;
    private int NumeroOrden;
}
