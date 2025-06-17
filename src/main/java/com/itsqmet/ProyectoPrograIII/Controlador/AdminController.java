package com.itsqmet.ProyectoPrograIII.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @PostMapping("/admin/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "redirect:/admin";
    }
}
