package com.recetario.usuario.controller;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.ListaDeCompra;
import com.recetario.usuario.repository.ListaDeCompraRepository;
import com.recetario.usuario.repository.UsuarioRepository;
import com.recetario.usuario.service.ListaDeCompraService;
import com.recetario.usuario.service.UsuarioService;

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

import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/listacompra")
public class ListaDeCompraController {

    ListaDeCompraRepository repo;
    ListaDeCompraService service;
    UsuarioService usuarioService;
    UsuarioRepository usuarioRepository;

    @Autowired
    public ListaDeCompraController(ListaDeCompraRepository repo, ListaDeCompraService service, UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.repo = repo;
        this.service = service;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    @RequestMapping("/nuevo")
    public String nuevaListaCompra(HttpSession httpSession, ModelMap modelMap) {
        try {
            modelMap.addAttribute("listaCompra", new ListaDeCompra());
            return "listacompra/nuevo";
        } catch (Exception e) {
            modelMap.addAttribute("error", e.getMessage());
            return "panel";
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
    public String nuevoListaCompraPost(HttpSession httpSession, ModelMap modelMap,
                                       @ModelAttribute("listaCompra") ListaDeCompra listaCompra) {
        try {
            Usuario aux = usuarioService
                    .getUsuario((Usuario) httpSession.getAttribute("usuariosession"));
            Set<ListaDeCompra> unaLista = aux.getListaCompra();
            unaLista.add(listaCompra);
            aux.setListaCompra(unaLista);
            usuarioRepository.save(aux);
            return "redirect:/panel";
        } catch (Exception ex) {
            ex.printStackTrace();
            modelMap.put("error", ex.getMessage());
            return "listacompra/nuevo";
        }
    }

    @PostMapping("/editar")
    public String editarListaCompraPost(HttpSession httpSession,
                                        ModelMap modelMap,
                                        @ModelAttribute("listaCompra") ListaDeCompra listaCompra) {
        try {

            service.modificarListaCompra(listaCompra);
            return "redirect:/panel";
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            return "listacompra/nuevo";
        }
    }

}
