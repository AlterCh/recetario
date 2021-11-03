package com.recetario.siu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UnidadesFundamentales {


    /**
     * Unidades y Simbolos
     */
    GRAMO("gramo", "gr", Magnitud.MASA),
    ONZA("onza", "oz", Magnitud.MASA),
    METRO("metro", "m", Magnitud.LONGITUD),
    SEGUNDO("segundo", "s", Magnitud.TIEMPO),
    AMPERE("amp\u00E8re", "A", Magnitud.CORRIENTE_ELECTRICA),
    KELVIN("kelvin", "K", Magnitud.TEMPERATURA),
    CELSIUS("celsius", "C", Magnitud.TEMPERATURA),
    CANDELA("candela", "cd", Magnitud.INTENSIDAD_LUMINICA),
    MOL("mol", "mol", Magnitud.MATERIA);

    private String nombre;
    private String sym;
    private Magnitud magnitud;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public void setMagnitud(Magnitud magnitud) {
        this.magnitud = magnitud;
    }
}
