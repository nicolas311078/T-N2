package com.EduTech.EduTech;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.repository.ContenidoEduRepository;
import com.EduTech.EduTech.repository.CursoRepository;
import com.EduTech.EduTech.service.ContenidoEduService;
import com.EduTech.EduTech.service.CursoService;
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
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockitoBean
    private CursoRepository cursoRepository;

    @Test
    public void testFindAll(){
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(cursoRepository.findAll()).thenReturn(List.of(new Curso(1, "Carlo Pfeffer", "Associate Degree in Nursing", 26)));

        // Llama al método findAll() del servicio.
        List<Curso> cursos = cursoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(cursos);
        assertEquals(1, cursos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Curso curso = new Curso(id, "Carlo Pfeffer", "Associate Degree in Nursing", 26);

        when(cursoRepository.findById(id)).thenReturn(Optional.of(curso));

        Curso found = cursoService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Curso curso = new Curso(1, "Carlo Pfeffer", "Associate Degree in Nursing", 26);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(cursoRepository.save(curso)).thenReturn(curso);

        // Llama al método save() del servicio.
        Curso saved = cursoService.save(curso);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Associate Degree in Nursing", saved.getNombreCurso());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        doNothing().when(cursoRepository).deleteById(id);

        cursoService.deleteById(id);

        verify(cursoRepository, times(1)).deleteById(id);
    }

}

