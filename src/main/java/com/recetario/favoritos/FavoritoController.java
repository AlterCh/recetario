package com.recetario.favoritos;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/favorito")
public class FavoritoController extends CusControlador {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private FavoritoRepository repo;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap model) {
        model.addAttribute("favorito", new Favorito());
        return "favorito/nuevo";
    }

    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
            ModelMap modelMap) {
        modelMap.addAttribute("favoritos", favoritoService.listarFavoritos());
        return "favorito/lista";
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
            ModelMap modelMap,
            @RequestParam String id) {
        try {
            if (id != null && repo.getById(id) != null) {
                modelMap.addAttribute("favoritos", repo.getById(id));
                return "favorito/editar";
            } else {
                throw new Exception("No se pudo editar lo solicitado, intente nuevamente.");
            }
        } catch (Exception e) {
            modelMap.put("error", e.getMessage());
            Logger.getLogger(FavoritoController.class.getName()).log(Level.SEVERE, null, e);
            return "redirect:/proveedor/lista";
        }
    }

    @GetMapping("/proveedor")
    public String proveedoresFavoritosGet(HttpSession httpSession, Model model) {
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        
        try {
            model.addAttribute("provfav", favoritoService.getAllByUsuarioP(usuario));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "favoritos/proveedorfav";
    }
    
    @GetMapping("/recetas")
    public String recetasFavoritasGet(HttpSession httpSession, Model model) {
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        
        try {
            model.addAttribute("recetasfav", favoritoService.getAllByUsuarioR(usuario));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "favoritos/recetafav";
    }

    @PostMapping("/nuevo")
    public String nuevoPost(@ModelAttribute Favorito favorito, HttpSession httpSession, ModelMap model) throws ErrorServicio {
        try {
            favoritoService.nuevoFavorito(favorito, httpSession, model);
            return "redirect:/favorito/lista";
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
            Logger.getLogger(FavoritoController.class.getName()).log(Level.SEVERE, null, e);
            return "favorito/nuevo";
        }
    }

    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute Favorito favoritos,
            @RequestParam("id") String id) {
        try {
            if (id != null) {
                favoritoService.borrar(repo.getById(id));
                return "redirect:/favorito/lista";
            } else {
                throw new ErrorServicio("No se eliminó lo registrado.");
            }
        } catch (ErrorServicio e) {
            modelMap.put("error", e.getMessage());
            Logger.getLogger(FavoritoController.class.getName()).log(Level.SEVERE, null, e);
            return "favorito/lista";
        }
    }

    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute("favorito") Favorito favorito) {
        try {
            favoritoService.modificar(favorito);
            return "redirect:/favorito/lista";
        } catch (Exception e) {
            Logger.getLogger(FavoritoController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "favorito/editar";
    }
}
