package com.recetario.controladores;

import com.recetario.ingrediente.Ingrediente;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@PropertySource("classpath:values.properties")
public class CusControlador {
    protected static List<Ingrediente> ingredienteList = new ArrayList<>();
    /**
     * Verifica que el usuario se enuentre logueado.
     * de no existir retorna null
     * sino retorna el rootURL "/"
     * <p>
     * Utilización básica:
     * Caso 1:
     *      String x = usuariosession(httpSession);
     *      if (x != null) return x; // x= "/"
     * Caso 2:
     *      if (usuariosession(httpSession) != null) throw new Exception("Error al obtener el usuario: usuario no logueado");
     *
     * @return "/" || null
     */
    protected String checkUsuario(HttpSession httpSession) {
        if (httpSession.getAttribute("usuariosession") == null) {
            return "/";
        }
        return null;
    }

    /**
     * Verifica que el usuario se enuentre logueado.
     * de no existir retorna null
     * sino retorna la url especificada
     *
     * @param url : "/" || "/login"
     */
    protected String redirectUsuario(HttpSession httpSession, String url) {
        if (httpSession.getAttribute("usuariosession") != null) {
            return "redirect:" + url;
        }
        return null;
    }
}
