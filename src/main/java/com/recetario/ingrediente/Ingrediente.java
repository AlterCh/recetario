package com.recetario.ingrediente;


import com.recetario.producto.Producto;
import com.recetario.siu.Unidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;

@Entity
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @NonNull
    private Producto producto;

    @Builder.Default
    private Double cantidad = 0.0;

    @Builder.Default
    private String unidades = "";

}
