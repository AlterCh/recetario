package com.recetario.categoria;

import com.recetario.producto.Producto;
import com.recetario.producto.ProductoRepository;
import com.recetario.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaRepository categoriaRepository;

    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("categorias",new Categoria());
        return "categoria/nuevo";
    }
    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("categorias",categoriaService.getAll());

        return "categoria/lista";
    }
    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap){
        //TODO
        return "categoria/editar";
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
        return "categoria/nuevo";
    }
    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
                            ModelMap modelMap,
                            @ModelAttribute Producto producto){
        //TODO
        return "categoria/lista";
    }
    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap modelMap,
                             @ModelAttribute Producto producto){
        //TODO
        return "categoria/editar";
    }
}
