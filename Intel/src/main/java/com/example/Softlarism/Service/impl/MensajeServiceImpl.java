package com.example.Softlarism.Service.impl;

import com.example.Softlarism.Model.Mensaje;
import com.example.Softlarism.Repository.MensajeRepository;
import com.example.Softlarism.Service.MensajeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MensajeServiceImpl implements MensajeService {
    private final MensajeRepository mensajeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> getAll() {
        return mensajeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mensaje getById(Integer id) {
        return mensajeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> getByComunidad(Integer idComunidad) {
        return mensajeRepository.findByComunidad(idComunidad);
    }

    @Override
    @Transactional
    public Mensaje save(Mensaje mensaje) {
        // Si no viene fecha, la ponemos nosotros (hora del servidor)
        if (mensaje.getFecha() == null) {
            mensaje.setFecha(LocalDateTime.now());
        }
        return mensajeRepository.save(mensaje);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Mensaje update(Integer id, Mensaje mensaje) {
        Mensaje aux = mensajeRepository.findById(id).orElse(null);
        if (aux == null) return null;
        aux.setContenido(mensaje.getContenido());
        aux.setId_usuario(mensaje.getId_usuario());
        aux.setId_comunidad(mensaje.getId_comunidad());
        return mensajeRepository.save(aux);
    }
}
