package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerteneceDto {
    private int id_usuario;
    private int id_comunidad;

}
