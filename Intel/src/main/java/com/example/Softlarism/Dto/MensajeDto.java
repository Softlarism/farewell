package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MensajeDto {
    private Integer id_mensaje;
    private String contenido;
    private Integer id_usuario;
    private Integer id_comunidad;
    private LocalDateTime fecha;
}

