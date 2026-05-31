package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventosDto {
    private Integer id_evento;
    private String tipo;
    private String descripcion;
    private Integer id_usuario;

}
