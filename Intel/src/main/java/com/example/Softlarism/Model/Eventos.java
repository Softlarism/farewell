package com.example.Softlarism.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "Eventos")
public class Eventos {
    @Id
    @Column (name = "ID_EVENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_evento;
    @Column (name = "TIPO")
    private String tipo;
    @Column (name = "DESCRIPCION")
    private String descripcion;
    @Column (name = "ID_USUARIO")
    private Integer id_usuario;

}
