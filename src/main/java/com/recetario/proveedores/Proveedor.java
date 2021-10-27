
package com.recetario.proveedores;

import com.recetario.producto.Producto;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    
    //private String provincia;

    private String telefono;

    private ArrayList<Producto> productos;
    
}
