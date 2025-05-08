package com.EduTech.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true, length = 13, nullable = false)
    private String run;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String tipocurso;

}
