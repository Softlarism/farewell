package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerteneceDto {
    private Integer id_usuario;
    private Integer id_comunidad;
    private Integer id_pertenece;

}
