package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Pertenece;

import java.util.List;

public interface PerteneceService {
    List<Pertenece> getAll();
    Pertenece getById(Integer id);
    Pertenece save (Pertenece pertenece);
    void delete(Integer id);
    Pertenece update(Integer id,Pertenece pertenece);

    List<Pertenece> getByUsuario(Integer idUsuario);
    boolean existeRelacion(Integer idUsuario,Integer idComunidad);
}
