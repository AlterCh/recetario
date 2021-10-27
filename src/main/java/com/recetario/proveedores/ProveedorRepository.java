
package com.recetario.proveedores;

import com.recetario.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProveedorRepository{ //extends JpaRepository<>{

    
    void save(Proveedor proveedor) {

    }
    
    @Query("SELECT c FROM Proveedor c WHERE c.id = :id")
    Optional<Proveedor> findById(String id) {
        return null;
    }
    
}
