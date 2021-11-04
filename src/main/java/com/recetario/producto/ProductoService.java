package com.recetario.producto;

import com.recetario.categoria.Categoria;
import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorRepository;
import com.recetario.siu.Unidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class ProductoService implements UserDetailsService{

    @Autowired
    ProductoRepository productoRepository;


    public List<Producto> getAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }
    
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void registrar(@NonNull Producto producto) throws Exception {

        String nombre = producto.getNombre();
        Double cantidad = producto.getCantidad();
        Unidad unidad = producto.getUnidad();
        Double stock = producto.getStock();
        List <Categoria> categoria = producto.getCategoria();
        
        validar(nombre, cantidad, unidad, stock);
        productoRepository.save(producto);

    }


    @Transactional
    public void modificar(@NonNull Producto producto) throws ErrorServicio {
       String nombre = producto.getNombre();
        Double cantidad = producto.getCantidad();
        Unidad unidad = producto.getUnidad();
        Double stock = producto.getStock();
        List <Categoria> categoria = producto.getCategoria();
        
        validar(nombre, cantidad, unidad, stock);

        Optional<Producto> respuesta = repo.findById(proveedor.getId());
        if (respuesta.isPresent()) {
            Proveedor aProveedor = respuesta.get();
            aProveedor.setNombre(nombre);
            aProveedor.setDireccion(direccion);
//            aProveedor.setProvincia(provincia);
            aProveedor.setTelefono(telefono);
            aProveedor.setProductos(proveedor.getProductos());
            repo.save(aProveedor);
        } else {
            throw new ErrorServicio("No se encontró el proveedor solicitado");
        }

    }
    
     @Transactional
    public List<Proveedor> listarProveedores() { //R
        return repo.findAll();
    }


    @Transactional
    public void borrar(@NonNull Proveedor proveedor) throws Exception {
       
         String id = proveedor.getId();
         Optional<Proveedor> respuesta = repo.findById(id);
            if (respuesta.isPresent()) {
                Proveedor prov = respuesta.get();
                repo.delete(prov);
            } else {
                throw new ErrorServicio("No se encontró el Proveedor solicitado.");
            }
    }

    private void validar(String nombre, Double cantidad, Unidad unidad, Double stock) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del Producto no puede ser nulo");
        }

        if (cantidad == null){
            throw new ErrorServicio ("La Cantidad no puede ser nula");
        }

        if (unidad == null) {
            throw new ErrorServicio("La unidad del producto no puede ser nula");
        }

        if (stock == null) {
            throw new ErrorServicio("El stock no puede ser nulo");
        }
    }
}




