package com.recetario.controladores;

import com.recetario.errores.ErrorServicio;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Objects;


//TODO Esto es algo que se debe realizar/modificar/eliminar


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
        return "index";
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
        model.addAttribute(
                "usuario",
                new Usuario() );
        //Si el usuariosession es distinto de null ENTONCES devolver usuario session SINO devolver "login"
        return usuariosession != null ? usuariosession : "login";
    }

    @GetMapping("/registro")
    public String registro(HttpSession httpSession,
                           Model model,
                           @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Usuario y/o contraseña incorrecto");
            httpSession.setAttribute("usuariosession", null);
        }
        String usuariosession = super.redirectUsuario(httpSession, "/panel");
        model.addAttribute(
                "usuario",
                new Usuario() );
        //Si el usuariosession es distinto de null ENTONCES devolver usuario session SINO devolver "login"
        return usuariosession != null ? usuariosession : "registro";
    }
    @PostMapping("/registro")
    public String registro(
            Model model,
            @ModelAttribute("usuario") Usuario usuario,
            @RequestParam("clave2") String clave2,
            @RequestParam("archivo") MultipartFile archivo) {

        try {
            System.out.println(usuario.getClave() + "-> "+ clave2);
            if(!Objects.equals(usuario.getClave(), clave2)){
                throw new ErrorServicio("Clave incorrecta");
            }
            usuarioService.registrar(
                    archivo,
                    usuario
            );
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

}
