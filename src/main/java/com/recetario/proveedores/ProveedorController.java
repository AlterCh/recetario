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

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController extends CusControlador {

    ProveedorService proveedorService;
    ProveedorRepository proveedorRepository;
    UsuarioService usuarioService;
    ErrorServicio ex;

    @Autowired
    public ProveedorController(ProveedorService proveedorService, ProveedorRepository proveedorRepository, UsuarioService usuarioService) {
        this.proveedorService = proveedorService;
        this.proveedorRepository = proveedorRepository;
        this.usuarioService = usuarioService;
    }

    /**
     * GET
     */
    @GetMapping("/nuevo")
    public String nuevoGet(HttpSession httpSession, ModelMap modelMap) {
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
                            @RequestParam String id) {

        try {
            if (id != null && proveedorRepository.getById(id) != null) {
                modelMap.addAttribute("proveedor", proveedorRepository.getById(id));
                return "proveedor/editar";
            } else {
                throw new Exception("No se ha podido encontrar el registro");
            }
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/proveedor/lista";
        }
    }

    /**
     * POST
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
            proveedorService.nuevo(httpSession,
                    proveedor);
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
                            @ModelAttribute Proveedor proveedor,
                            @RequestParam("id") String id) {

        try {
            if (id != null) {
                proveedorService.borrar(httpSession,proveedorRepository.getById(id));
                return "redirect:/proveedor/lista";
            } else {
                throw new Exception("No se ha eliminado el registro, disculpe las molestias");
            }
        } catch (Exception ex) {
            modelMap.put("error", ex.getMessage());
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            return "proveedor/lista";
        }
    }


    @PostMapping("/editar")
    public String editarPost(HttpSession httpSession,
                             ModelMap modelMap,
                             @ModelAttribute("proveedor") Proveedor proveedor) {
        try {
            proveedorService.modificar(proveedor);
            return "redirect:/proveedor/lista";
        } catch (ErrorServicio ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "proveedor/editar";
    }


}
