package com.EduTech.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroConsulta;

    @Column(nullable = false)
    private String tipoIncidecia;

    @Column (nullable = false)
    private String descripcion;

    @Column (nullable = false)
    private String correoSolicitante;

}
