package com.recetario.controladores;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpSession;

/**
 *
 */

@PropertySource("classpath:values.properties")
public class CusControlador {

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
