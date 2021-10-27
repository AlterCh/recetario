package com.recetario.producto;

import com.recetario.categoria.Categoria;
import com.recetario.enumeraciones.UnidadMedicion;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity //JPA
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
public class Producto {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Double precio;
    private Double cantidad;
    private UnidadMedicion unidadMedicion;
    private Double stock;
    @OneToMany
    private Categoria categoria; //TODO



}
