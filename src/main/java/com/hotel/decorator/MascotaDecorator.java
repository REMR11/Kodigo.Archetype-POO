package com.hotel.decorator;

import com.hotel.factory.ServicioFactory;
import com.hotel.interfaces.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MascotaDecorator extends ServicioAdicional{
    private static final double COSTO_MASCOTA = 30.0;
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class);

    public MascotaDecorator(Servicio wrappee) {
        super(wrappee);
        logger.debug("Aplicando MascotaDecorator");
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Permiso para mascota";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + COSTO_MASCOTA;
    }
}