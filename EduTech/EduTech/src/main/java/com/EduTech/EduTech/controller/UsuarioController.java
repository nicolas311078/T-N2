package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.Usuario;
import com.EduTech.EduTech.service.UsuarioService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag( name= "Usuarios", description = "Operaciones Relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios ", description = "Obtiene una lista con todos los usuarios")
    public ResponseEntity<List<Usuario>> lister() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Operation(summary = "Crear usuario ", description = "Crea un usuario")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener curso por codigo ", description = "Obtiene un curso por codigo")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario ", description = "Actualiza un usuario existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                        schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario pac = usuarioService.findById(id);
            pac.setId(id);
            pac.setRun(usuario.getRun());
            pac.setNombre(usuario.getNombre());
            pac.setApellido(usuario.getApellido());
            pac.setCorreo(usuario.getCorreo());
            pac.setContrasena(usuario.getContrasena());
            pac.setRol(usuario.getRol());

            usuarioService.save(pac);
            return ResponseEntity.ok(usuario);
        } catch (Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario ", description = "Elimina un usuario existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}

