package com.example.Softlarism.Service;

import com.example.Softlarism.Model.Autoridades;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface AutoridadesService {
    List<Autoridades> getAll( );
    Autoridades getById(Integer id);
    Autoridades save(Autoridades autoridades);
    void delete(Integer id);
    Autoridades update(Integer id,Autoridades autoridades);
}
