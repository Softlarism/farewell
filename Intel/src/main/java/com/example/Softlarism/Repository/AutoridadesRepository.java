package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Autoridades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoridadesRepository extends JpaRepository<Autoridades,Integer> {
}
