package com.hotel.models;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private String id;
  private String nombre;
  private String email;
  private String telefono;
  private List<Reserva> historialReservas;

  public Cliente(String id, String nombre, String email, String telefono) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.historialReservas = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void actualizarInfo(String email, String telefono) {
    this.email = email;
    this.telefono = telefono;
  }

  public List<Reserva> getHistorialReservas() {
    return new ArrayList<>(historialReservas); // Retorna una copia para evitar modificación externa
  }

  public void agregarReserva(Reserva reserva) {
    if (reserva != null && !historialReservas.contains(reserva)) {
      historialReservas.add(reserva);
    }
  }

  @Override
  public String toString() {
    return "Cliente{" +
            "id='" + id + '\'' +
            ", nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            ", telefono='" + telefono + '\'' +
            '}';
  }
}