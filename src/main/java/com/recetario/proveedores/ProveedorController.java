package com.recetario.proveedores;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import com.recetario.producto.Producto;
import com.recetario.producto.ProductoRepository;
import com.recetario.producto.ProductoService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    ErrorServicio ex;
    
    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap modelMap) {
        //atributo proveedor con un objeto vacio
        //el objeto vacio trae todos los atributos en null

        modelMap.addAttribute("proveedor", new Proveedor());
        return "proveedor/nuevo";
    }

    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
            ModelMap modelMap) {
        modelMap.addAttribute("proveedores", proveedorService.listarProveedores());
        return "proveedor/lista";
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
            ModelMap modelMap,
            Proveedor proveedor) {
        modelMap.addAttribute("proveedor", proveedor);
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
            @ModelAttribute Proveedor proveedor) throws Exception {
        try {
            proveedorService.registrar(proveedor);
             return "redirect:/proveedor/lista";
        } catch (ErrorServicio ex) {
            modelMap.put("error", ex.getMessage());
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            return "proveedor/nuevo";
        }
        
       
    }

    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute Proveedor proveedor) {
        
            proveedorService.listarProveedores() ;
       
        return "proveedor/lista";
    }

    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute("proveedor") Proveedor proveedor) {
        try {
            proveedorService.modificar(proveedor);
            return "proveedor/editar";
        } catch (ErrorServicio ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "proveedor/editar";
    }
    

}
