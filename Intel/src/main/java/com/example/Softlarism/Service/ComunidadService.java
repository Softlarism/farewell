package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Comunidad;

import java.util.List;

public interface ComunidadService {
    List<Comunidad> getAll( );
    Comunidad getById( Integer id);
    Comunidad save(Comunidad comunidad);
    void delete(Integer id);
    Comunidad update(Integer id, Comunidad comunidad);
}
