package com.itsqmet.ProyectoPrograIII.Controlador;

import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itsqmet.ProyectoPrograIII.Entidad.Medico;
import com.itsqmet.ProyectoPrograIII.Repositorio.MedicoRepositorio;
import com.itsqmet.ProyectoPrograIII.Servicio.MedicoServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class MedicoController {
    @Autowired
    private MedicoServicio medicoServicio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;


    @GetMapping("/Medico/lista")
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoRepositorio.findAll());
        return "Medico/listaMedico";
    }
    @GetMapping("/medicos/editar/{id}")
    public String editarMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoRepositorio.findById(id).orElse(null);
        if (medico != null) {
            model.addAttribute("medico", medico);
            return "Medico/formularioMedico";
        } else {
            model.addAttribute("alerta", "Medico no encontrado.");
            return "redirect:/Medico/lista";
        }
    }
    @GetMapping("/Medico/formulario")
    public String mostrarFormularioMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "Medico/formularioMedico";
    }

    @PostMapping("/Medico/registrar")
    public String registrarMedico(@ModelAttribute Medico medico) {
        medicoRepositorio.save(medico);
        return "redirect:/Medico/lista";
    }
    @GetMapping("/medicos/{usuario}")
    public String mostrarMedicoPorUsuario(@PathVariable String usuario, Model model) {
        var medico = medicoServicio.buscarMedicoPorUsuario(usuario);
        if (!medico.isEmpty()) {
            model.addAttribute("medico", medico.get(0));
            return "Clinica/medicoHome";
        } else {
            model.addAttribute("alerta", "Usuario no encontrado.");
            return "Clinica/medico";
        }
    }
    @GetMapping(" /medicos/{usuario}")
    public String mostrarMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoRepositorio.findById(id).orElse(null);
        if (medico != null) {
            model.addAttribute("medico", medico);
            return "Medico/detalleMedico";
        } else {
            model.addAttribute("alerta", "Médico no encontrado.");
            return "redirect:/Medico/lista";
        }
    }

    @GetMapping("/medicos/pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=medicos.pdf");
        List<Medico> medicos = medicoServicio.mostrarMedicos();

        Document documento = new Document(PageSize.A4, 40, 40, 60, 50);
        PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());
        documento.open();

        // Título con ícono y fuente grande
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26, new BaseColor(33, 76, 180));
        Paragraph titulo = new Paragraph("👨‍⚕️ Lista de Médicos", fontTitulo);
        titulo.setAlignment(Element.ALIGN_LEFT);
        titulo.setSpacingAfter(12f);
        documento.add(titulo);

        // Línea decorativa azul
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(new BaseColor(33, 76, 180));
        documento.add(new Chunk(ls));

        // Tabla de datos
        PdfPTable tabla = new PdfPTable(new float[]{1, 2, 2, 2});
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(18f);

        // Encabezados
        String[] headers = {"🆔 ID", "👤 Usuario", "📝 Nombre", "🏥 Especialidad"};
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.WHITE);
        BaseColor[] gradiente = {new BaseColor(63, 81, 181), new BaseColor(123, 31, 162)};
        for (int i = 0; i < headers.length; i++) {
            PdfPCell header = new PdfPCell(new Phrase(headers[i].toUpperCase(), fontHeader));
            header.setBackgroundColor(gradiente[i % gradiente.length]);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPaddingTop(14f);
            header.setPaddingBottom(14f);
            header.setBorderWidth(0.8f);
            header.setBorderColor(new BaseColor(200, 200, 255));
            header.setCellEvent(new RoundedCell());
            tabla.addCell(header);
        }

        // Filas de datos con alternancia de color
        boolean alternar = false;
        for (Medico medico : medicos) {
            BaseColor bg = alternar ? new BaseColor(237, 231, 246) : BaseColor.WHITE;
            tabla.addCell(celdaDato(String.valueOf(medico.getId()), bg));
            tabla.addCell(celdaDato(medico.getUsuario(), bg));
            tabla.addCell(celdaDato(medico.getNombre(), bg));
            tabla.addCell(celdaDato(medico.getEspecialidad(), bg));
            alternar = !alternar;
        }

        // Pie de página
        Font fontPie = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY);
        Paragraph pie = new Paragraph("Generado el: " + new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()) + " | Hospital Nova Salud", fontPie);
        pie.setAlignment(Element.ALIGN_RIGHT);
        pie.setSpacingBefore(20f);
        documento.add(tabla);
        documento.add(pie);

        documento.close();
    }

    // Clase para bordes redondeados en celdas
    private static class RoundedCell implements PdfPCellEvent {
        @Override
        public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
            PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
            cb.roundRectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight(), 8);
            cb.stroke();
        }
    }

    // Método auxiliar para celdas de datos
    private PdfPCell celdaDato(String texto, BaseColor bg) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setBackgroundColor(bg);
        cell.setPadding(8f);
        cell.setBorderWidth(0.5f);
        cell.setBorderColor(new BaseColor(220, 220, 240));
        cell.setCellEvent(new RoundedCell());
        return cell;
    }
}
