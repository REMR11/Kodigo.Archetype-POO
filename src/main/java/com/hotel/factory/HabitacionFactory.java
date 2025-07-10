package com.hotel.factory;

import com.hotel.exceptions.CapacidadHabitacionExcedidaException;
import com.hotel.models.Habitacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HabitacionFactory {
    private static final Logger logger = LoggerFactory.getLogger(HabitacionFactory.class);
    private static final int MAX_CAPACITY = 8;

    public static Habitacion crearHabitacion(String numero, String tipo, double precio, int capacidad)
            throws CapacidadHabitacionExcedidaException {
        logger.info("Creando habitacion de tipo {} y capacidad {}");
        validarCapacidad(capacidad);
        return new Habitacion(numero, tipo, precio, capacidad);
    }
    public static void validarCapacidad(int capacidad)
            throws CapacidadHabitacionExcedidaException {
        if (capacidad>MAX_CAPACITY){
            logger.warn("Intento de crear habitacion que excede la capacidad maxima");
            throw new CapacidadHabitacionExcedidaException("La capacidad maxima es : "
                    + MAX_CAPACITY + " de personas");
        }
    }
}