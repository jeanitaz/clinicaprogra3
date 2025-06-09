package com.itsqmet.ProyectoPrograIII.Controlador;



import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClinicaHControlador {
    @GetMapping("/index")
    public String mostrarHome(){
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
    public String mostrarFormulario() {return "Clinica/formularioC";
    }
}
