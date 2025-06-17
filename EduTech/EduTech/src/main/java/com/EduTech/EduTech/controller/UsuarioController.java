package com.EduTech.EduTech.controller;

import com.EduTech.EduTech.model.Usuario;
import com.EduTech.EduTech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}

