package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Autoridades;
import com.example.Softlarism.Repository.AutoridadesRepository;
import com.example.Softlarism.Service.AutoridadesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AutoridadesServiceImpl implements AutoridadesService {
    private final AutoridadesRepository autoridadesRepository;

    @Override
    public List<Autoridades> getAll(){
        return autoridadesRepository.findAll();
    }

    @Override
    public Autoridades getById(Integer id){
        return autoridadesRepository.findById(id).orElse(null);
    }
    @Override
    public Autoridades save(Autoridades autoridades){
        return autoridadesRepository.save(autoridades);
    }

    @Override
    public void delete(Integer id){
        autoridadesRepository.deleteById(id);
    }

    @Override
    public Autoridades update(Integer id, Autoridades autoridades) {
        Autoridades aux =autoridadesRepository.getById(id);
        autoridades.setId_autoridad(autoridades.getId_autoridad());
        autoridades.setContacto(autoridades.getContacto());
        autoridades.setAnombre(autoridades.getAnombre());
        autoridadesRepository.save(aux);
        return aux;
    }
}
