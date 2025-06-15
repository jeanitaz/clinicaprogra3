package com.itsqmet.ProyectoPrograIII.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesControler {

    @GetMapping("/home")
    public String mostrarHome() {
        return "paciente";
    }
    @GetMapping("/index")
    public String mostrarIndex(){
        return "index";
    }

    @GetMapping("/nosotros")
    public String mostrarNosotros(){
        return "Clinica/Nosotros";
    }

    @GetMapping("/contacto")
    public String mostrarContacto(){
        return "Clinica/contacto";
    }

    @GetMapping("/formularioC")
    public String mostrarFormulario() {
        return "Paciente/formularioC";
    }
    @GetMapping("/Clinica/paciente")
    public String mostrarPrueba() {
        return "/Clinica/paciente";
    }
    @GetMapping("/Clinica/pacienteHome")
    public String mostrarPacienteHome() {
        return "Clinica/pacienteHome";
    }
    @GetMapping("/Clinica/medico")
    public String mostrarMedico() {
        return "Clinica/medico";
    }
}
