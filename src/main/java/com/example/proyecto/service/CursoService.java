package com.example.proyecto.service;

import java.util.List;

import com.example.proyecto.dto.CursoDTO;

public interface CursoService {
    CursoDTO crearCurso(CursoDTO cursoDTO);
    CursoDTO obtenerCursoPorId(int id);
    List<CursoDTO> obtenerTodosLosCursos();
    boolean eliminarCurso(int id);
}
