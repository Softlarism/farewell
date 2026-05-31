package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Eventos;

import java.util.List;

public interface EventosService {
    List<Eventos> getAll();
    Eventos getById(Integer id);
    Eventos save(Eventos eventos);
    void delete(Integer id);
    Eventos update(Integer id, Eventos eventos);
}
