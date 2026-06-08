package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Pertenece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerteneceRepository extends JpaRepository<Pertenece, Integer> {

    /** Todas las comunidades a las que pertenece un usuario. */
    @Query("SELECT p FROM Pertenece p WHERE p.id_usuario = :idUsuario")
    List<Pertenece> findByUsuario(@Param("idUsuario") Integer idUsuario);

    /**
     * Cuenta cuántas filas coinciden con (usuario, comunidad).
     * Usamos COUNT (en vez de "COUNT(p) > 0") porque la versión booleana
     * falla en algunas versiones de Hibernate y provoca error 500.
     */
    @Query("SELECT COUNT(p) FROM Pertenece p WHERE p.id_usuario = :idUsuario AND p.id_comunidad = :idComunidad")
    long contarRelacion(@Param("idUsuario") Integer idUsuario,
                        @Param("idComunidad") Integer idComunidad);
}