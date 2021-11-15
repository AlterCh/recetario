package com.recetario.usuario.controller;

import com.recetario.controladores.CusControlador;
import com.recetario.siu.Magnitud;
import com.recetario.siu.UnidadesServicio;
import com.recetario.usuario.domain.PreferenciasUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/preferencias")
public class PreferenciasController  extends CusControlador {

    @Autowired
    private UnidadesServicio unidadesServicio;

    @RequestMapping("")
    public String preferencias(ModelMap modelMap){
        try {
            modelMap.addAttribute("preferencias", new PreferenciasUsuario());
            modelMap.addAttribute("magnitudes", Arrays.asList(Magnitud.MASA, Magnitud.CORRIENTE_ELECTRICA));
            modelMap.addAttribute("unidadesServicio", unidadesServicio);
            return "panel/configuracion/preferencias";
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return "redirect:/panel";
    }



}
