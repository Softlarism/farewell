package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos,Integer> {
}
