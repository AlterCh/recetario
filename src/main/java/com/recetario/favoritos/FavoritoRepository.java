package com.recetario.favoritos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
@Query(value = "select * from receta r left join usuario_lista_recetas ulr on r.id = ulr.lista_recetas_id where ulr.usuario_id = :id",nativeQuery = true)
List<Receta> findRecetaByUsuario(@Param("id")String id);
*/

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, String> {
    
    @Query(value = "SELECT * FROM favorito f LEFT JOIN usuario_lista_favoritos ulf ON f.id = ulf.lista_favoritos_id WHERE ulf.usuario_id = :id AND favoritos_recetas != null AND favoritos_proveedores = null",nativeQuery = true)
    public List<Favorito> getAllByUsuarioR(@Param("id")String id);
    
    @Query(value = "SELECT * FROM favorito f LEFT JOIN usuario_lista_favoritos ulf ON f.id = ulf.lista_favoritos_id WHERE ulf.usuario_id = :id AND favoritos_recetas = null AND favoritos_proveedores != null", nativeQuery = true)
    public List<Favorito> getAllByUsuarioP(@Param("id")String id); 
    
}
