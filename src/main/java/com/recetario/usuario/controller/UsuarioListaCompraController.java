package com.recetario.usuario.controller;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.UsuarioListaCompra;
import com.recetario.usuario.repository.UsuarioListaCompraRepository;
import com.recetario.usuario.service.UsuarioListaCompraService;
import com.recetario.usuario.service.UsuarioService;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/panel/listacompra")
public class UsuarioListaCompraController {

    UsuarioListaCompraRepository repo;
    UsuarioListaCompraService service;
    UsuarioService usuarioService;
    @Autowired
    public UsuarioListaCompraController(UsuarioListaCompraRepository repo, UsuarioListaCompraService service, UsuarioService usuarioService) {
        this.repo = repo;
        this.service = service;
        this.usuarioService = usuarioService;
    }


    @GetMapping
    @RequestMapping("/nuevo")
    public String nuevaListaCompra(HttpSession httpSession, ModelMap modelMap) {
        try {
            modelMap.addAttribute("listaCompra", new UsuarioListaCompra());
            return "listacompra/nuevo";
        } catch (Exception e) {
            modelMap.addAttribute("error", e.getMessage());
            return "listacompra/nuevo";
        }
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap,
                            @RequestParam String id) {

        try {
            if (id != null && repo.getById(id) != null) {
                modelMap.addAttribute("lista", repo.getById(id));
                return "listacompra/editar";
            } else {
                throw new Exception("No se ha podido encontrar el registro");
            }
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            return "redirect:/panel/listacompra/nuevo";
        }
    }

    @PostMapping("/nuevo")
    public String nuevoListaCompraPost(HttpSession httpSession, ModelMap modelMap, @ModelAttribute("listaCompra") UsuarioListaCompra listaCompra) {
        try {
            listaCompra.setUsuario((Usuario) httpSession.getAttribute("usuariosession"));
            Usuario aux = usuarioService.getUsuarioById((Usuario) httpSession.getAttribute("usuariosession"));
            aux.getListaCompra().add(listaCompra);
             usuarioService.modificar(aux);
            service.nuevaListaCompra(listaCompra);
            return "redirect:/panel";
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            return "listacompra/nuevo";
        }
    }

    @PostMapping("/editar")
    public String editarListaCompraPost(HttpSession httpSession, ModelMap modelMap,
                                        @ModelAttribute("listaCompra") UsuarioListaCompra listaCompra) {
        try {

            service.modificarListaCompra(listaCompra);
            return "redirect:/panel";
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            return "listacompra/nuevo";
        }
    }

}
