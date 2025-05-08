package com.EduTech.EduTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>>listar(){
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();


        }
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso){
        Curso cursoNuevo = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoNuevo);
    }



}
