package com.recetario.siu;

public enum TipoUnidad {
    MASA("Masa"),LIQUIDO("Líquido");
    String nombre;

    TipoUnidad() {
    }

    TipoUnidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
