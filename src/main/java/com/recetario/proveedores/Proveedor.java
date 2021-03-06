
package com.recetario.proveedores;

import com.recetario.producto.Producto;
import com.recetario.provincia.Provincia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //
public class Proveedor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;

    private String direccion;

    private String telefono;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Producto> productos = new HashSet<>();

    @Builder.Default
    private Boolean favorito = false;
}
