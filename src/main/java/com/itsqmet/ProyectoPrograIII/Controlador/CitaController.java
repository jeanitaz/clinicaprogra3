package com.itsqmet.ProyectoPrograIII.Controlador;

import com.itsqmet.ProyectoPrograIII.Entidad.Cita;
import com.itsqmet.ProyectoPrograIII.Entidad.Medico;
import com.itsqmet.ProyectoPrograIII.Repositorio.CitaRepositorio;
import com.itsqmet.ProyectoPrograIII.Repositorio.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CitaController {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @PostMapping("/citas/guardar")
    public String guardarCita(
            @RequestParam String nombrePaciente,
            @RequestParam String pacienteCedula,
            @RequestParam String fecha,
            @RequestParam String hora,
            @RequestParam String especialidad
    ) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fechaHora = sdf.parse(fecha + " " + hora);

        Cita cita = new Cita();
        cita.setNombrePaciente(nombrePaciente);
        cita.setPacienteCedula(pacienteCedula);
        cita.setFechaHora(fechaHora);
        cita.setEspecialidad(especialidad);

        citaRepositorio.save(cita);
        return "redirect:/citas";
    }
    @GetMapping("/citas")
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaRepositorio.findAll());
        return "Pages/Agendar";
    }
}
