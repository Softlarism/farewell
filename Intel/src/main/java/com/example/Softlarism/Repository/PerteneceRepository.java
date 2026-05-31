package com.example.Softlarism.Repository;

import com.example.Softlarism.Model.Pertenece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerteneceRepository extends JpaRepository<Pertenece,Integer> {
}
