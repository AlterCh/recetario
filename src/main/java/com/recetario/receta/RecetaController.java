package com.recetario.receta;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import com.recetario.ingrediente.Ingrediente;
import com.recetario.ingrediente.IngredienteService;
import com.recetario.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receta")
public class RecetaController extends CusControlador {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IngredienteService ingredienteService;




    @GetMapping("/nuevo") //TODO
    public String nuevoGet(
            @RequestParam(value = "id_ingrediente",required = false) String id_ingrediente,
            HttpSession httpSession,
            ModelMap model) {
        //lista ingredientes
        //nueva receta
        //lista de ingredientes agregados
        try{
            if(id_ingrediente != null){
                ingredienteList.add(ingredienteService.getIngrediente(id_ingrediente));
            }
        } catch (ErrorServicio errorServicio) {
            errorServicio.printStackTrace();
            model.addAttribute("error", errorServicio.getMessage());
        }
        model.addAttribute("ingredientes_agregados",ingredienteList);
        model.addAttribute("receta", new Receta());
        return "receta/nuevo";
    }
    @GetMapping("/ingrediente")
    public String nuevoIngrediente(
            HttpSession httpSession,
            ModelMap modelMap){

        return "receta/RecetaIngrediente";
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
