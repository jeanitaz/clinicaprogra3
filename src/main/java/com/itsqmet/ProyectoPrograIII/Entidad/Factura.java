package com.itsqmet.ProyectoPrograIII.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private String estado;

    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}