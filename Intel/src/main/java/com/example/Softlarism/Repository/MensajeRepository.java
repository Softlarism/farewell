package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    // Mensajes de una comunidad, ordenados del más viejo al más nuevo (orden de chat)
    @Query("select m from Mensaje m where m.id_comunidad = :idComunidad order by m.fecha asc")
    List<Mensaje> findByComunidad(@Param("idComunidad") Integer idComunidad);
}