package com.hotel.factory;

import com.hotel.exceptions.ServicioNoDisponibleException;
import com.hotel.interfaces.Servicio;
import com.hotel.models.Reserva;
import com.hotel.models.TypeServices;
import com.hotel.services.ServicioBasico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class);
    public static Servicio crearServicio(TypeServices tipoServicio) throws ServicioNoDisponibleException {
        logger.info("Creando servicio de tipo {}", tipoServicio.getNombre());

        switch (tipoServicio.getNombre()){
            case "BASICO":
                return (Servicio) new ServicioBasico(tipoServicio.getNombre(), tipoServicio.getPrecio());
            case "DESAYUNO":
                return (Servicio) new ServicioBasico(tipoServicio.getNombre(), tipoServicio.getPrecio());
            case "TRANSPORTE":
                return (Servicio) new ServicioBasico(tipoServicio.getNombre(), tipoServicio.getPrecio());
            case "PARKING":
                return (Servicio) new ServicioBasico(tipoServicio.getNombre(), tipoServicio.getPrecio());
            case "SPA":
                return (Servicio) new ServicioBasico(tipoServicio.getNombre(), tipoServicio.getPrecio());
            default:
                logger.error("Tipo de servicio no reconocido: {}", tipoServicio.getNombre());
                throw new ServicioNoDisponibleException("Servicio " + tipoServicio.getNombre() + " no esta disponible de momento");
        }

    }
    public static void validarRestricciones(TypeServices servicio, Reserva reserva) throws ServicioNoDisponibleException {
        logger.debug("Validando las restricciones para servicio {} en reserva para {}", servicio.getNombre(), reserva.getCliente());
        if (servicio.getNombre().equals("MASCOTA")&& reserva.getHabitacion().getTipo().equals("suite")){
            logger.warn("Restriccion: NO se permiten mascotas en la habitacion {}", reserva.getHabitacion().getTipo());
            throw new ServicioNoDisponibleException("Mascotas no permitidas en las habitaciones de tipo "+ reserva.getHabitacion().getTipo());
        }
    }
}