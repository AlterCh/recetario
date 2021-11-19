package com.recetario.favoritos;

import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.receta.Receta;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository repo;
    
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public void nuevoFavorito(@ModelAttribute Favorito favorito, HttpSession httpSession, ModelMap model) throws ErrorServicio {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        usuarioService.agregarFavorito(favorito, usuario);
        usuarioService.actualizarHttpSession(httpSession,usuario);
    }

    @Transactional
    public void guardarFavoritosProveedores(Favorito favorito, Proveedor proveedor) {
        Optional<Favorito> aux = repo.findById(favorito.getId());
        if (aux.isPresent()) {
            Favorito fav = aux.get();
            fav.getFavoritosProveedores().add(proveedor);
            repo.save(fav);
        }
    }

    @Transactional
    public void guardarFavoritosRecetas(Favorito favorito, Receta receta) {
        Optional<Favorito> aux = repo.findById(favorito.getId());
        if (aux.isPresent()) {
            Favorito fav = aux.get();
            fav.getFavoritosRecetas().add(receta);
            repo.save(fav);
        }
    }

    @Transactional
    public void modificar(@NonNull Favorito favorito) throws ErrorServicio {
        
        List<Proveedor> proveedoresFav = favorito.getFavoritosProveedores();
        List<Receta> recetasFav = favorito.getFavoritosRecetas();
        
        Optional<Favorito> respuesta = repo.findById(favorito.getId());
        if (respuesta.isPresent()) {
            Favorito fav = respuesta.get();
            fav.setFavoritosProveedores(proveedoresFav);
            fav.setFavoritosRecetas(recetasFav);
            repo.save(fav);
        } else {
            throw new ErrorServicio("Lo solicitado no fue encontrado, intente de nuevo por favor.");
        }
        
    }
    
    @Transactional
    public List<Favorito> listarFavoritos() {
        return repo.findAll();
    }

    @Transactional
    public List<Favorito> listarRecetasFavoritas() {
        return repo.findAll();
    }

    @Transactional
    public void borrar(@NonNull Favorito favorito) throws ErrorServicio {

        String id = favorito.getId();
        Optional<Favorito> respuesta = repo.findById(id);
        if (respuesta.isPresent()) {
            Favorito fav = respuesta.get();
            repo.delete(fav);
        } else {
            throw new ErrorServicio("No se encontró el favorito a eliminar.");
        }
    }

    public Favorito getByUsuarioP(Usuario usuario) {
        return repo.getById(usuario.getId());
    }
    
    public Favorito getByUsuarioR(Usuario usuario) {
        return repo.getById(usuario.getId());
    }
    
}
