package com.hotel.factory;

import com.hotel.exceptions.ServicioNoDisponibleException;
import com.hotel.interfaces.Servicio;
import com.hotel.models.Reserva;
import com.hotel.models.TypeServices;
import com.hotel.services.ServicioBasico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.interfaces.Servicio;
import com.hotel.models.Reserva;
import com.hotel.services.ServicioBasico;
import com.hotel.exceptions.ServicioNoDisponibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class); // Añadido

    public static Servicio crearServicio(String tipoServicio) throws ServicioNoDisponibleException {
        logger.info("Creando servicio de tipo: {}", tipoServicio);
        switch (tipoServicio.toLowerCase()) {
            case "basico":
                return new ServicioBasico("Servicio Básico de Habitacion", 0.0);
            // Estos servicios básicos son útiles para ser envueltos por decoradores
            case "desayuno":
                return new ServicioBasico("Desayuno", 25.0); // O podrías retornar el decorador directamente aquí
            case "parking":
                return new ServicioBasico("Parking", 15.0);
            case "spa":
                return new ServicioBasico("Acceso a Spa", 50.0);
            case "mascota":
                return new ServicioBasico("Permiso Mascota", 30.0);
            case "transporte":
                return new ServicioBasico("Servicio de Transporte", 40.0);
            case "latecheckout":
                return new ServicioBasico("Late Checkout", 60.0);
            case "wifipremium":
                return new ServicioBasico("WiFi Premium", 10.0);
            case "limpiezaextra":
                return new ServicioBasico("Limpieza Extra", 20.0);
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
