package com.hotel.models;

public class Habitacion {
    private String numero;
    private String tipo;
    private double precio;
    private int capacidad;
    private boolean disponible;

    public Habitacion(String numero, String tipo, double precio, int capacidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}