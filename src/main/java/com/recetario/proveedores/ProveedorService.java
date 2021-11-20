package com.recetario.proveedores;

import com.recetario.errores.ErrorServicio;

import java.util.List;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.recetario.producto.Producto;
import com.recetario.producto.ProductoService;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ProveedorRepository repo;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoService productoService;


    @Transactional
    public void registrar(Usuario usuario, @NonNull Proveedor proveedor) throws Exception {

        String nombre = proveedor.getNombre();
        String direccion = proveedor.getDireccion();
        String telefono = proveedor.getTelefono();
        validar(nombre, direccion, telefono);
        Usuario aux = usuarioService.getUsuario(usuario);
        aux.getListaProveedores().add(proveedor);
        usuarioService.modificar(aux);
    }

    @Transactional
    public void nuevo(Usuario usuario, Proveedor proveedor) throws ErrorServicio {
        usuarioService.agregarProveedor(proveedor, usuario);
    }

    @Transactional
    public void modificar(@NonNull Proveedor proveedor) throws ErrorServicio {
        String nombre = proveedor.getNombre();
        String direccion = proveedor.getDireccion();
        String telefono = proveedor.getTelefono();
        validar(nombre, direccion, telefono);

        Optional<Proveedor> respuesta = repo.findById(proveedor.getId());
        if (respuesta.isPresent()) {
            Proveedor aProveedor = respuesta.get();
            aProveedor.setNombre(nombre);
            aProveedor.setDireccion(direccion);
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
    public void borrar(Usuario usuario, @NonNull Proveedor proveedor) throws Exception {
        Usuario aux = usuarioService.getUsuario(usuario);
        aux.getListaProveedores().remove(proveedor);
        usuarioService.modificar(aux);
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
        return repo.findProveedorByUsuarioId(usuario.getId());
    }

    //TODO
    public void agregarProducto(HttpSession httpSession, String idProveedor, Producto producto) throws ErrorServicio {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        Usuario aux = usuarioService.getUsuario(usuario);
        aux.getListaProveedores().forEach(proveedor -> {
            if (proveedor.getId().equals(idProveedor)) {

            }
        });
        usuarioService.actualizarHttpSession(httpSession, usuario);
    }
}


