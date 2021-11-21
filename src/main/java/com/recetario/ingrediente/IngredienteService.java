package com.recetario.ingrediente;

import com.recetario.errores.ErrorServicio;
import com.recetario.producto.ProductoRepository;
import com.recetario.producto.ProductoService;
import com.recetario.receta.RecetaRepository;
import com.recetario.receta.RecetaService;
import com.recetario.siu.Unidad;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {


    private IngredienteRepository ingredienteRepository;
    private ProductoRepository productoRepository;
    private ProductoService productoService;
    private RecetaService recetaService;
    private RecetaRepository recetaRepository;

    @Autowired
    public IngredienteService(IngredienteRepository ingredienteRepository, ProductoRepository productoRepository, ProductoService productoService, RecetaService recetaService, RecetaRepository recetaRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.productoRepository = productoRepository;
        this.productoService = productoService;
        this.recetaService = recetaService;
        this.recetaRepository = recetaRepository;
    }

    @Transactional
    public void registrar(@NonNull Ingrediente i) throws ErrorServicio {

        try {
            validar(i);
            ingredienteRepository.save(i);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("No se pudo registrar el ingrediente, intente de nuevo.");
        }

    }

    @Transactional
    public List<Ingrediente> listarIngredientes() { //R
        return ingredienteRepository.findAll();
    }

    @Transactional
    public void modificar(String id, Double cantidad, String unidad) throws ErrorServicio { //U

        try {
            Optional<Ingrediente> respuesta = ingredienteRepository.findById(id);
            if (respuesta.isPresent()) {
                try {
                    Ingrediente ing = respuesta.get();
                    ing.setCantidad(cantidad);
                    ing.setUnidades(unidad);
                    validar(ing);
                    ingredienteRepository.save(ing);
                } catch (ErrorServicio e) {
                    throw new ErrorServicio("Ha ingresado mal los datos.");
                }
            } else {
                throw new ErrorServicio("No se encontró el Ingrediente registrado");
            }

        } catch (ErrorServicio e) {
            throw new ErrorServicio("Ha habido un error, intente nuevamente.");
        }

    }

    @Transactional
    public void borrar(String id) throws ErrorServicio { //D
        Optional<Ingrediente> respuesta = ingredienteRepository.findById(id);
        if (respuesta.isPresent()) {
            Ingrediente ing = respuesta.get();
            ingredienteRepository.delete(ing);
        } else {
            throw new ErrorServicio("No se encontró el ingrediente solicitado.");
        }
    }

    public void validar(Ingrediente i) throws ErrorServicio {

        if (i == null) {
            throw new ErrorServicio("Ingrese los datos del ingrediente por favor.");
        }
        if (i.getProducto() == null || i.getProducto().getId().isEmpty()) {
            throw new ErrorServicio("El producto no existe o esta mal insertado.");
        }
        if (i.getCantidad() == null || i.getCantidad() < 0) {
            throw new ErrorServicio("La cantidad no puede ser nula o menor a cero.");
        }
        if (i.getUnidades() == null) {
            throw new ErrorServicio("Debe indicar una Unidad válida.");
        }
    }

    public Ingrediente getIngrediente(String id_ingrediente) throws ErrorServicio {
        try {
            if (id_ingrediente != null) {
                Ingrediente ingrediente = ingredienteRepository.getById(id_ingrediente);
                validar(ingrediente);
                return ingrediente;
            }else{
                throw new ErrorServicio("");
            }
        } catch (ErrorServicio errorServicio) {
            throw new ErrorServicio("No se ha encontrado el ingrediente");
        }
    }
}
