package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByTelefono(String telefono);
    Optional<Usuarios> findByTelefonoAndContrasena(String telefono, String contrasena);
}
