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
    
    @Query(value = "SELECT * FROM favorito f LEFT JOIN lista_favoritos lf ON f.id = lf.lista_favoritos_id WHERE lf.usuario_id = :id && favoritosRecetas != null",nativeQuery = true)
    public List<Favorito> recetasFavoritas(@Param("id")String id);
    
    @Query(value = "SELECT * FROM favorito WHERE favoritosProveedores != null", nativeQuery = true)
    public List<Favorito> proveedoresFavoritos(@Param("id")String id); 
    
}
