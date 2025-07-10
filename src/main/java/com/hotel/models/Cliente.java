package com.hotel.models;

import java.util.Arrays;

public class Cliente{
  private String id;
  private String nombre;
  private String telefono;
  private Reserva[] historialReservas;

  public Cliente(String id, String nombre, String telefono, Reserva[] historialReservas) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.historialReservas = historialReservas;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Reserva[] getHistorialReservas() {
    return historialReservas;
  }

  public void setHistorialReservas(Reserva[] historialReservas) {
    this.historialReservas = historialReservas;
  }

  @Override
  public String toString() {
    return "Cliente{" +
            "id='" + id + '\'' +
            ", nombre='" + nombre + '\'' +
            ", telefono='" + telefono + '\'' +
            ", historialReservas=" + Arrays.toString(historialReservas) +
            '}';
  }
}