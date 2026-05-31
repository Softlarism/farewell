package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Pertenece;
import com.example.Softlarism.Repository.PerteneceRepository;
import com.example.Softlarism.Service.PerteneceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class PerteneceServiceImpl implements PerteneceService {

    private final PerteneceRepository perteneceRepository;

    @Override
    public List<Pertenece> getAll(){
        return perteneceRepository.findAll();
    }

    @Override
    public Pertenece getById(Integer id) {
        return perteneceRepository.findById(id).orElse(null);
    }

    @Override
    public Pertenece save(Pertenece pertenece) {
        return perteneceRepository.save(pertenece);
    }
    @Override
    public  void delete (Integer id){
        perteneceRepository.deleteById(id);
    }
    @Override
    public Pertenece update(Integer id, Pertenece pertenece) {

        Pertenece aux = perteneceRepository.getById(id);
        pertenece.setId_comunidad(pertenece.getId_comunidad());
        pertenece.setId_usuario(pertenece.getId_usuario());
        perteneceRepository.save(aux);
        return aux;
    }
}
