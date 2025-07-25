package com.hotel.decorator;

import com.hotel.factory.ServicioFactory;
import com.hotel.interfaces.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpaDecorator extends ServicioAdicional{
    private static final double COSTO_SPA = 50.0;
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class);

    public SpaDecorator(Servicio wrappee) {
        super(wrappee);
        logger.debug("Aplicando SpaDecorator");
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Acceso a spa ";
    }

    @Override
    public double getCosto() {
        return super.getCosto() +  COSTO_SPA;
    }
}