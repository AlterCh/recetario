package com.recetario.favoritos;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService {
    
    @Autowired
    private FavoritoRepository repo;
    
    @Transactional
    public void nuevoFavorito(Favorito favorito) {
        repo.save(favorito);
    }
    
    @Transactional
    public void guardarFavoritosProveedores(Favorito favorito) {
        Optional<Favorito> aux = repo.findById(favorito.getId());
        if (aux.isPresent()) {
            Favorito fav = aux.get();
            if(fav.getFavoritosProveedores() != favorito.getFavoritosProveedores()){
                fav.setFavoritosProveedores(favorito.getFavoritosProveedores());
            }
            repo.save(fav);
        }
    }
    
    @Transactional
    public void guardarFavoritosRecetas(Favorito favorito) {
        Optional<Favorito> aux = repo.findById(favorito.getId());
        if (aux.isPresent()) {
            Favorito fav = aux.get();
            if(fav.getFavoritosRecetas() != favorito.getFavoritosRecetas()){
                fav.setFavoritosRecetas(favorito.getFavoritosRecetas());
            }
            repo.save(fav);
        }
    }
    
    @Transactional
    public List<Favorito> listarRecetasFavoritas() {
        return repo.findAll();
    }
    
}
