package com.EduTech.EduTech;

import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.repository.ContenidoEduRepository;
import com.EduTech.EduTech.service.ContenidoEduService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ContenidoEduServiceTest {

    @Autowired
    private ContenidoEduService contenidoEduService;

    @MockitoBean
    private ContenidoEduRepository contenidoEduRepository;

    @Test
    public void testFindAll(){
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(contenidoEduRepository.findAll()).thenReturn(List.of(new ContenidoEdu(1, "Associate Degree in Engineering", "Distinctio autem veritatis accusantium.", "Distinctio autem veritatis accusantium.")));

        // Llama al método findAll() del servicio.
        List<ContenidoEdu> contenidoEdus = contenidoEduService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(contenidoEdus);
        assertEquals(1, contenidoEdus.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        ContenidoEdu contenidoEdu = new ContenidoEdu(id, "Associate Degree in Engineering", "Distinctio autem veritatis accusantium.", "Completado");

        when(contenidoEduRepository.findById(id)).thenReturn(Optional.of(contenidoEdu));

        ContenidoEdu found = contenidoEduService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        ContenidoEdu contenidoEdu = new ContenidoEdu(1, "Associate Degree in Engineering", "Distinctio autem veritatis accusantium.", "Completado");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(contenidoEduRepository.save(contenidoEdu)).thenReturn(contenidoEdu);

        // Llama al método save() del servicio.
        ContenidoEdu saved = contenidoEduService.save(contenidoEdu);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Associate Degree in Engineering", saved.getActividad());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        doNothing().when(contenidoEduRepository).deleteById(id);

        contenidoEduService.deleteById(id);

        verify(contenidoEduRepository, times(1)).deleteById(id);
    }

}
