package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Contacta;

import java.util.List;

public interface ContactaService {
    List<Contacta> getAll();
    Contacta getById(Integer id);
    Contacta save ( Contacta contacta);
    void delete ( Integer id);
    Contacta update (Integer id,Contacta contacta);
}
