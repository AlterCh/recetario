package com.recetario.producto;

import com.recetario.controladores.CusControlador;
import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorRepository;
import com.recetario.proveedores.ProveedorService;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    ErrorServicio ex;
    
    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap modelMap) {
        //atributo proveedor con un objeto vacio
        //el objeto vacio trae todos los atributos en null

        modelMap.addAttribute("producto", new Producto());
        return "producto/nuevo";
    }

    @GetMapping("/lista")
    public String listaGet(HttpSession httpSession,
            ModelMap modelMap) {
        modelMap.addAttribute("productos", productoService.getAll());
        return "producto/lista";
    }

    @GetMapping("/editar")
    public String editarGet(HttpSession httpSession,
                            ModelMap modelMap,
                            @RequestParam String id) {

        try {
            if (id != null && productoRepository.getById(id) != null) {
                modelMap.addAttribute("producto", productoRepository.getById(id));
             return "producto/editar";
            }else{
                throw new Exception("No se ha podido encontrar el registro");
            }
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/producto/lista";
        }
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
            @ModelAttribute Producto producto) throws Exception {
        try {
            productoService.registrar(producto);
             return "redirect:/proveedor/lista";
        } catch (ErrorServicio ex) {
            modelMap.put("error", ex.getMessage());
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return "producto/nuevo";
        }
        
       
    }

    @PostMapping("/lista")
    public String listaPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute Proveedor proveedor,
            @RequestParam("id") String id) {

            try {
                if (id != null) {
                productoService.borrar(productoRepository.getById(id));
                return "redirect:/producto/lista";
                }else{
                    throw new Exception("No se ha eliminado el registro, disculpe las molestias");
                }
            } catch (Exception ex) {
                modelMap.put("error", ex.getMessage());
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                return "producto/lista";
            }
        }



    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
            ModelMap modelMap,
            @ModelAttribute("producto") Producto producto) {
        try {
            productoService.modificar(producto);
            return "redirect:/producto/lista";
        } catch (ErrorServicio ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "producto/editar";
    }
    

}

