package com.recetario.producto;

import com.recetario.categoria.Categoria;
import com.recetario.siu.Unidad;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


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

    @Enumerated(EnumType.STRING)
    private Unidad unidadMedicion;

    private Double stock;

    @OneToMany
    private List<Categoria> categoria; //TODO



}
