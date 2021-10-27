package com.recetario.ingrediente;

import Errores.ErrorServicio;
import com.recetario.enumeraciones.UnidadMedicion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repo;

    @Transactional
    public void registrar(@NonNull Ingrediente i) throws ErrorServicio { //C

        try {
            validar(i);
            repo.save(i);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("No se pudo registrar el ingrediente, intente de nuevo.");
        }

    }

    @Transactional
    public List<Ingrediente> listarIngredientes() { //R
        return repo.findAll();
    }

    @Transactional
    public void modificar(String id, Double cantidad, UnidadMedicion unidad) throws ErrorServicio { //U

        try {
            Optional<Ingrediente> respuesta = repo.findById(id);
            if (respuesta.isPresent()) {
                try {
                    Ingrediente ing = respuesta.get();
                    ing.setCantidad(cantidad);
                    ing.setUnidades(unidad);
                    validar(ing);
                    repo.save(ing);
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
            Optional<Ingrediente> respuesta = repo.findById(id);
            if (respuesta.isPresent()) {
                Ingrediente ing = respuesta.get();
                repo.delete(ing);
            } else {
                throw new ErrorServicio("No se encontró el ingrediente solicitado.");
            }
    }

    public void validar(Ingrediente i) throws ErrorServicio {

        if (i == null) {
            throw new ErrorServicio("Ingrese los datos del ingrediente por favor.");
        }
        if (i.getProducto() == null || i.getProducto().getNombre().isEmpty()) {
            throw new ErrorServicio("El producto no existe o esta mal insertado.");
        }
        if (i.getCantidad() == null || i.getCantidad() < 0) {
            throw new ErrorServicio("La cantidad no puede ser nula o menor a cero.");
        }
        if (i.getUnidades() == null) {
            throw new ErrorServicio("Debe indicar una Unidad válida.");
        }
    }
}
