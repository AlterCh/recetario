package com.recetario.controladores;

import org.springframework.web.bind.annotation.GetMapping;

public class PortalControlador {


    //TODO Esto es algo que se debe realizar/modificar/eliminar
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
