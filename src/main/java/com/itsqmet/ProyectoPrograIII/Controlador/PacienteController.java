package com.itsqmet.ProyectoPrograIII.Controlador;


import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import com.itsqmet.ProyectoPrograIII.Servicio.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PacienteController {
    @Autowired
    private PacienteServicio pacienteServicio;

    //
    @GetMapping("/pacientes")
    public String mostrarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteServicio.mostrarPacientes());
        return "Paciente/listaPaciente";
    }

    //Guardar Paciente
    @GetMapping("/formularioPaciente")
    public String formularioPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "Paciente/formularioC";
    }

    //Metodo para guardar Paciente
    @GetMapping("/guardarPaciente")
    public String crearPaciente(Paciente paciente) {
        pacienteServicio.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }

    //Actualizar Paciente
    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "Paciente/formularioC";
    }

    //Eliminar Paciente
   @GetMapping("/pacientes/eliminar/{id}")
   public String eliminarPaciente(@PathVariable Long id) {
       pacienteServicio.eliminarPaciente(id);
       return "redirect:/pacientes";
   }

    @PostMapping ("/pacientes/registrar")
    public String registrarPaciente(Paciente paciente) {
        pacienteServicio.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }
    @GetMapping("/pacientes/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "Paciente/formularioC";
    }

    //Validar si existe un paciente por cedula
    @PostMapping("/validar")
    public String validarPaciente(@RequestParam String cedula, Model model) {
        if (pacienteServicio.existePorCedula(cedula)) {
            return "redirect:/Clinica/pacienteHome";
        } else {
            model.addAttribute("alerta", "Cédula no válida o no encontrada.");
            return "Clinica/paciente";
        }
    }
}
