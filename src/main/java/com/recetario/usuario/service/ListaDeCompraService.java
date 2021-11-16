package com.recetario.usuario.service;

import com.recetario.errores.ErrorServicio;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.ListaDeCompra;
import com.recetario.usuario.repository.ListaDeCompraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaDeCompraService {

    ListaDeCompraRepository repo;
    UsuarioService usuarioService;
    @Autowired
    public ListaDeCompraService(ListaDeCompraRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

//    @Transactional
//    public List<ListaDeCompra> getListaCompra(Usuario usuario) throws ErrorServicio {
//        if(usuario == null){
//            throw new ErrorServicio(this.getClass().getName() + ": No se pudo encontrar el usuario");
//        }
//        Usuario u = usuarioService.getUsuarioById(usuario);
//        return repo.findUsuarioListaCompraByUsuario(u);
//    }

    @Transactional
    public void nuevaListaCompra(ListaDeCompra listaDeCompra){
        repo.save(listaDeCompra);
    }

    @Transactional
    public void modificarListaCompra(ListaDeCompra listaCompra) {
        Optional<ListaDeCompra> aux =  repo.findById(listaCompra.getId());
        if(aux.isPresent()){
            ListaDeCompra ulc = aux.get();
            if(ulc.getLista() != listaCompra.getLista()){
                ulc.setLista(listaCompra.getLista());
            }
            repo.save(ulc);
        }
    }

    public List<ListaDeCompra> getAllByUsuario(Usuario usuariosession) {
        return repo.findListaDeCompraByUsuario(usuariosession.getId());
    }
}
