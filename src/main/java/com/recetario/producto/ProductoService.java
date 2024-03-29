package com.recetario.producto;

import com.recetario.errores.ErrorServicio;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    UsuarioService usuarioService;

    public List<Producto> getAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Transactional
    public void registrar(Usuario usuario, @NonNull Producto producto) throws Exception {
        try {
            String nombre = producto.getNombre();
            Double cantidad = producto.getCantidad();
            Double stock = producto.getStock();
            validar(nombre, producto.getPrecio(), cantidad, producto.getUnidad(), stock);
            Usuario aux = usuarioService.getUsuario(usuario);
            if (aux != null) {
                aux.getListaProductos().add(producto);
                usuarioService.modificar(aux);
            } else {
                throw new Exception("Error al agregar un producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }


    @Transactional
    public void modificar(Usuario usuario,@NonNull Producto producto) throws ErrorServicio {
        try {
            String nombre = producto.getNombre();
            Double cantidad = producto.getCantidad();
            Double stock = producto.getStock();

            validar(nombre, producto.getPrecio(), cantidad, producto.getUnidad(), stock);

            Optional<Producto> respuesta = productoRepository.findById(producto.getId());
            if (respuesta.isPresent()) {
                usuario.getListaProductos().remove(producto);
                Producto aProducto = respuesta.get();

                if (aProducto.getNombre() != producto.getNombre()) {
                    aProducto.setNombre(nombre);
                }
                if (aProducto.getPrecio() != producto.getPrecio()) {
                    aProducto.setPrecio(producto.getPrecio());
                }
                if (aProducto.getCantidad() != producto.getCantidad()) {
                    aProducto.setCantidad(cantidad);
                }
                if (aProducto.getUnidad() != producto.getUnidad()) {
                    aProducto.setUnidad(producto.getUnidad());
                }
                if (aProducto.getStock() != producto.getStock()) {
                    aProducto.setStock(stock);
                }

                usuario.getListaProductos().add(aProducto);
                usuarioService.modificar(usuario);

            } else {
                throw new ErrorServicio("No se encontró el producto solicitado");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }


    @Transactional
    public void borrar(@NonNull String id, Usuario usuario) throws Exception {
        try {
            Optional<Producto> respuesta = productoRepository.findById(id);
            if (respuesta.isPresent()) {
                usuarioService.borrarProducto(respuesta.get(), usuario);
            } else {
                throw new ErrorServicio("No se encontró el Producto solicitado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void validar(String nombre, Double precio, Double cantidad, String unidad, Double stock) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (precio == null) {
            throw new ErrorServicio("El precio no puede ser nulo");
        }

        if (cantidad == null) {
            throw new ErrorServicio("La Cantidad no puede ser nula");
        }
        if (unidad == null) {
            throw new ErrorServicio("La unidad no puede ser nula");
        }
//        if (stock == null) {
//            throw new ErrorServicio("El stock no puede ser nulo");
//        }
    }
}




