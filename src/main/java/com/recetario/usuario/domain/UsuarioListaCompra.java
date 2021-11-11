package com.recetario.usuario.domain;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioListaCompra {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    @Builder.Default
    private LocalDate fechaAgregado = LocalDate.now();
    private String lista;
    @ManyToOne
    private Usuario usuario;
}
