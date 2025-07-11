package com.hotel.models;
import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.PagoNoProcesadoException;
import com.hotel.interfaces.Servicio;
import com.hotel.state.ReservaState;
import com.hotel.strategy.PagoStrategy;
import com.hotel.strategy.PoliticaPrecio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom; // Para simular ID aleatorio

public class Reserva {
    private String idReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private List<Servicio> serviciosAdicionales;
    private double precioTotal;
    private ReservaState estadoActual;

    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaLlegada, LocalDate fechaSalida) {
        this.idReserva = "RES-" + ThreadLocalRandom.current().nextInt(10000, 99999);
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.serviciosAdicionales = new ArrayList<>();
        this.precioTotal = 0.0;
        this.estadoActual = new ReservaPendiente(); // Estado inicial
    }

    // Constructor con idReserva (útil si se carga desde persistencia o si se requiere un ID específico)
    public Reserva(String idReserva, Cliente cliente, Habitacion habitacion, LocalDate fechaLlegada, LocalDate fechaSalida) {
        this(cliente, habitacion, fechaLlegada, fechaSalida); // Llama al constructor principal
        this.idReserva = idReserva;
    }

    public String getIdReserva() {
        return idReserva;
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

    public List<Servicio> getServiciosAdicionales() {
        return new ArrayList<>(serviciosAdicionales); // Retorna una copia
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public ReservaState getEstado() {
        return estadoActual;
    }

    public void setEstado(ReservaState estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void agregarServicio(Servicio servicio) {
        if (servicio != null) {
            this.serviciosAdicionales.add(servicio);
        }
    }

    public double calcularPrecioTotal() {
        long dias = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        if (dias <= 0) {
            dias = 1; // Mínimo un día de reserva
        }

        double subtotalHabitacion = habitacion.getPrecioBase() * dias;
        double subtotalServicios = serviciosAdicionales.stream()
                .mapToDouble(Servicio::getCosto)
                .sum();
        this.precioTotal = subtotalHabitacion + subtotalServicios;
        return this.precioTotal;
    }

    public void aplicarPoliticaPrecio(PoliticaPrecio politica) {
        long dias = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        if (dias <= 0) {
            dias = 1;
        }
        this.precioTotal = politica.calcularPrecio(habitacion.getPrecioBase(), (int) dias);
        // Sumar servicios adicionales después de aplicar la política a la habitación
        this.precioTotal += serviciosAdicionales.stream()
                .mapToDouble(Servicio::getCosto)
                .sum();
    }

    public boolean procesarPago(PagoStrategy estrategiaPago) throws PagoNoProcesadoException {
        if (precioTotal <= 0) {
            throw new PagoNoProcesadoException("No se puede procesar pago con precio total cero o negativo.");
        }
        return estrategiaPago.procesarPago(this.precioTotal);
    }

    // Métodos delegados al patrón State
    public void confirmar() throws ConfirmacionReservaException {
        estadoActual.confirmar(this);
    }

    public void cancelar() {
        estadoActual.cancelar(this);
    }

    public void pendificar() {
        estadoActual.pendificar(this);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", habitacion=" + habitacion.getNumero() +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaSalida=" + fechaSalida +
                ", precioTotal=" + String.format("%.2f", precioTotal) +
                ", estado=" + estadoActual.obtenerNombreEstado() +
                ", servicios=" + serviciosAdicionales.size() +
                '}';
    }
}