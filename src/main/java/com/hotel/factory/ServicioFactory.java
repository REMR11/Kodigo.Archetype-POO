package com.hotel.factory;

import com.hotel.exceptions.ServicioNoDisponibleException;
import com.hotel.interfaces.Servicio;
import com.hotel.models.Reserva;
import com.hotel.models.TypeServices;
import com.hotel.services.ServicioBasico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hotel.models.TypeServices.*;


public class ServicioFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class); // Añadido

    public static Servicio crearServicio(TypeServices tipoServicio) throws ServicioNoDisponibleException {
        logger.info("Creando servicio de tipo: {}", tipoServicio);
        switch (tipoServicio) {
            case BASICO:
                return new ServicioBasico("Servicio Básico de Habitacion", tipoServicio.getPrecio());
            case DESAYUNO:
                return new ServicioBasico("Desayuno", tipoServicio.getPrecio());
            case TRANSPORTE:
                return new ServicioBasico("Servicio de Transporte", tipoServicio.getPrecio());
            case PARKING:
                return new ServicioBasico("Parking", tipoServicio.getPrecio());
            case SPA:
                return new ServicioBasico("Acceso a Spa", tipoServicio.getPrecio());
            case MASCOTA:
                return new ServicioBasico("Permiso Mascota", tipoServicio.getPrecio());
            default:
                logger.error("Tipo de servicio no reconocido: {}", tipoServicio);
                throw new ServicioNoDisponibleException("Servicio '" + tipoServicio + "' no disponible.");
        }
    }


    public static void validarRestricciones(String tipoServicio, Reserva reserva) throws ServicioNoDisponibleException {
        logger.debug("Validando restricciones para servicio {} en reserva {}", tipoServicio, reserva.getIdReserva());
        if (tipoServicio.equalsIgnoreCase("mascota") && reserva.getHabitacion().getTipo().equalsIgnoreCase("suite")) {
            // Ejemplo de restricción: No se permiten mascotas en suites
            logger.warn("Restricción: No se permiten mascotas en habitaciones tipo suite. Reserva: {}", reserva.getIdReserva());
            throw new ServicioNoDisponibleException("Mascotas no permitidas en habitaciones tipo Suite.");
        }
        // Puedes añadir más validaciones aquí
    }
}
