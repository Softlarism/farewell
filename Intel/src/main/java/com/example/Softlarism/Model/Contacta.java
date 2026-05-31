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
@Table (name = "Contacta")
public class Contacta {
    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @Column(name = "ID_AUTORIDAD")
    private Integer id_autoridad;

}
