package com.recetario.producto;

import com.recetario.controladores.CusControlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/producto")
public class ProductoController extends CusControlador {

    @Autowired
    ProductoService productoService;
    @Autowired
    ProductoRepository productoRepository;
    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("producto",new Producto());
        return "producto/nuevo";
    }
    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("productos",productoService.getAll());
        return "producto/lista";
    }
    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap,
                            @RequestParam String id
                            ){

        System.out.println(productoRepository.findById(id));
        return "producto/editar";
    }


    /**
     * POST
     */
    /**
     *
     * @param httpSession
     * @param modelMap
     * @return
     */
    @PostMapping("/nuevo")
    public String nuevoPost(HttpSession httpSession,
                            ModelMap modelMap,
                            @ModelAttribute Producto producto){
        //TODO
        return "producto/nuevo";
    }
    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
                            ModelMap modelMap,
                            @ModelAttribute Producto producto){
        //TODO
        return "producto/lista";
    }
    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap modelMap,
                             @ModelAttribute Producto producto){
        //TODO
        return "producto/editar";
    }

}
