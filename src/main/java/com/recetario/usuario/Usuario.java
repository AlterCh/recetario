package com.recetario.usuario;

import com.recetario.foto.Foto;
import com.recetario.provincia.Provincia;
import com.recetario.rol.Rol;
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

    @JoinColumn(name = "rol_ID")
    @OneToOne
    private Rol rol;

}
