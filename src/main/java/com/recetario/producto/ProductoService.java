package com.recetario.producto;

import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorService;
import com.recetario.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository repo;
    @Autowired
    ProveedorService proveedorService;

    public List<Producto> getAll() {
        List<Producto> productos = repo.findAll();
        return productos;
    }

    public void registrar(HttpSession httpSession,String idProveedor, Producto producto) throws ErrorServicio {
        try {
            proveedorService.agregarProducto(httpSession,idProveedor, producto);
        }catch (Exception e){
            throw new ErrorServicio("Error al registrar producto");
        }
    }

    public void borrar(Producto producto) throws ErrorServicio {
        try{
            repo.delete(producto);
        }catch (Exception exception){
            throw new ErrorServicio("No se ha podido eliminar su producto");
        }
    }

    public void modificar(Producto producto) throws ErrorServicio {
        try{
            Optional<Producto> aux = repo.findById(producto.getId());
            if(aux.isPresent()){
                Producto mProducto = producto;
                repo.save(mProducto);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new ErrorServicio("No se ha podido modificar su producto");
        }
    }
}
