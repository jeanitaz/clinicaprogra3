package com.itsqmet.ProyectoPrograIII.Repositorio;

import com.itsqmet.ProyectoPrograIII.Entidad.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    List<Cita> findByEspecialidad(String especialidad);

    List<Cita> findByMedicoId(Long medicoId);
}
