package com.EduTech.EduTech.repository;

import com.EduTech.EduTech.model.Usuario;
import com.EduTech.EduTech.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
}
