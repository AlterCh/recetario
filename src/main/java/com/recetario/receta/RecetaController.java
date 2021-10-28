package com.recetario.receta;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receta")
public class RecetaController extends CusControlador {

    @Autowired
    private RecetaService recetaService;

    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap model) {
        model.addAttribute("receta", new Receta());
        return "receta/nuevo";
    }

    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession, ModelMap model) {
        model.addAttribute("receta", recetaService.listarRecetas());
        return "receta/lista";
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession, ModelMap model, Receta receta) {
        model.addAttribute("receta", receta); //Trae el Objeto a editar como atributo de Thymeleaf
        return "receta/editar";
    }

    @PostMapping("/nuevo")
    public String nuevoPost(Model model, @ModelAttribute("receta") Receta receta) {
        try {
            recetaService.registrar(receta);
            return "receta/nuevo";
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "receta/nuevo";
    }

    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession, ModelMap model, @ModelAttribute("receta") Receta receta) {
        try {
            recetaService.modificar(receta);
            return "receta/editar";
        } catch (ErrorServicio ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "receta/editar";
    }
}
