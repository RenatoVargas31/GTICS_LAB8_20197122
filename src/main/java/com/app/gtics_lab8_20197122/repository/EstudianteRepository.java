package com.app.gtics_lab8_20197122.repository;

import com.app.gtics_lab8_20197122.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    //Listar Estudiantes ordenados por GPA
    @Query("SELECT e FROM Estudiante e ORDER BY e.gpa DESC")
    List<Estudiante> listarPorGPA();

    //Listar Estudiantes por facultad ordenados por GPA
    @Query("SELECT e FROM Estudiante e WHERE e.facultad = ?1 ORDER BY e.gpa DESC")
    List<Estudiante> listarPorFacultad(String facultad);
}
