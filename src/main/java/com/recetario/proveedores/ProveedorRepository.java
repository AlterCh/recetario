
package com.recetario.proveedores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorios = Interfaces
 * deben extender de JpaRepository<tipo,tipo_id>
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,String>{
//EDICION
    @Query(value = "select * from proveedor p left join usuario_lista_proveedores ulp on p.id = ulp.lista_proveedores_id where ulp.usuario_id = :id",nativeQuery = true)
    List<Proveedor> findProveedorByUsuarioId(@Param("id") String id);
}
