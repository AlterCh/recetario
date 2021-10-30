package com.recetario.controladores;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController extends CusControlador implements org.springframework.boot.web.servlet.error.ErrorController {

    @Value("${error.message}")
    private String errorMsg;

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView renderErrorPage(HttpServletRequest httpServletRequest) {
        ModelAndView ep = new ModelAndView("error");
        int errorCode = getErrorCode(httpServletRequest);
        //https://www.restapitutorial.com/httpstatuscodes.html#
        //https://www.baeldung.com/custom-error-page-spring-mvc
        ep.addObject("codigo", errorCode);
        ep.addObject("mensaje", errorMsg);
        return ep;
    }

    private int getErrorCode(HttpServletRequest httpServletRequest) {

        return (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
    }
}
