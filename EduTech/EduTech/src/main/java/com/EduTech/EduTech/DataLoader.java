package com.EduTech.EduTech;

import com.EduTech.EduTech.model.Curso;
import com.EduTech.EduTech.model.SoporteTec;
import com.EduTech.EduTech.repository.ContenidoEduRepository;
import com.EduTech.EduTech.model.ContenidoEdu;
import com.EduTech.EduTech.repository.CursoRepository;
import com.EduTech.EduTech.repository.SoporteTecRepository;
import com.EduTech.EduTech.repository.UsuarioRepository;
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

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 4; i++) {
            ContenidoEdu contenidoEdu = new ContenidoEdu();
            contenidoEdu.setId(i + 1);
            contenidoEdu.setActividad(faker.educator().course());
            contenidoEdu.setEvaluacion(faker.lorem().sentence());
            contenidoEdu.setProgresoEstudiante(faker.options().option("Iniciado", "En progreso", "Completado"));

            contenidoEduRepository.save(contenidoEdu);
        }
    }
//        List<ContenidoEdu> contenidoEdus = contenidoEduRepository.findAll();
//        // Generar cursos
//        for (int i = 0; i < 5; i++) {
//            Curso curso = new Curso();
//            curso.setId(i + 1);
//            curso.setNombreCurso(faker.book().genre());
//            curso.setDocente(faker.book().genre());
//            curso.setNumAlumnos(faker.number().numberBetween(1, 70));
//            cursoRepository.save(curso);
//        }
//
//        // Generar estudiantes
//        for (int i = 0; i < 50; i++) {
//            SoporteTec soporteTec = new SoporteTec();
//            soporteTec.setNumeroConsulta(i + 1);
//            soporteTec.setDescripcion(faker.idNumber().valid());
//            soporteTec.setCorreoSolicitante(faker.name().fullName());
//            soporteTec.setTipoIncidencia(faker.internet().emailAddress());
//            soporteTecRepository.save(soporteTec);
//        }
//        // Generar salas
//        for (int i = 0; i < 10; i++) {
//            Sala sala = new Sala();
//            sala.setCodigo(i + 1);
//            sala.setNombre(faker.university().name());
//            sala.setCapacidad(faker.number().numberBetween(10, 100));
//            sala.setIdInstituo(faker.number().randomDigit());
//            sala.setTipoSala(tipoSalaRepository.findAll().get(random.nextInt(3)));
//            salaRepository.save(sala);
//        }
//        List<Estudiante> estudiantes = estudianteRepository.findAll();
//        List<Sala> salas = salaRepository.findAll();
//        // Generar reservas
//        for (int i = 0; i < 20; i++) {
//            Reserva reserva = new Reserva();
//            reserva.setId(i + 1);
//            reserva.setEstudiante(estudiantes.get(random.nextInt(estudiantes.size())));
//            reserva.setSala(salas.get(random.nextInt(salas.size())));
//            reserva.setFechaSolicitada(new Date());
//            reserva.setHoraSolicitada(new Date());
//            reserva.setHoraCierre(new Date(System.currentTimeMillis() +
//                    faker.number().numberBetween(3600000, 7200000))); // 1-2 horas más
//            reserva.setEstado(faker.number().numberBetween(0, 2));
//            reservaRepository.save(reserva);
//        }
    }

