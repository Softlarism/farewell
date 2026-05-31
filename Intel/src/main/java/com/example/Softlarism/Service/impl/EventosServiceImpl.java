package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Eventos;
import com.example.Softlarism.Repository.EventosRepository;
import com.example.Softlarism.Service.EventosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class EventosServiceImpl implements EventosService {
    private final EventosRepository eventosRepository;

    @Override
    @Transactional (readOnly = true)
    public List<Eventos> getAll() {
        return eventosRepository.findAll();
    }
    @Override
    @Transactional
    public Eventos getById(Integer id) {
        return eventosRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Eventos save(Eventos eventos) {
        return eventosRepository.save(eventos);
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        eventosRepository.deleteById(id);
    }
    @Override
    @Transactional
    public Eventos update (Integer id, Eventos eventos) {

        Eventos aux = eventosRepository.findById(id).orElse(null);
        if (aux == null)return null;
        aux.setTipo(eventos.getTipo());
        aux.setDescripcion(eventos.getDescripcion());
        aux.setId_usuario(eventos.getId_usuario());
        return eventosRepository.save(aux);
    }

}
