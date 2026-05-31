package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoridadesDto {
    private int id_autoridad;
    private String anombre;
    private String contacto;

}
