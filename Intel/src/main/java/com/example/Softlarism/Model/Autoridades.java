package com.example.Softlarism.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "Autoridades")
public class Autoridades {
    @Id
    @Column(name = "ID_AUTORIDAD")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_autoridad;
    @Column(name = "ANOMBRE")
    private String anombre;
    @Column (name = "CONTACTO")
    private String contacto;
}
