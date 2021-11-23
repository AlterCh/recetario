package com.recetario.favoritos;

import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorRepository;
import com.recetario.receta.Receta;
import com.recetario.receta.RecetaRepository;
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
    private ProveedorRepository proveedorRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public void setFavorito(Usuario usuario, String id, String tipo) throws ErrorServicio {
        if (tipo.equals("proveedor")) {
            Optional<Proveedor> aux = proveedorRepository.findById(id);
            if (aux.isPresent()) {
                Proveedor fav = aux.get();
                fav.setFavorito(!fav.getFavorito());
                proveedorRepository.save(fav);
            }
        } else if (tipo.equals("receta")) {
            Optional<Receta> aux = recetaRepository.findById(id);
            if (aux.isPresent()) {
                Receta fav = aux.get();
                fav.setFavorito(!fav.getFavorito());
                recetaRepository.save(fav);
            }
        }
    }


//
//    @Transactional
//    public void modificar(@NonNull Favorito favorito) throws ErrorServicio {
//
//        List<Proveedor> proveedoresFav = favorito.getFavoritosProveedores();
//        List<Receta> recetasFav = favorito.getFavoritosRecetas();
//
//        Optional<Favorito> respuesta = repo.findById(favorito.getId());
//        if (respuesta.isPresent()) {
//            Favorito fav = respuesta.get();
//            fav.setFavoritosProveedores(proveedoresFav);
//            fav.setFavoritosRecetas(recetasFav);
//            repo.save(fav);
//        } else {
//            throw new ErrorServicio("Lo solicitado no fue encontrado, intente de nuevo por favor.");
//        }
//
//    }
//
//    @Transactional
//    public List<Favorito> listarFavoritos() {
//        return repo.findAll();
//    }
//
//    @Transactional
//    public List<Favorito> listarRecetasFavoritas() {
//        return repo.findAll();
//    }
//
//    @Transactional
//    public void borrar(@NonNull Favorito favorito) throws ErrorServicio {
//
//        String id = favorito.getId();
//        Optional<Favorito> respuesta = repo.findById(id);
//        if (respuesta.isPresent()) {
//            Favorito fav = respuesta.get();
//            repo.delete(fav);
//        } else {
//            throw new ErrorServicio("No se encontró el favorito a eliminar.");
//        }
//    }
//
//    public List<Favorito> getAllByUsuarioP(Usuario usuario) {
//        return repo.getAllByUsuarioP(usuario.getId());
//    }
//
//    public List<Favorito> getAllByUsuarioR(Usuario usuario) {
//        return repo.getAllByUsuarioR(usuario.getId());
//    }

}
