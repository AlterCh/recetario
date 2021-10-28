
package com.recetario.proveedores;

import com.recetario.controladores.CusControlador;
import com.recetario.producto.Producto;
import com.recetario.producto.ProductoRepository;
import com.recetario.producto.ProductoService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/proveedor")
public class ProveedorController extends CusControlador {

    @Autowired
    ProveedorService proveedorService;
    @Autowired
    ProveedorRepository proveedorRepository;
    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("proveedor",new Proveedor());
        return "proveedor/nuevo";
    }
    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
                           ModelMap modelMap){
        modelMap.addAttribute("proveedor", proveedorService.listarProveedores());
        return "proveedor/lista";
    }
    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap){
        modelMap.addAttribute("proveedor", proveedorService.modificar()); // Ver por que tira error
        return "proveedor/editar";
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
                            @ModelAttribute Proveedor proveedor){
        //TODO
        return "proveedor/nuevo";
    }
    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
                            ModelMap modelMap,
                            @ModelAttribute Proveedor proveedor){
        //TODO
        return "proveedor/lista";
    }
    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap modelMap,
                             @ModelAttribute Proveedor proveedor){
        //TODO
        return "proveedor/editar";
    }

}
