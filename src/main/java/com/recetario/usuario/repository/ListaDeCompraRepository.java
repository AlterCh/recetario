package com.recetario.usuario.repository;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.domain.ListaDeCompra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeCompraRepository extends JpaRepository<ListaDeCompra,String> {




}
