package com.recetario.proveedores;

import com.recetario.errores.ErrorServicio;
import java.util.List;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ProveedorService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ProveedorRepository repo;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void registrar(@NonNull Proveedor proveedor) throws Exception {

        String nombre = proveedor.getNombre();
        String direccion = proveedor.getDireccion();
//        Provincia provincia = proveedor.getProvincia();
        String telefono = proveedor.getTelefono();
        validar(nombre, direccion, telefono);
        repo.save(proveedor);

    }


    @Transactional
    public void modificar(@NonNull Proveedor proveedor) throws ErrorServicio {
        String nombre = proveedor.getNombre();
        String direccion = proveedor.getDireccion();
//        Provincia provincia = proveedor.getProvincia();
        String telefono = proveedor.getTelefono();
        validar(nombre, direccion, telefono);

        Optional<Proveedor> respuesta = repo.findById(proveedor.getId());
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
    public void borrar(HttpSession httpSession, @NonNull Proveedor proveedor) throws Exception {
       
         String id = proveedor.getId();
         Optional<Proveedor> respuesta = repo.findById(id);
         Usuario usuarioOptional = usuarioService.getUsuarioById((Usuario) httpSession.getAttribute("usuariosession"));
            if (respuesta.isPresent()) {
                Proveedor prov = respuesta.get();
                List<Proveedor> proveedorList = usuarioOptional.getListaProveedores();
                proveedorList.remove(prov);
                usuarioOptional.setListaProveedores(proveedorList);
                usuarioService.modificar(usuarioOptional);
                usuarioService.actualizarHttpSession(httpSession);
            } else {
                throw new ErrorServicio("No se encontró/borró el Proveedor solicitado.");
            }
    }

    private void validar(String nombre, String direccion, String telefono) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del Proveedor no puede ser nulo");
        }

        /*if (provincia == null || provincia.getId().isEmpty()) {
            throw new ErrorServicio ("La Provincia del Proveedor no puede ser nula");
        }*/

        if (direccion == null || direccion.isEmpty()) {
            throw new ErrorServicio("La direccion del Proveedor no puede ser nula");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo");
        }
    }

    public List<Proveedor> getAllByUsuario(Usuario usuario) {
        return repo.findProveedorByUsuario(usuario.getId());
    }

    public void nuevo(HttpSession httpSession,Proveedor proveedor) throws ErrorServicio {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        usuarioService.agregarProveedor(proveedor,usuario);
        usuarioService.actualizarHttpSession(httpSession);
    }
}


