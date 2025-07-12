package com.hotel.builder;

import com.hotel.exceptions.ReservaInvalidaException;
import com.hotel.factory.ServicioFactory;
import com.hotel.interfaces.Servicio;
import com.hotel.models.Cliente;
import com.hotel.models.Habitacion;
import com.hotel.models.Reserva;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class); // Añadido

    private String idReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private List<Servicio> servicios;
    private List<Servicio> serviciosAdicionales;


    public ReservaBuilder(Cliente cliente, Habitacion habitacion, LocalDate fechaLlegada, LocalDate fechaSalida, List<Servicio> servicios) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.servicios = servicios;
        this.serviciosAdicionales = new ArrayList<>();
    }

    public ReservaBuilder conId(String idReserva){
        this.idReserva = idReserva;
        return this;
    }

    public ReservaBuilder conServicio(Servicio servicio){
        if (servicio !=null){
            this.serviciosAdicionales.add(servicio);
            logger.debug("Aniadiendo nuevo servicio {}", servicio.getDescripcion());
        }
        return this;
    }

    public Reserva build() throws ReservaInvalidaException {
        if (cliente == null || habitacion == null || fechaLlegada ==null || fechaSalida ==null){
            logger.error("Error al construir la reserva: Campos obligatorios faltantes.");
            throw new ReservaInvalidaException("Error al construir la reserva: Campos obligatorios faltantes.");
        }

        if (fechaLlegada.isAfter(fechaSalida)){
            logger.error("Error la fechas no son coherentes.");
            throw new ReservaInvalidaException("Error al construir la reserva: Revisa las fechas.");
        }

        if (fechaLlegada.isBefore(fechaSalida)){
            logger.error("Error la fechas no son coherentes.");
            throw new ReservaInvalidaException("Error al construir la reserva: Revisa las fechas.");
        }

        Reserva reserva;

        if (idReserva != null && !idReserva.isEmpty()){
            reserva = new Reserva(idReserva, habitacion, fechaLlegada, fechaSalida);
        }else {
            reserva = new Reserva(cliente, habitacion, fechaLlegada, fechaSalida);
        }


        for (Servicio serviciosAdicionale : serviciosAdicionales) {
            reserva.agregarServicio(serviciosAdicionale);
        }
        logger.info("Reserva creada exitosamente {}.", reserva);
        return reserva;
    }
}