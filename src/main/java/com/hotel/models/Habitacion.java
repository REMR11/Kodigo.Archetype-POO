package com.hotel.models;

public class Habitacion {
    private String numero;
    private String tipo; // ej. "simple", "doble", "suite"
    private int capacidad;
    private double precioBase;
    private boolean disponible;

    public Habitacion(String numero, String tipo, int capacidad, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.disponible = true; // Por defecto, una habitación se crea disponible
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean esDisponible() {
        return disponible;
    }

    public void reservar() {
        this.disponible = false;
    }

    public void liberar() {
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", disponible=" + disponible +
                '}';
    }
}