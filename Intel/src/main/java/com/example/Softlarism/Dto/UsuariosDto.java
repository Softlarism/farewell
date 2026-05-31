package com.example.Softlarism.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuariosDto {
    private int id_usuario;
    private String telefono;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String cp1;
    private String numero;



}
