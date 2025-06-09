package com.itsqmet.ProyectoPrograIII.PacienteControlador;

import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import com.itsqmet.ProyectoPrograIII.Repositorio.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/formularioPaciente")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "Clinica/formularioC";
    }

    @PostMapping("/guardar")
    public String guardarPaciente(@Valid @ModelAttribute Paciente paciente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Clinica/formularioC";
        }
        pacienteRepository.save(paciente);
        return "redirect:/formularioC";
    }
}
