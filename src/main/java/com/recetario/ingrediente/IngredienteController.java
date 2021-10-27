package com.recetario.ingrediente;

import com.recetario.controladores.CusControlador;
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
@RequestMapping("/ingrediente")
public class IngredienteController extends CusControlador {
    
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap model) {
        model.addAttribute("ingrediente", new Ingrediente());
        
        return "ingrediente/nuevo"; //Me crea un nuevo Ingrediente.
    }
    
    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession, ModelMap model) {
        model.addAttribute("ingredientes", ingredienteService.listarIngredientes());
        
        return "ingrediente/lista"; //Lista todos los Ingredientes que existen.
    }
    
    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap){
        //TODO
        return "ingrediente/editar";
    }
    
    @PostMapping("/nuevo")
    public String nuevoPost(Model model, @ModelAttribute("ingrediente") Ingrediente ingrediente) {
        try {
            ingredienteService.registrar(ingrediente);
            return "ingrediente/nuevo";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "/ingrediente";
        }
        
    }
    
    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap modelMap,
                             @ModelAttribute Ingrediente ingrediente){
        //TODO
        return "ingrediente/editar";
    }
    

}
