package com.recetario.siu;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Magnitud {

    MASA("Masa"),
    LONGITUD("Longitud"),
    TIEMPO("Tiempo"),
    CORRIENTE_ELECTRICA("Corriente Electrica"),
    TEMPERATURA("Temperatura"),
    INTENSIDAD_LUMINICA("Intensidad Luminica"),
    MATERIA("Materia");

    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
