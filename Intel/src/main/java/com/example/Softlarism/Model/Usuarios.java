package com.example.Softlarism.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "Usuarios")

public class Usuarios {
    @Id
    @Column(name = "ID_USUARIO" )
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column (name = "CONTRASENA")
    private String contrasena;
    @Column (name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "CP1")
    private String cp1;
    @Column (name = "NUMERO")
    private String numero;

}
