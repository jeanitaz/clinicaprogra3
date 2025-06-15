package com.itsqmet.ProyectoPrograIII.Servicio;

import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import com.itsqmet.ProyectoPrograIII.Repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServicio {
    @Autowired
    private PacienteRepository pacienteRepository;

    //Mostrar Pacientes
    public List<Paciente> mostrarPacientes() {
        return pacienteRepository.findAll();
    }

    //Buscar Paciente por Nombre
    public List<Paciente> buscarPacientePorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return pacienteRepository.findAll();
        } else {
            return pacienteRepository.findByNombre(nombre);
        }
    }

    //Guardar Paciente
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    //Buscar Paciente por Id
    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    //Eliminar Paciente
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    //Buscar Paciente por Cedula
    public boolean existePorCedula(String cedula) {
        return pacienteRepository.findByCedula(cedula).isPresent();
    }
}
