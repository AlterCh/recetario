package com.recetario.receta;

import com.recetario.errores.ErrorServicio;
import com.recetario.ingrediente.Ingrediente;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.repository.UsuarioRepository;
import com.recetario.usuario.service.UsuarioService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaService {
    
    private RecetaRepository recetaRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.recetaRepository = recetaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public void registrar(Usuario u, @NonNull Receta r) throws ErrorServicio {
        try {
            validar(r);
            Usuario usuario = usuarioService.getUsuario(u);
            usuario.getListaRecetas().add(r);
            usuarioService.modificar(usuario);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("Hubo un error registrando la receta.");
        }
    }
    
    @Transactional
    public List<Receta> listarRecetasPorUsuario(Usuario usuario) {
        recetaRepository.findAllByUsuarioId(usuario.getId());
        return recetaRepository.findAll();
    }
    
//    @Transactional
//    public Page<Receta> listarNombresCategorias(String nombre, Pageable pageable) { //R
//        return repo.findAllByCategoriaNombre(nombre, pageable); //TODO , ver que tipo de dato se pasa en Pageable.
//    }
    
    @Transactional
    public void modificar(@NonNull Receta r) throws ErrorServicio { //U
        validar(r);
        String nombre = r.getNombre();
        List<Ingrediente> ingredientes = r.getIngredientes();
        Integer porciones = r.getPorciones();
        String descripcion = r.getDescripcion();
        Integer tiempo = r.getTiempo();
        
        Optional<Receta> respuesta = recetaRepository.findById(r.getId());
        if (respuesta.isPresent()) {
            Receta aReceta = respuesta.get();
            aReceta.setNombre(nombre);
            aReceta.setIngredientes(ingredientes);
            aReceta.setPorciones(porciones);
            aReceta.setDescripcion(descripcion);
            aReceta.setTiempo(tiempo);
            recetaRepository.save(aReceta);
        } else {
            throw new ErrorServicio("La receta no se encuentra registrada.");
        }
    }
    
    @Transactional
    public void borrar(String id) throws ErrorServicio { //D
        Optional<Receta> respuesta = recetaRepository.findById(id);
        if (respuesta.isPresent()) {
            Receta rec = respuesta.get();
            recetaRepository.delete(rec);
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

    public List<Receta> getAllByUsuario(Usuario usuario) {
        return recetaRepository.findAllByUsuarioId(usuario.getId());
    }
}
