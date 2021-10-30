package com.recetario.siu;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.test.context.jdbc.Sql;

@Entity
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
@Sql({"/sql/core/v1__intial-data-unidad.sql"})
public class Unidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private TipoUnidad tipoUnidad;
    
    private String abrev;
    
}
