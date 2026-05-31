package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComunidadDto {
    private int id_comunidad;
    private String cnombre;
    private String descripcion;
    private String tipo;
    private String cp2;
}
