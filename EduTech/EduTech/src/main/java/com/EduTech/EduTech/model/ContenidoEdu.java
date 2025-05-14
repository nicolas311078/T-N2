package com.EduTech.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Contenido_Educativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoEdu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = true)
    private String actividad;

    @Column (nullable = true)
    private String evaluacion;

    @Column (nullable = true)
    private String progresoEstudiante;

}
