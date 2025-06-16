package com.itsqmet.ProyectoPrograIII.Entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Pattern(regexp = "\\d+")
    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Pattern(regexp = "\\d{10}")
    @NotBlank(message = "El teléfono debe tener 10 dígitos")
    private String telefono;

}