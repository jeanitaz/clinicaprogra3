package com.itsqmet.ProyectoPrograIII.Controlador;

import java.text.SimpleDateFormat;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itsqmet.ProyectoPrograIII.Entidad.Paciente;
import com.itsqmet.ProyectoPrograIII.Servicio.PacienteServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class PacienteController {
    @Autowired
    private PacienteServicio pacienteServicio;

    //Leer Pacientes
    @GetMapping("/pacientes")
    public String mostrarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteServicio.mostrarPacientes());
        return "Paciente/listaPaciente";
    }

    //Guardar Paciente
    @GetMapping("/formularioPaciente")
    public String formularioPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "Paciente/formularioC";
    }

    //Metodo para guardar Paciente
    @GetMapping("/guardarPaciente")
    public String crearPaciente(Paciente paciente) {
        pacienteServicio.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }

    //Actualizar Paciente
    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "Paciente/formularioC";
    }

    //Eliminar Paciente
   @GetMapping("/pacientes/eliminar/{id}")
   public String eliminarPaciente(@PathVariable Long id) {
       pacienteServicio.eliminarPaciente(id);
       return "redirect:/pacientes";
   }

    @PostMapping ("/pacientes/registrar")
    public String registrarPaciente(Paciente paciente) {
        pacienteServicio.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }
    @GetMapping("/pacientes/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "Paciente/formularioC";
    }

    //Validar si existe un paciente por cedula
    @PostMapping("/validar")
    public String validarPaciente(@RequestParam String cedula, Model model) {
        if (pacienteServicio.buscarPorCedula(cedula)) {
            return "redirect:/Clinica/pacienteHome";
        } else {
            model.addAttribute("alerta", "Cédula no válida o no encontrada.");
            return "Clinica/paciente";
        }
    }

    //Metodo para mostrar paciente por usuario
    @GetMapping("/pacientes/{usuario}")
    public String mostrarPacientePorUsuario(@PathVariable String usuario, Model model) {
        var paciente = pacienteServicio.buscarPacientePorNombre(usuario);
        if (!paciente.isEmpty()) {
            model.addAttribute("paciente", paciente.get(0));
            return "Clinica/pacienteHome";
        } else {
            model.addAttribute("alerta", "Usuario no encontrado.");
            return "Clinica/paciente";
        }
    }

    @GetMapping("/pacientes/pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=pacientes.pdf");
        List<Paciente> pacientes = pacienteServicio.mostrarPacientes();

        Document documento = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
        PdfWriter.getInstance(documento, response.getOutputStream());
        documento.open();

        // Título principal
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.WHITE);
        PdfPCell cellTitulo = new PdfPCell(new Phrase("Lista de Pacientes", fontTitulo));
        cellTitulo.setBackgroundColor(new BaseColor(33, 150, 243));
        cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTitulo.setBorder(Rectangle.NO_BORDER);
        cellTitulo.setPadding(16f);

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        tablaTitulo.addCell(cellTitulo);
        documento.add(tablaTitulo);

        // Subtítulo
        Font fontSub = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
        Paragraph sub = new Paragraph("Reporte generado automáticamente", fontSub);
        sub.setAlignment(Element.ALIGN_RIGHT);
        documento.add(sub);
        documento.add(new Paragraph(" "));

        // Tabla de datos
        PdfPTable tabla = new PdfPTable(new float[]{2,2,2,2,2,3});
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(10f);

        // Encabezados
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        String[] headers = {"Nombre", "Apellido", "Cédula", "Fecha Nacimiento", "Teléfono", "Email"};
        for (String col : headers) {
            PdfPCell header = new PdfPCell(new Phrase(col, fontHeader));
            header.setBackgroundColor(new BaseColor(33, 150, 243));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPadding(8f);
            tabla.addCell(header);
        }

        // Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Filas de datos
        boolean alternar = false;
        for (Paciente paciente : pacientes) {
            BaseColor bg = alternar ? new BaseColor(232, 245, 253) : BaseColor.WHITE;
            tabla.addCell(celdaDato(paciente.getNombre(), bg));
            tabla.addCell(celdaDato(paciente.getApellido(), bg));
            tabla.addCell(celdaDato(paciente.getCedula(), bg));
            tabla.addCell(celdaDato(
                    paciente.getFechaNacimiento() != null ? sdf.format(paciente.getFechaNacimiento()) : "", bg));
            tabla.addCell(celdaDato(paciente.getTelefono(), bg));
            tabla.addCell(celdaDato(paciente.getEmail(), bg));
            alternar = !alternar;
        }

        documento.add(tabla);
        documento.close();
    }

    // Metodo auxiliar para crear celdas de datos
    private PdfPCell celdaDato(String texto, BaseColor bg) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setBackgroundColor(bg);
        cell.setPadding(6f);
        return cell;
    }
}
