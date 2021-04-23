package com.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Mesas")
public class Mesas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private int asientos;
    private String    estado;
    private int Numero;
    private int Piso;
}
