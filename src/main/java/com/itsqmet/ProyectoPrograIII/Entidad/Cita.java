package com.itsqmet.ProyectoPrograIII.Entidad;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor


public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePaciente;
    private String pacienteCedula;
    private Date fechaHora;
    private String especialidad;

}
