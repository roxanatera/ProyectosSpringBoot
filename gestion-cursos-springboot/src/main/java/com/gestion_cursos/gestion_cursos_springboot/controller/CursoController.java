package com.gestion_cursos.gestion_cursos_springboot.controller;

import com.gestion_cursos.gestion_cursos_springboot.entity.Curso;
import com.gestion_cursos.gestion_cursos_springboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model) {
        Curso curso = new Curso();
        curso.setPublicado(true);
        model.addAttribute("curso", curso);
        model.addAttribute("pageTitle", "Nuevo curso");
        return "curso_form";
    }

    @PostMapping("/cursos/save")
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes) {
        try {
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message", "El curso se ha guardado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }
    @GetMapping("/cursos/edit/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Integer id, Model model) {
        Curso curso = cursoRepository.findById(id).orElse(null); // Asegúrate de manejar el caso null adecuadamente
        if (curso != null) {
            model.addAttribute("curso", curso);
            model.addAttribute("pageTitle", "Editar Curso: " + curso.getTitulo());
            return "curso_form";
        } else {
            // Redirecciona si el curso no existe
            return "redirect:/cursos";
        }
    }

    @GetMapping("/cursos/{id}")
    public String editarCurso(@PathVariable Integer id, Curso curso, RedirectAttributes redirectAttributes) {
        try {
            curso.setId(id);
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message", "Curso actualizado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "curso_form";
    }

    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            cursoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Curso eliminado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar el curso: " + e.getMessage());
        }
        return "redirect:/cursos";
    }
}
