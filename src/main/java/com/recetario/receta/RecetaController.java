package com.recetario.receta;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;

import javax.servlet.http.HttpSession;

import com.recetario.ingrediente.IngredienteService;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorController;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

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
            HttpSession httpSession,
            ModelMap model) {
        model.addAttribute("receta", new Receta());
        return "receta/nuevo";

    }

    @GetMapping("/ingrediente")
    public String nuevoIngrediente(
            HttpSession httpSession,
            ModelMap modelMap) {

        return "receta/RecetaIngrediente";
    }

    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession, ModelMap model) {
        Usuario u = (Usuario) httpSession.getAttribute("usuariosession");
        model.addAttribute("recetas", recetaService.getAllByUsuario(u));
        return "receta/lista";
    }

    private void printTestUsuario(Usuario u) {
        System.out.println(":: LISTA DE RECETAS USUARIO => "+ u.getNombre()+ " ::");
        u.getListaRecetas().forEach(System.out::println);
        System.out.println(":: LISTA DE RECETAS USUARIO => "+ u.getNombre()+ " ::");
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession, ModelMap model,@RequestParam("id") String id) {
        model.addAttribute("receta", recetaService.getById(id));
        return "receta/editar";
    }

    @PostMapping("/nuevo")
    public String nuevoPost(HttpSession httpSession,
                            Model model,
                            @ModelAttribute("receta") Receta receta) {
        try {
            Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
            recetaService.registrar(usuario, receta);
            usuarioService.actualizarHttpSession(httpSession, usuario);
            return "redirect:/receta/lista";
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
            return "receta/nuevo";
        }

    }

    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
                            ModelMap modelMap,
                            @RequestParam("id") String id) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");

        try {
            if (id != null) {
                recetaService.borrar(usuario, recetaService.getById(id));
                usuarioService.actualizarHttpSession(httpSession, usuario);
                return "redirect:/receta/lista";
            } else {
                throw new Exception("No se ha eliminado el registro, disculpe las molestias");
            }
        } catch (Exception ex) {
            modelMap.addAttribute("recetas", recetaService.getAllByUsuario(usuario));
            modelMap.put("error", ex.getMessage());
            ex.printStackTrace();
            return "receta/lista";
        }
    }


    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap model,
                             @ModelAttribute("receta") Receta receta) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        try {
            recetaService.modificar(usuario,receta);
            usuarioService.actualizarHttpSession(httpSession,usuario);
            return "receta/editar";
        } catch (ErrorServicio ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
        }
        return "receta/editar";
    }
}
