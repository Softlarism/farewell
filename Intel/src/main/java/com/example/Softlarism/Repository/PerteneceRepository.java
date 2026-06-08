package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Pertenece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerteneceRepository extends JpaRepository<Pertenece, Integer> {

    // Usamos consultas SQL NATIVAS (nativeQuery=true) contra las columnas reales
    // de la tabla. Así evitamos por completo los problemas de Hibernate con
    // los nombres de campo que llevan guion bajo (id_usuario, id_comunidad).

    /** Todas las filas de un usuario. */
    @Query(value = "SELECT * FROM Pertenece WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    List<Pertenece> findByUsuario(@Param("idUsuario") Integer idUsuario);

    /** Cuántas filas coinciden con (usuario, comunidad). 0 = no pertenece. */
    @Query(value = "SELECT COUNT(*) FROM Pertenece WHERE ID_USUARIO = :idUsuario AND ID_COMUNIDAD = :idComunidad",
            nativeQuery = true)
    long contarRelacion(@Param("idUsuario") Integer idUsuario,
                        @Param("idComunidad") Integer idComunidad);
}