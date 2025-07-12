package com.hotel.decorator;

import com.hotel.factory.ServicioFactory;
import com.hotel.interfaces.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesayunoDecorator extends ServicioAdicional{
    private static final double COSTO_DESAYUNO = 25.0;
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class);

    public DesayunoDecorator(Servicio wrappee) {
        super(wrappee);
        logger.debug("Aplicando desayunoDecorator");
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Desayuno continental";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + COSTO_DESAYUNO;
    }
}