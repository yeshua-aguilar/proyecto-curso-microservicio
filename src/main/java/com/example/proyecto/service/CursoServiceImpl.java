package com.example.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.dto.CursoDTO;
import com.example.proyecto.entity.Curso;
import com.example.proyecto.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public CursoDTO crearCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setCantidadLibro(cursoDTO.getCantidadLibro());
        curso.setCodigoLibro(cursoDTO.getCodigoLibro());
        cursoRepository.save(curso);
        cursoDTO.setId(curso.getId());
        return cursoDTO;
    }

    @Override
    public CursoDTO obtenerCursoPorId(int id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            return null;
        }
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setNombre(curso.getNombre());
        cursoDTO.setCantidadLibro(curso.getCodigoLibro());
        cursoDTO.setCodigoLibro(curso.getCodigoLibro());
        return cursoDTO;
    }

    @Override
    public List<CursoDTO> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(curso -> {
            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setId(curso.getId());
            cursoDTO.setNombre(curso.getNombre());
            cursoDTO.setCantidadLibro(curso.getCodigoLibro());
            cursoDTO.setCodigoLibro(curso.getCantidadLibro());
            return cursoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void eliminarCurso(int id) {
        cursoRepository.deleteById(id);
    }
}
