package com.recetario.usuario.controller;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.ProveedorService;
import com.recetario.receta.RecetaService;
import com.recetario.usuario.repository.UsuarioRepository;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioListaCompraService;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/panel")
public class UsuarioController extends CusControlador {

    UsuarioService usuarioService;
    UsuarioRepository usuarioRepository;
    ProveedorService proveedorService;
    RecetaService recetaService;
    UsuarioListaCompraService listaService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, ProveedorService proveedorService, RecetaService recetaService, UsuarioListaCompraService usuarioListaCompraService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.proveedorService = proveedorService;
        this.recetaService = recetaService;
        this.listaService = usuarioListaCompraService;
    }



    @GetMapping("")
    public String panel(HttpSession httpSession,Model model) {
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }

        try {
            model.addAttribute("listaCompra",listaService.getListaCompra((Usuario) httpSession.getAttribute("usuariosession")));
        } catch (ErrorServicio e) {
            e.printStackTrace();
        }

        return "panel/inicio";
    }


    @GetMapping("/perfil")
    public String editar(HttpSession httpSession, Model model) {
        //Check login
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");

        model.addAttribute("usuario", usuario);
        try {

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "panel/perfil";
    }

    @PostMapping("/perfil")
    public String actualizar(
            HttpSession httpSession,
            Model model,
            @ModelAttribute("usuario") Usuario usuario,
            @RequestParam("clave2") String clave2,
            @RequestParam("archivo") MultipartFile archivo) {
        //Check login
        String x = checkUsuario(httpSession);
        if (x != null) {
            return x;
        }

        //TODO falta verificar si las contraseñas son ingresadas para cambiar la clave o no
        Usuario aux = usuarioRepository.getById(((Usuario) httpSession.getAttribute("usuariosession")).getId());
        usuario.setId(aux.getId());
        usuario.setAlta(aux.getAlta());
        if (archivo == null) {
            usuario.setFoto(aux.getFoto());
        }
        try {
            //Modifico el usuario
            usuarioService.modificar(archivo, usuario);
            //Guardo el usuario modificado en la cache
            httpSession.setAttribute("usuariosession", usuario);
            //Cuando se usa redirect se debe ingresar la direccion url
            //a la que se desea redirigir.
            return "redirect:/panel";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "panel/configuracion/perfil";
        }
    }



}
