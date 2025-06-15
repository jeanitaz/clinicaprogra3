package com.itsqmet.ProyectoPrograIII.Repositorio;

import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombre(String nombre);
    Optional<Paciente> findByCedula(String cedula);

}
