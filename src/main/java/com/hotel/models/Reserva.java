package com.hotel.models;

import com.hotel.interfaces.Servicio;
import com.hotel.state.ReservaState;

import java.time.LocalDate;
import java.util.Arrays;

public class Reserva{
    private String id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private Servicio[] serviciosAdicionales;
    private ReservaState estadoActual;
    private double precioTotal;

    public Reserva(String id, Cliente cliente, Habitacion habitacion, LocalDate fechaLlegada, LocalDate fechaSalida, Servicio[] serviciosAdicionales, ReservaState estadoActual, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.serviciosAdicionales = serviciosAdicionales;
        this.estadoActual = estadoActual;
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", habitacion=" + habitacion +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaSalida=" + fechaSalida +
                ", serviciosAdicionales=" + Arrays.toString(serviciosAdicionales) +
                ", estadoActual=" + estadoActual +
                ", precioTotal=" + precioTotal +
                '}';
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public Servicio[] getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public ReservaState getEstadoActual() {
        return estadoActual;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setServiciosAdicionales(Servicio[] serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public void setEstadoActual(ReservaState estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}