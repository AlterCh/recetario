package com.recetario.usuario.repository;

import com.recetario.usuario.domain.ListaDeCompra;
import com.recetario.usuario.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeCompraRepository  extends JpaRepository<ListaDeCompra,String> {
    @Query(value = "select lc.* from lista_de_compra lc left join usuario_lista_compra ulc on lc.id = ulc.lista_compra_id where ulc.usuario_id =  :id",nativeQuery = true)
    List<ListaDeCompra> findListaDeCompraByUsuario(@Param("id") String idUsuario);

}