package com.itsqmet.ProyectoPrograIII.Controlador;

import com.itsqmet.ProyectoPrograIII.Entidad.Medico;
import com.itsqmet.ProyectoPrograIII.Repositorio.MedicoRepositorio;
import com.itsqmet.ProyectoPrograIII.Servicio.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedicoController {
    @Autowired
    private MedicoServicio medicoServicio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @GetMapping("/Medico/lista")
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoRepositorio.findAll());
        return "Medico/listaMedico";
    }
    @GetMapping("/medicos/editar/{id}")
    public String editarMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoRepositorio.findById(id).orElse(null);
        if (medico != null) {
            model.addAttribute("medico", medico);
            return "Medico/formularioMedico";
        } else {
            model.addAttribute("alerta", "Medico no encontrado.");
            return "redirect:/Medico/lista";
        }
    }
    @GetMapping("/Medico/formulario")
    public String mostrarFormularioMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "Medico/formularioMedico";
    }

    @PostMapping("/Medico/registrar")
    public String registrarMedico(@ModelAttribute Medico medico) {
        medicoRepositorio.save(medico);
        return "redirect:/Medico/lista";
    }
    @GetMapping("/medicos/{usuario}")
    public String mostrarMedicoPorUsuario(@PathVariable String usuario, Model model) {
        var medico = medicoServicio.buscarMedicoPorUsuario(usuario);
        if (!medico.isEmpty()) {
            model.addAttribute("medico", medico.get(0));
            return "Clinica/medicoHome";
        } else {
            model.addAttribute("alerta", "Usuario no encontrado.");
            return "Clinica/";
        }
    }
}
