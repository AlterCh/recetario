package com.recetario.favoritos;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.receta.Receta;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
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
    private UsuarioService usuarioService;


    @GetMapping("/proveedor")
    public String proveedoresFavoritosGet(HttpSession httpSession, Model model) {
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        try {
            List<Proveedor> proveedorList = new ArrayList<>();
            usuario.getListaProveedores().forEach(proveedor -> {
                if (proveedor.getFavorito()) {
                    proveedorList.add(proveedor);
                }
            });
            model.addAttribute("proveedorFav", proveedorList);
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
            List<Receta> recetaList = new ArrayList<>();
            usuario.getListaRecetas().forEach(receta -> {
                if (receta.getFavorito()) {
                    recetaList.add(receta);
                }
            });
            model.addAttribute("recetasfav", recetaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "favoritos/recetafav";
    }

    @PostMapping("/nuevo")
    public String nuevoPost(@RequestParam("id") String id,
                            @RequestParam("tipo") String tipo,
                            HttpSession httpSession,
                            ModelMap model) throws ErrorServicio {
        try {
            Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
            favoritoService.setFavorito(usuario, id, tipo);
            usuarioService.actualizarHttpSession(httpSession, usuario);
            if (tipo.equals("proveedor")) {
                return "redirect:/proveedor/lista";
            } else if (tipo.equals("receta")) {
                return "redirect:/receta/lista";
            } else {
                return "redirect:/";
            }
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
            Logger.getLogger(FavoritoController.class.getName()).log(Level.SEVERE, null, e);
            return "favorito/nuevo";
        }
    }
}
