
package com.recetario.proveedores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorios = Interfaces
 * deben extender de JpaRepository<tipo,tipo_id>
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,String>{
    
}
