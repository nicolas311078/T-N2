package com.EduTech.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soporte_tecnico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteTec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroConsulta;

    @Column(nullable = false)
    private String tipoIncidencia;

    @Column (nullable = false)
    private String descripcion;

    @Column (nullable = false)
    private String correoSolicitante;

}
