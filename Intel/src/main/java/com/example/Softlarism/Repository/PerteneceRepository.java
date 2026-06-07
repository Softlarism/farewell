package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Pertenece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerteneceRepository extends JpaRepository<Pertenece, Integer> {

    // Usamos @Query explícito porque los campos llevan guion bajo (id_usuario),
    // y así evitamos problemas con la generación automática de Spring Data.

    /** Todas las comunidades a las que pertenece un usuario. */
    @Query("SELECT p FROM Pertenece p WHERE p.id_usuario = :idUsuario")
    List<Pertenece> findByUsuario(@Param("idUsuario") Integer idUsuario);

    /** true si ese usuario ya pertenece a esa comunidad (evitar duplicados). */
    @Query("SELECT COUNT(p) > 0 FROM Pertenece p WHERE p.id_usuario = :idUsuario AND p.id_comunidad = :idComunidad")
    boolean existeRelacion(@Param("idUsuario") Integer idUsuario,
                           @Param("idComunidad") Integer idComunidad);
}
