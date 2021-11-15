package com.recetario.usuario.domain;

import com.recetario.siu.Magnitud;
import com.recetario.siu.Unidad;
import com.recetario.siu.UnidadesFundamentales;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity //JPA
@Data //Getter y Setter
@ToString //To String
@Builder //Defaults
@AllArgsConstructor //Constructor con todos los parametros
@NoArgsConstructor //Constructor vacío
@RequiredArgsConstructor
public class PreferenciasUsuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Builder.Default
    private Magnitud magnitud = Magnitud.MASA;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Builder.Default
    private UnidadesFundamentales unidadesFundamentales = UnidadesFundamentales.GRAMO;

    @Builder.Default
    private Integer decimales=3;


}
