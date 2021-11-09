package com.recetario.receta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, String> {
    
    Receta findByNombre(String nombre);
    
//    Receta findByCategoria(String categoria);

//    Page<Receta> findAllByCategoriaNombre(String nombre, Pageable pageable);

}
