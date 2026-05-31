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
@Table (name = "Comunidad")
public class Comunidad {
    @Id
    @Column(name = "ID_COMUNIDAD")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_comunidad;
    @Column(name = "CNOMBRE")
    private String cnombre;
    @Column (name = "DESCRIPCION")
    private String descripcion;
    @Column (name = "TIPO")
    private String tipo;
    @Column(name = "CP2")
    private String cp2;
}
