package com.hotel.observer;

import com.hotel.models.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClienteObserver {
    private final static Logger logger = LoggerFactory.getLogger(ClienteObserver.class);
    private Cliente cliente;

    public ClienteObserver(Cliente cliente) {
        this.cliente = cliente;
        logger.info("CLiente Observer creado para cliente {}", cliente.getNombre());
    }
    public void update(String evento, Object data){
        String mensaje = "Estimodo/a " + cliente.getNombre()+ ",";
        switch (evento){
            case "RESERVA_CREADA": mensaje+= " su reserva ha sido creada exitosamente.";
            case "RESERVA_CONFIRMADA": mensaje +=  " su reserva ha sido confirmada.";
            case "RESERVA_CANCELADA": mensaje += " su reserva ha sido cancelada.";
            default: mensaje += "Hemos recibido una actualizacion de evento : " +evento;
        };

        recibirNotificacion(mensaje);
    }

    public void recibirNotificacion(String mensaje){
        logger.info("Notificacion para {}:{}", cliente.getNombre(), mensaje);
    }

}