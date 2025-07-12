package com.hotel.core;

import com.hotel.builder.ReservaBuilder;
import com.hotel.exceptions.*;
import com.hotel.models.Cliente;
import com.hotel.models.Habitacion;
import com.hotel.models.Reserva;
import com.hotel.observer.NotificadorReservas;
import com.hotel.strategy.PagoStrategy;
import com.hotel.strategy.PoliticaPrecio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GestorReservas {

    private static final Logger logger = LoggerFactory.getLogger(GestorReservas.class);
    private static GestorReservas instancia;
    private List<Reserva> reservas;
    private List<Cliente> clientes;
    private List<Habitacion> habitaciones;
    private NotificadorReservas notificador;


    public GestorReservas() {
        this.reservas = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.notificador = new NotificadorReservas();
        logger.info("Gestor de reserva inicializado (Singleton)");
    }

    public static GestorReservas getInstance(){
        if (instancia == null){
         instancia = new GestorReservas();
        }
        return instancia;
     }

     public void registrarCliente(Cliente cliente) throws ClienteDuplicadoException {
        if (clientes.stream().anyMatch(clientelocal -> clientelocal.getId().equals(cliente.getId()))){
            logger.warn("Intento de registro de cliente duplicado {}", cliente.getId());
            throw new ClienteDuplicadoException("Cliente ID" + cliente.getId() + " ya existe en el sistema");
        }
        this.clientes.add(cliente);
        logger.info("Cliente {} registrado exitosamente.", cliente.getNombre());

     }

     public Optional<Cliente> obtenerCliente(String idCliente){
        return clientes.stream().filter(c -> c.getId().equals(idCliente)).findFirst();
     }

     public void registrarHabitacion(Habitacion habitacion){
        this.habitaciones.add(habitacion);
        logger.info("Habitacion {} de tipo {} registrada exitosamente.", habitacion.getNumero(), habitacion.getTipo());
     }
     public Reserva crearReserva(ReservaBuilder builder) throws HabitacionNoDisponibleException, ClienteDuplicadoException {
        Reserva reserva = builder.build();
        if (!reserva.getHabitacion().esDisponible()){
            logger.warn("Habitacion {} no disponible.", reserva.getHabitacion().getNumero());
            throw new HabitacionNoDisponibleException("Habitacion" +  reserva.getHabitacion().getNumero()+" no disponible.");
        }
        if (!obtenerCliente(reserva.getCliente().getId()).isPresent()){
            logger.warn("Cliente {} no encontrado.", reserva.getCliente().getNombre());
            registrarCliente(reserva.getCliente());
        }

        reserva.getHabitacion().reservar();
        this.reservas.add(reserva);
        reserva.getCliente().agregarReserva(reserva);
         logger.info("Reserva {} creada para {} exitosamente.", reserva.getIdReserva(), reserva.getCliente().getNombre());
         return reserva;
    }

    public boolean confirmarReserva(String idReserva, PagoStrategy estrategia)
            throws ConfirmacionReservaException, PagoNoProcesadoException {
        Optional<Reserva> optionalReserva = obtenerReserva(idReserva);

        if (!optionalReserva.isPresent()){
            logger.warn("Intento de confirmar reserva no existente: {}", idReserva);
            throw new ConfirmacionReservaException("Reserva ID: " + idReserva + " No existe");
        }

        Reserva reserva = optionalReserva.get();
        reserva.confirmar();
        reserva.calcularPrecioTotal();

        if (reserva.procesarPago(estrategia)){
            logger.info("Reserva {} confirmada.", reserva.getIdReserva());
        return true;
        }else {
            logger.error("Error fatal: fallo al procesar el pago de la reserva {}.", reserva.getIdReserva());
            reserva.cancelar();
            throw new PagoNoProcesadoException("El proce pago para la reserva " + reserva.getIdReserva() + " no concluyo.");
        }

    }

    public void cancelarReserva(String idReserva) throws EstadoReservaInvalidoException {
        Optional<Reserva> optionalReserva = obtenerReserva(idReserva);

        if (!optionalReserva.isPresent()){
            logger.warn("Reserva {}, no encontrada.", idReserva);
            throw new EstadoReservaInvalidoException("Intento de cancelar reserva " + idReserva + " fallido.");
        }

        Reserva reserva = optionalReserva.get();
        reserva.cancelar();
        reserva.getHabitacion().liberar();
        logger.info("RESERVA CANCELADA {}", reserva.getIdReserva());
    }

     public void aplicarPoliticaPrecio(Reserva reserva, PoliticaPrecio politica){
      reserva.aplicarPoliticaPrecio(politica);
      logger.info("Politicas de precio aplicadas.");
     }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    private Optional<Reserva> obtenerReserva(String idReserva) {
        return reservas.stream().filter(r -> r.getIdReserva().equals(idReserva)).findFirst();
    }


}