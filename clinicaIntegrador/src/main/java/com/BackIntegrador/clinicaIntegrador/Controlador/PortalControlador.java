package com.BackIntegrador.clinicaIntegrador.Controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {







    @RequestMapping("/")
    public String paginaInicio(){
        return "index";
    }





}
