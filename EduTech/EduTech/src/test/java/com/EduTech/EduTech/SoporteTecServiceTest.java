package com.EduTech.EduTech;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.repository.CursoRepository;
import com.EduTech.EduTech.repository.SoporteTecRepository;
import com.EduTech.EduTech.service.CursoService;
import com.EduTech.EduTech.service.SoporteTecService;
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
public class SoporteTecServiceTest {

    @Autowired
    private SoporteTecService soporteTecService;

    @MockitoBean
    private SoporteTecRepository soporteTecRepository;

    @Test
    public void testFindAll(){
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(soporteTecRepository.findAll()).thenReturn(List.of(new SoporteTec(1, "Carlo Pfeffer", "Associate Degree in Nursing", "descripcion del soporte")));

        // Llama al método findAll() del servicio.
        List<SoporteTec> soporteTecs = soporteTecService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(soporteTecs);
        assertEquals(1, soporteTecs.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        SoporteTec soporteTec = new SoporteTec(id, "Carlo Pfeffer", "Associate Degree in Nursing", "descripcion del soporte");

        when(soporteTecRepository.findById(id)).thenReturn(Optional.of(soporteTec));

        SoporteTec found = soporteTecService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getNumeroConsulta());
    }

    @Test
    public void testSave() {
        SoporteTec soporteTec = new SoporteTec(1, "Carlo Pfeffer", "Associate Degree in Nursing", "descripcion del soporte");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(soporteTecRepository.save(soporteTec)).thenReturn(soporteTec);

        // Llama al método save() del servicio.
        SoporteTec saved = soporteTecService.save(soporteTec);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Carlo Pfeffer", saved.getTipoIncidencia());
    }

    @Test
    public void testDeleteById() {
        Integer numeroConsulta = 1;

        doNothing().when(soporteTecRepository).deleteById(numeroConsulta);

        soporteTecService.deleteById(1);

        verify(soporteTecRepository, times(1)).deleteById(numeroConsulta);
    }

}

