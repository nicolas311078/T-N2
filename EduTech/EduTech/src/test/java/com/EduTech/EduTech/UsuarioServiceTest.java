package com.EduTech.EduTech;

import com.EduTech.EduTech.model.Usuario;
import com.EduTech.EduTech.repository.UsuarioRepository;
import com.EduTech.EduTech.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll(){
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "779-05-0180", "Willian", "Kunze","amada.lesch@yahoo.com","6iofsg178b1vc7o","ESTUDIANTE")));

        // Llama al método findAll() del servicio.
        List<Usuario> usuarios = usuarioService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Usuario usuario = new Usuario(id, "779-05-0180", "Willian", "Kunze","amada.lesch@yahoo.com","6iofsg178b1vc7o","ESTUDIANTE");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1, "779-05-0180", "Willian", "Kunze","amada.lesch@yahoo.com","6iofsg178b1vc7o","ESTUDIANTE");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llama al método save() del servicio.
        Usuario saved = usuarioService.save(usuario);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("amada.lesch@yahoo.com", saved.getCorreo());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.delete(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
