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
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   id;
    private String name;
    private String Usuario;
    private String Password;
    private String Correo;
}