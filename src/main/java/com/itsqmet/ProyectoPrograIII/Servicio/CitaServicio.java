package com.itsqmet.ProyectoPrograIII.Servicio;

import com.itsqmet.ProyectoPrograIII.Entidad.Cita;
import com.itsqmet.ProyectoPrograIII.Repositorio.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    public void guardarCita(Cita cita) {
        citaRepositorio.save(cita);
    }

    public List<Cita> obtenerCitasPorMedico(Long medicoId) {
        return citaRepositorio.findByMedicoId(medicoId);
    }
}