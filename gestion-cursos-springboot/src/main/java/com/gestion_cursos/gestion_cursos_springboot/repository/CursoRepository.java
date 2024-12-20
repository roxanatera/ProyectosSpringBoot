package com.gestion_cursos.gestion_cursos_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion_cursos.gestion_cursos_springboot.entity.Curso;
@Repository
public interface CursoRepository  extends JpaRepository<Curso, Integer> {

}
