package com.example.proyecto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dto.CursoDTO;
import com.example.proyecto.response.CursosResponse;
import com.example.proyecto.response.CursosResponse2;
import com.example.proyecto.service.CursoService;

@RestController
@RequestMapping(value = "/cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursosResponse> crearCurso(@RequestBody CursoDTO cursoDTO) {
        CursoDTO cursoCreado = cursoService.crearCurso(cursoDTO);
        if (cursoCreado == null) {
        	return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CursosResponse("Se creo el curso corectamente", cursoCreado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerCursoPorId(@PathVariable int id) {
        CursoDTO curso = cursoService.obtenerCursoPorId(id);
        if (curso == null) {
            return new ResponseEntity<>(new CursosResponse("La consulta ha dado error", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CursosResponse("Busqueda a travez del id Exitosa", curso), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<CursosResponse2> obtenerTodosLosCursos() {
        List<CursoDTO> cursos = cursoService.obtenerTodosLosCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CursosResponse2("Busqueda de todos los cursos Exitosa", cursos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CursosResponse> eliminarCurso(@PathVariable int id) {
        boolean eliminado = cursoService.eliminarCurso(id);
        if (!eliminado) {
            return new ResponseEntity<>(new CursosResponse("Error al eliminar el curso", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CursosResponse("Curso eliminado exitosamente", null), HttpStatus.OK);
    }
}
