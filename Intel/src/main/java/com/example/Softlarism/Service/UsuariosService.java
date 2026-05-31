package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Usuarios;

import java.util.List;

public interface UsuariosService {
    List<Usuarios> getAll();
    Usuarios getById(Integer id);
    Usuarios save (Usuarios usuarios);
    void delete (Integer id);
    Usuarios update ( Integer id, Usuarios usuarios);
}
