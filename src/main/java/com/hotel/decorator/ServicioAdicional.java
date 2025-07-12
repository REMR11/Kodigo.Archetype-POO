package com.hotel.decorator;

import com.hotel.factory.ServicioFactory;
import com.hotel.interfaces.Servicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioAdicional {
    private static final Logger logger = LoggerFactory.getLogger(ServicioFactory.class); // Añadido

    protected Servicio wrappee;

    public ServicioAdicional(Servicio wrappee) {
        this.wrappee = wrappee;
        logger.debug("CREANDO SERVICIO ADICIONAL PARA: {}", wrappee.getDescripcion());
    }
    public String getDescripcion(){
        return wrappee.getDescripcion();
    }
    public double getCosto(){
        return wrappee.getCosto();
    }

}