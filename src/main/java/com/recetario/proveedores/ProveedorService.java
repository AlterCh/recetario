package com.recetario.proveedores;

import Errores.ErrorServicio;
import com.recetario.producto.Producto;
import com.recetario.provincia.Provincia;
import com.recetario.usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void registrar(String id, String nombre, String direccion,Provincia provincia, String telefono, ArrayList<Producto> productos) throws ErrorServicio {

        validar(id, nombre, direccion,provincia, telefono);

        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setProvincia(provincia);
        proveedor.setTelefono(telefono);
        proveedor.setProductos(productos);
        
        repo.save(proveedor);

    }
    //Ver que forma es mas eficiente y no tiene errores
    @Transactional
    public void registrar2(@NonNull Proveedor proveedor,@NonNull String name) throws Exception {
        
            String id = proveedor.getId();
            String nombre = proveedor.getNombre();
            String direccion = proveedor.getDireccion();
            Provincia provincia = proveedor.getProvincia();
            String telefono = proveedor.getTelefono();
            
            validar(id, nombre, direccion, provincia, telefono);
        
            repo.save(proveedor);
            
    }

     @Transactional
    public void modificar(String id, String nombre, String direccion, Provincia provincia, String telefono, ArrayList<Producto> productos) throws ErrorServicio {

        validar(id, nombre, direccion, provincia, telefono);

        Optional<Proveedor> respuesta = repo.findById(id);
        if (respuesta.isPresent()) {

            Proveedor proveedor = respuesta.get();
            proveedor.setNombre(nombre);
            proveedor.setDireccion(direccion);
            proveedor.setProvincia(provincia);
            proveedor.setTelefono(telefono);
            proveedor.setProductos(productos);
            
            repo.save(proveedor);
        } else {

            throw new ErrorServicio("No se encontró el proveedor solicitado");
        }

    }
    
    private void validar(String id, String nombre, String direccion,Provincia provincia, String telefono) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del Proveedor no puede ser nulo");
        }

        if (direccion == null || direccion.isEmpty()) {
            throw new ErrorServicio("La direccion del Proveedor no puede ser nula");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo");
        }

    }

}


