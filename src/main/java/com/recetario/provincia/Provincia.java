package com.recetario.provincia;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "provincia")
@Entity //JPA
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
public class Provincia {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String nombre;

}