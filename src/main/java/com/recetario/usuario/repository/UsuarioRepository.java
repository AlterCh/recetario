package com.recetario.usuario.repository;

import com.recetario.usuario.domain.ListaDeCompra;
import com.recetario.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByMail(String mail);
//    List<Usuario> findAllByMail(String mail);

  
    
}                                      