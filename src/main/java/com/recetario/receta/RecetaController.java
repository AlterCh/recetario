package com.recetario.receta;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;

import javax.servlet.http.HttpSession;

import com.recetario.ingrediente.IngredienteService;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receta")
public class RecetaController extends CusControlador {

    private RecetaService recetaService;
    private UsuarioService usuarioService;
    private IngredienteService ingredienteService;


    @Autowired
    public RecetaController(RecetaService recetaService, UsuarioService usuarioService, IngredienteService ingredienteService) {
        this.recetaService = recetaService;
        this.usuarioService = usuarioService;
        this.ingredienteService = ingredienteService;
    }

    @GetMapping("/nuevo")
    public String nuevoGet(
            @RequestParam(value = "id_ingrediente",required = false) String id_ingrediente,
            HttpSession httpSession,
            ModelMap model) {
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
        Usuario u = (Usuario) httpSession.getAttribute("usuariosession");
        model.addAttribute("recetas", recetaService.listarRecetasPorUsuario(u));
        return "receta/lista";
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession, ModelMap model, Receta receta) {
        model.addAttribute("receta", receta);
        return "receta/editar";
    }

    @PostMapping("/nuevo")
    public String nuevoPost(HttpSession httpSession,
                            Model model,
                            @ModelAttribute("receta") Receta receta) {
        try {
            Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
            recetaService.registrar(usuario,receta);
            usuarioService.actualizarHttpSession(httpSession,usuario);
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
