package com.recetario.controladores;

import com.recetario.Usuario.Usuario;
import com.recetario.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Controlador principal del portal.
 * Contiene:
 * Index
 * Registro de usuario
 * Logueo de usuario
 * TODO (completar)
 *
 */
@Controller
@RequestMapping("/")
public class PortalControlador extends CusControlador{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public String index() {
        return "index.html";
    }


    /**
     * USUARIO
     * Registro de Usuario
     * Logueo
     */
    @GetMapping("/login")
    public String ingresar(HttpSession httpSession,
                           Model model,
                           @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Usuario y/o contraseña incorrecto");
            httpSession.setAttribute("usuariosession", null);
        }
        String usuariosession = super.redirectUsuario(httpSession, "/panel");
        //Si el usuariosession es distinto de null ENTONCES devolver usuario session SINO devolver "login"
        return usuariosession != null ? usuariosession : "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute(
                "usuario",
                new Usuario() );
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(
            Model model,
            @ModelAttribute("usuario") Usuario usuario,
            @RequestParam("clave2") String clave2,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            usuarioService.registrar(
                    archivo,
                    usuario,
                    clave2
            );
            return "usuario/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }

}
