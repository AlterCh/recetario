package com.recetario.favoritos;

import com.recetario.proveedores.Proveedor;
import com.recetario.receta.Receta;
import com.recetario.usuario.domain.Usuario;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorito {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
//    private Usuario usuario;
    
    @OneToMany (cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Receta> favoritosRecetas;
    
    @OneToMany (cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Proveedor> favoritosProveedores;
    
}
