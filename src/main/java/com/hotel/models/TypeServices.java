package com.hotel.models;

public enum TypeServices {
    BASICO("BASICO", 0.0),
    DESAYUNO("DESAYUNO", 15),
    TRANSPORTE("TRANSPORTE", 30),
    PARKING("PARKING", 15),
    SPA("SPA", 50),
    MASCOTA("PERMISO MASCOSTA", 30);


    private final String nombre;
    private final double precio;

    TypeServices(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
