package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Mensaje;

import java.util.List;

public interface MensajeService {
    List<Mensaje> getAll();
    Mensaje getById(Integer id);
    List<Mensaje> getByComunidad(Integer idComunidad);
    Mensaje save(Mensaje mensaje);
    void delete(Integer id);
    Mensaje update(Integer id, Mensaje mensaje);
}
