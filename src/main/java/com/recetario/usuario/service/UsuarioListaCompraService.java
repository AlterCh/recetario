package com.recetario.usuario.service;

import com.recetario.errores.ErrorServicio;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.UsuarioListaCompra;
import com.recetario.usuario.repository.UsuarioListaCompraRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioListaCompraService {

    UsuarioListaCompraRepository repo;
    UsuarioService usuarioService;
    @Autowired
    public UsuarioListaCompraService(UsuarioListaCompraRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public List<UsuarioListaCompra> getListaCompra(Usuario usuario) throws ErrorServicio {
        if(usuario == null){
            throw new ErrorServicio(this.getClass().getName() + ": No se pudo encontrar el usuario");
        }
        Usuario u = usuarioService.getUsuarioById(usuario);
        return repo.findUsuarioListaCompraByUsuario(u);
    }

    @Transactional
    public void nuevaListaCompra(UsuarioListaCompra usuarioListaCompra){
        repo.save(usuarioListaCompra);
    }

    @Transactional
    public void modificarListaCompra(UsuarioListaCompra listaCompra) {
        Optional<UsuarioListaCompra> aux =  repo.findById(listaCompra.getId());
        if(aux.isPresent()){
            UsuarioListaCompra ulc = aux.get();
            if(ulc.getLista() != listaCompra.getLista()){
                ulc.setLista(listaCompra.getLista());
            }
            repo.save(ulc);
        }
    }
}
