package com.example.Softlarism.Service.impl;
import com.example.Softlarism.Model.Usuarios;
import com.example.Softlarism.Service.UsuariosService;
import com.example.Softlarism.Repository.UsuariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;

    @Override
    public List<Usuarios> getAll ( ){
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios getById(Integer id){
        return usuariosRepository.findById(id).orElse(null);
    }
    @Override
    public Usuarios save(Usuarios usuarios){
        return usuariosRepository.save(usuarios);
    }
    @Override
    public void delete(Integer id){
        usuariosRepository.deleteById(id);
    }

    // ── CORREGIDO ──
    // Antes: copiaba los datos sobre sí mismo y guardaba 'aux' sin cambios,
    // y getById() lanzaba excepción (error 500) si el id no existía.
    @Override
    public Usuarios update(Integer id, Usuarios usuarios){
        Usuarios aux = usuariosRepository.findById(id).orElse(null);
        if (aux == null) return null;

        // Solo actualizamos los campos que llegan con valor (no borramos lo demás)
        if (usuarios.getNombre() != null)     aux.setNombre(usuarios.getNombre());
        if (usuarios.getApellido() != null)   aux.setApellido(usuarios.getApellido());
        if (usuarios.getTelefono() != null)   aux.setTelefono(usuarios.getTelefono());
        if (usuarios.getNumero() != null)     aux.setNumero(usuarios.getNumero());
        if (usuarios.getCp1() != null)        aux.setCp1(usuarios.getCp1());
        // La contraseña solo se cambia si viene una nueva
        if (usuarios.getContrasena() != null && !usuarios.getContrasena().isEmpty()) {
            aux.setContrasena(usuarios.getContrasena());
        }

        return usuariosRepository.save(aux);   // guardamos 'aux' YA con los cambios
    }
}