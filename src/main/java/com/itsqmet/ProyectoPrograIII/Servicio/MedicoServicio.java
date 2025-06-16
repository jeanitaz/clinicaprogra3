package com.itsqmet.ProyectoPrograIII.Servicio;


import com.itsqmet.ProyectoPrograIII.Entidad.Medico;
import com.itsqmet.ProyectoPrograIII.Repositorio.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServicio {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    //Mostrar Medicos
    public List<Medico> mostrarMedicos() {
        return medicoRepositorio.findAll();
    }
    //Buscar Medico por Especialidad
    public List<Medico> buscarMedicoPorEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isEmpty()) {
            return medicoRepositorio.findAll();
        } else {
            return medicoRepositorio.findByEspecialidadContainingIgnoreCase(especialidad);
        }
    }
    //Buscar Medico por Nombre
    public List<Medico> buscarMedicoPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return medicoRepositorio.findAll();
        } else {
            return medicoRepositorio.findByNombreContainingIgnoreCase(nombre);
        }
    }
    //Buscar Medico por Usuario
    public List<Medico> buscarMedicoPorUsuario(String usuario) {
        if (usuario == null || usuario.isEmpty()) {
            return medicoRepositorio.findAll();
        } else {
            return medicoRepositorio.findByUsuarioContainingIgnoreCase(usuario);
        }
    }
}
