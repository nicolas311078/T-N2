package com.EduTech.EduTech.controller;
import com.EduTech.EduTech.assemblers.UsuarioModelAssembler;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.Usuario;
import com.EduTech.EduTech.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequestMapping("/api/v2/usuarios")
@Tag( name= "Usuarios V2", description = "Operaciones Relacionadas con los usuarios")
public class UsuarioControllerV2 {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los usuarios ", description = "Obtiene una lista con todos los usuarios")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener Usuario")
    })
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener usuario por codigo ", description = "Obtiene un usuario por codigo")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error al obtener Usuario")
    })
    public EntityModel<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        return assembler.toModel(usuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear usuario ", description = "Crea un usuario")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(newUsuario.getId())).toUri())
                .body(assembler.toModel(newUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un usuario ", description = "Actualiza un usuario existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content =@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<EntityModel<Usuario>> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .ok(assembler.toModel(updatedUsuario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un usuario ", description = "Elimina un usuario existente")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Usuario Eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


