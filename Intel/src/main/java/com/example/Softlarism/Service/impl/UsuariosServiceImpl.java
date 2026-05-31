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
    @Override
    public Usuarios update(Integer id, Usuarios usuarios){
        Usuarios aux = usuariosRepository.getById(id);
        usuarios.setNombre(usuarios.getNombre());
        usuarios.setApellido(usuarios.getApellido());
        usuarios.setContrasena(usuarios.getContrasena());
        usuarios.setId_usuario(usuarios.getId_usuario());
        usuarios.setNumero(usuarios.getNumero());
        usuarios.setCp1(usuarios.getCp1());
        usuarios.setTelefono(usuarios.getTelefono());
        usuariosRepository.save(aux);
        return aux;
    }
}
