package com.itsqmet.ProyectoPrograIII.Repositorio;

import com.itsqmet.ProyectoPrograIII.Entidad.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidadContainingIgnoreCase(String especialidad);
    List<Medico> findByNombreContainingIgnoreCase(String nombre);
}
