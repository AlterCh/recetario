package com.recetario.usuario.domain;

import com.recetario.favoritos.Favorito;
import com.recetario.foto.Foto;
import com.recetario.producto.Producto;
import com.recetario.proveedores.Proveedor;
import com.recetario.provincia.Provincia;
import com.recetario.receta.Receta;
import com.recetario.rol.Rol;

import java.util.List;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity //JPA
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;

    private String apellido;

    private String mail;

    private String clave;

    private String telefono;

    private String domicilio;

    @OneToOne
    private Provincia provincia;

    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    @OneToOne(cascade = CascadeType.ALL)
    private Foto foto;


    @OneToOne
    @JoinColumn(name = "rol_ID")
    private Rol rol;

    @OneToOne
    private PreferenciasUsuario preferenciasUsuario ;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ListaDeCompra> listaCompra;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Proveedor> listaProveedores;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Receta> listaRecetas;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Favorito> listaFavoritos;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Producto> listaProductos;


}
