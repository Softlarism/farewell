package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Comunidad;
import com.example.Softlarism.Repository.ComunidadRepository;
import com.example.Softlarism.Service.ComunidadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ComunidadServiceImpl implements ComunidadService {
    private final ComunidadRepository comunidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comunidad> getAll(){
        return comunidadRepository.findAll();
    }

    @Override
    @Transactional
    public Comunidad getById(Integer id){
        return comunidadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Comunidad save(Comunidad comunidad){
        return comunidadRepository.save(comunidad);
    }

    @Override
    @Transactional
    public void delete(Integer id){
        comunidadRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Comunidad update(Integer id, Comunidad comunidad){

        Comunidad aux = comunidadRepository.findById(id).orElse(null);
        if (aux == null)return null;
        aux.setCnombre(comunidad.getCnombre());
        aux.setDescripcion(comunidad.getDescripcion());
        aux.setTipo(comunidad.getTipo());
        aux.setCp2(comunidad.getCp2());
        return comunidadRepository.save(aux);
    }
}
