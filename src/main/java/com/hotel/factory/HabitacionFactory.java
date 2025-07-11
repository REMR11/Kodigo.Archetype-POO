package com.hotel.factory;

import com.hotel.models.Habitacion;
import com.hotel.exceptions.CapacidadHabitacionExcedidaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HabitacionFactory {
    private static final Logger logger = LoggerFactory.getLogger(HabitacionFactory.class);
    private static final int MAX_CAPACIDAD = 6; // Constante añadida

    public static Habitacion crearHabitacion(String tipo, String numero, int capacidad, double precio)
            throws CapacidadHabitacionExcedidaException {
        validarCapacidad(capacidad);
        logger.info("Creando habitación de tipo {} con número {} y capacidad {}", tipo, numero, capacidad);
        return new Habitacion(numero, tipo, capacidad, precio);
    }

    public static void validarCapacidad(int capacidad) throws CapacidadHabitacionExcedidaException {
        if (capacidad > MAX_CAPACIDAD) {
            logger.warn("Intento de crear habitación con capacidad excedida: {}", capacidad);
            throw new CapacidadHabitacionExcedidaException(
                    "La capacidad máxima es " + MAX_CAPACIDAD + " personas");
        }
    }
}
