package com.itsqmet.ProyectoPrograIII.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesControler {
    @GetMapping("/cooming")
    public String mostrarCooming() {return "Pages/paciencia";}
    @GetMapping("/admin")
    public String mostrarAdmin() {return "Pages/admin";}
    @GetMapping("/administrador")
    public String mostrarAdministrador() {return "Pages/administrador";}
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
    @GetMapping("/formulario")
    public String mostrarFormularioM() {
        return "Medico/formularioMedico";
    }
    @GetMapping("/Clinica/medico")
    public String mostrarMedico() {
        return "Clinica/medico";
    }
    @GetMapping("/Clinica/medicoHome")
    public String mostrarmedicoHome() {
        return "Clinica/medicoHome";
    }
    @GetMapping("/Agendamiento")
    public String mostrarAgenda() {
        return "Pages/Agendar";
    }
    @GetMapping("/Perfil")
    public String mostrarPerfil() {
        return "Pages/Perfil";
    }
    @GetMapping("/Resultados")
    public String mostrarResultados() {
        return "Pages/Resultados";
    }
    @GetMapping("/Facturas")
    public String mostrarFacturas() {
        return "Pages/Facturas";
    }
    @GetMapping("/Historial")
    public String mostrarHistorial() {
        return "Pages/Historial";
    }
    @GetMapping("/Soporte")
    public String mostrarSoporte() {
        return "Pages/Soporte";
    }
    @GetMapping("/Citas")
    public String mostrarCitas() {
        return "Pages/Citas";
    }
}
