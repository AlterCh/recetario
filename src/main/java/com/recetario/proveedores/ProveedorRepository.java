
package com.recetario.proveedores;

import com.recetario.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorios = Interfaces
 * deben extender de JpaRepository<tipo,tipo_id>
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,String>{
    
}
