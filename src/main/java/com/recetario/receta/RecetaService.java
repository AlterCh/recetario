package com.recetario.receta;

import com.recetario.categoria.Categoria;
import com.recetario.errores.ErrorServicio;
import com.recetario.ingrediente.Ingrediente;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecetaService {
    
    @Autowired
    private RecetaRepository repo;
    
    @Transactional
    public void registrar(@NonNull Receta r) throws ErrorServicio { //C
        try {
            validar(r);
            repo.save(r);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("Hubo un error registrando la receta.");
        }
    }
    
    @Transactional
    public List<Receta> listarRecetas() { //R
        return repo.findAll();
    }
    
    @Transactional
    public Page<Receta> listarNombresCategorias(String nombre, Pageable pageable) { //R
        return repo.findAllByCategoriaNombre(nombre, pageable); //TODO , ver que tipo de dato se pasa en Pageable.
    }
    
    @Transactional
    public void modificar(@NonNull Receta r) throws ErrorServicio { //U
        validar(r);
        String nombre = r.getNombre();
        List<Ingrediente> ingredientes = r.getIngredientes();
        Integer porciones = r.getPorciones();
        String descripcion = r.getDescripcion();
        Integer tiempo = r.getTiempo();
        List<Categoria> categoria = r.getCategoria();
        
        Optional<Receta> respuesta = repo.findById(r.getId());
        if (respuesta.isPresent()) {
            Receta aReceta = respuesta.get();
            aReceta.setNombre(nombre);
            aReceta.setIngredientes(ingredientes);
            aReceta.setPorciones(porciones);
            aReceta.setDescripcion(descripcion);
            aReceta.setTiempo(tiempo);
            aReceta.setCategoria(categoria);
            repo.save(aReceta);
        } else {
            throw new ErrorServicio("La receta no se encuentra registrada.");
        }
    }
    
    @Transactional
    public void borrar(String id) throws ErrorServicio { //D
        Optional<Receta> respuesta = repo.findById(id);
        if (respuesta.isPresent()) {
            Receta rec = respuesta.get();
            repo.delete(rec);
        } else {
            throw new ErrorServicio("La receta solicitada no esta registrada.");
        }
    }
    
    
    public void validar(Receta r) throws ErrorServicio {
        
        if (r == null) {
            throw new ErrorServicio("Ingrese los datos de la receta por favor.");
        }
        if (r.getNombre() == null || r.getNombre().isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío.");
        }
        if (r.getPorciones() == null || r.getPorciones() < 0) {
            throw new ErrorServicio("Las cantidades no pueden ser nulas o menores a cero.");
        }
        if (r.getIngredientes() == null) {
            throw new ErrorServicio("Los ingredientes no pueden ser nulos.");
        }
        if (r.getTiempo() == null || r.getTiempo() < 0) {
            throw new ErrorServicio("Ingrese un valor válido de tiempo.");
        }
    }
    
}
