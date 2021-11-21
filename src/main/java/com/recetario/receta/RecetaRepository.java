package com.recetario.receta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, String> {
    
    Receta findByNombre(String nombre);

    @Query(value = "select * from receta r left join usuario_lista_recetas ulr on r.id = ulr.lista_recetas_id where ulr.usuario_id = :id",nativeQuery = true)
    List<Receta> findRecetaByUsuario(@Param("id")String id);

//    Receta findByCategoria(String categoria);

//    Page<Receta> findAllByCategoriaNombre(String nombre, Pageable pageable);

}
