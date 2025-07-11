package com.hotel.services;

import com.hotel.interfaces.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioBasico implements Servicio {
    private static final Logger logger = LoggerFactory.getLogger(ServicioBasico.class); // Añadido
    private String descripcion;
    private double costo;

    public ServicioBasico(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
        logger.debug("Creando Servicio Básico: {} con costo {}", descripcion, costo);
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public double getCosto() {
        return costo;
    }
}