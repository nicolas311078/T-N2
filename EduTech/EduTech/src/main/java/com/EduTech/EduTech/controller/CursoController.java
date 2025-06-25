package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
@Tag( name= "Cursos", description = "Operaciones Relacionadas con los cursos")
public class    CursoController {
    @Autowired
    private CursoService cursoService;


    @GetMapping
    @Operation(summary = "Obtener todos los cursos ", description = "Obtiene una lista con todos los cursos")
    public ResponseEntity<List<Curso>>listar(){
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }



    @PostMapping
    @Operation(summary = "Crear curso ", description = "Crea un nuevo curso")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Curso creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "curso no encontrado")
    })
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso){
        Curso cursoNuevo = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoNuevo);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por codigo ", description = "Obtiene curso via codigo")
    public ResponseEntity<Curso> buscar(@PathVariable Integer id){
        try{
            Curso curso = cursoService.findById(id);
            return ResponseEntity.ok(curso);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso", description = "Actualiza un curso existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> actualizar(@PathVariable Integer id,@RequestBody Curso curso){
        try{
            Curso cur = cursoService.findById(id);
            cur.setId(id);
            cur.setNombreCurso(curso.getNombreCurso());
            cur.setDocente(curso.getDocente());
            cur.setNumAlumnos(curso.getNumAlumnos());
            cursoService.save(cur);
            return ResponseEntity.ok(curso);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Curso ", description = "Elimina un curso existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Curso Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try{
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
