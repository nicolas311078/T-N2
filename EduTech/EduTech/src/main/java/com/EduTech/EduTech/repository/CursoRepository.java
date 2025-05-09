package com.EduTech.EduTech.repository;

import com.EduTech.EduTech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository  extends JpaRepository<Curso,Long> {

}
