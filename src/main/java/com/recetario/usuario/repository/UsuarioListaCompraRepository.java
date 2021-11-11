package com.recetario.usuario.repository;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.UsuarioListaCompra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioListaCompraRepository  extends JpaRepository<UsuarioListaCompra,String> {

    List<UsuarioListaCompra> findUsuarioListaCompraByUsuario(Usuario usuario);

}
