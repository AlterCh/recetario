package com.recetario.proveedores;

import Errores.ErrorServicio;
import com.recetario.producto.Producto;
import java.util.ArrayList;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ProveedorRepository repo;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void registrar(String id, String nombre, String direccion, String telefono, ArrayList<Producto> productos) throws ErrorServicio {

        validar(id, nombre, direccion, telefono, productos);

        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setProductos(productos);

    }

     @Transactional
    public void modificar(String id, String nombre, String direccion, String telefono, ArrayList<Producto> productos) throws ErrorServicio {

        validar(id, nombre, direccion, telefono, productos);

        Optional<Proveedor> respuesta = repo.findById(id);
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();
            proveedor.setNombre(nombre);
            proveedor.setDireccion(direccion);
            proveedor.setTelefono(telefono);
            proveedor.setProductos(productos);
            
            repo.save(proveedor);
        } else {

            throw new ErrorServicio("No se encontró el proveedor solicitado");
        }

    }
    
    private void validar(String id, String nombre, String direccion, String telefono, ArrayList<Producto> productos) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del Proveedor no puede ser nulo");
        }

        if (direccion == null || direccion.isEmpty()) {
            throw new ErrorServicio("La direccion del Proveedor no puede ser nula");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }

    }

}


