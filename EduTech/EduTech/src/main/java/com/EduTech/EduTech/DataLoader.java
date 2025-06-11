package com.EduTech.EduTech;

import com.EduTech.EduTech.repository.ContenidoEduRepository;
import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.repository.CursoRepository;
import com.EduTech.EduTech.repository.SoporteTecRepository;
import com.EduTech.EduTech.repository.UsuarioRepository;
import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.model.Usuario;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ContenidoEduRepository contenidoEduRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private SoporteTecRepository soporteTecRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar contenido educativo
        for (int i = 0; i < 4; i++) {
            ContenidoEdu contenidoEdu = new ContenidoEdu();
            //contenidoEdu.setId(i + 1);
            contenidoEdu.setActividad(faker.educator().course());
            contenidoEdu.setEvaluacion(faker.lorem().sentence());
            contenidoEdu.setProgresoEstudiante(faker.options().option("Iniciado", "En progreso", "Completado"));
            contenidoEduRepository.save(contenidoEdu);
        }

        // Generar cursos
        for (int i = 0; i < 5; i++) {
            Curso curso = new Curso();
            //curso.setId(i + 1);
            curso.setNombreCurso(faker.educator().course());
            curso.setDocente(faker.name().fullName());
            curso.setNumAlumnos(faker.number().numberBetween(1, 70));
            cursoRepository.save(curso);
        }

        // Generar soporte técnico
        for (int i = 0; i < 50; i++) {
            SoporteTec soporteTec = new SoporteTec();
            //soporteTec.setNumeroConsulta(i + 1);
            soporteTec.setDescripcion(faker.lorem().sentence());
            soporteTec.setCorreoSolicitante(faker.internet().emailAddress());
            soporteTec.setTipoIncidencia(faker.options().option("Hardware", "Software", "Red", "Otro"));
            soporteTecRepository.save(soporteTec);
        }

        // Generar usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            //usuario.setId(i + 1);
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasena(faker.internet().password());
            usuario.setRol(faker.options().option("ADMIN", "DOCENTE", "ESTUDIANTE"));
            usuarioRepository.save(usuario);
        }
    }
}