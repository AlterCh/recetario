package com.recetario.producto;

import com.recetario.controladores.CusControlador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/producto")
public class ProductoController extends CusControlador {

    @GetMapping("/nuevo")
    public String nuevo(HttpSession httpSession, ModelMap modelMap){

        return "producto/nuevo";
    }
    @GetMapping("/lista")
    public String lista(HttpSession httpSession, ModelMap modelMap){

        return "producto/lista";
    }
    @GetMapping("/editar")
    public String editar(HttpSession httpSession, ModelMap modelMap){

        return "producto/editar";
    }


}
