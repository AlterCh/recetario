package com.recetario.receta;

import com.recetario.ingrediente.Ingrediente;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


@Entity //JPA
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
public class Receta {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @OneToMany
    private List<Ingrediente> ingredientes;
    
    private Integer porciones;
    
    private String descripcion;
    
    private Integer tiempo;
    
    private Categoria categoria; //TODO Hay que hacer la Clase Entidad, CRUD, Repositorio
    
}
