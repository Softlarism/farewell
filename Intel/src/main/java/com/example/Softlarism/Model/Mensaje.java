package com.example.Softlarism.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Mensaje")
public class Mensaje {
    @Id
    @Column(name = "ID_MENSAJE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mensaje;

    @Column(name = "CONTENIDO", length = 500)
    private String contenido;

    @Column(name = "ID_USUARIO")
    private Integer id_usuario;

    @Column(name = "ID_COMUNIDAD")
    private Integer id_comunidad;

    @Column(name = "FECHA")
    private LocalDateTime fecha;
}
