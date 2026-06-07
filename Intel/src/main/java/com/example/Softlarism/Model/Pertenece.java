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
@Table(name = "Pertenece")
public class Pertenece {


    @Id
    @Column(name = "ID_PERTENECE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pertenece;


    @Column(name = "ID_USUARIO")
    private Integer id_usuario;

    @Column(name = "ID_COMUNIDAD")
    private Integer id_comunidad;
}
