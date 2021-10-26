package com.recetario.controladores;

import org.springframework.web.bind.annotation.GetMapping;

public class PortalControlador {

    //Holiwi UwU
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
