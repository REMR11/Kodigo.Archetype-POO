package com.hotel.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioBasico {
    private static final Logger logger = LoggerFactory.getLogger(ServicioBasico.class);

    private String descripcion;
    private double costo;


    public ServicioBasico(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
        logger.debug("Creacion de servicio basico: {} con costo {}", descripcion, costo);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion(String descripcion, double costo){

    }
    public double getCosto(){

    }
}