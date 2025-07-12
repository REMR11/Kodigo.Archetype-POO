package com.hotel.observer;

import com.hotel.factory.HabitacionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NotificadorReservas {
    private static final Logger logger = LoggerFactory.getLogger(HabitacionFactory.class);

    private List<ClienteObserver> clienteObservers;
    private LoggerObserver loggerObserver;

    public NotificadorReservas() {
        this.clienteObservers = new ArrayList<>();
        this.loggerObserver = new LoggerObserver();
    }

    public void agregarObservador(ClienteObserver observer){
        if (observer !=null){
            this.clienteObservers.add(observer);
            logger.debug("ClienteObserver aniadido: {}", observer.getClass().getSigners());
        }
    }

    public void removerObservador(ClienteObserver observer){
        if (observer !=null){
            this.clienteObservers.remove(observer);
            logger.debug("ClienteObserver removido: {}", observer.getClass().getSigners());
        }
    }

    public void notificar(String evento, Object data){
        logger.info("Notificacion evento: {} ", evento);
        for (ClienteObserver clienteObserver : clienteObservers) {
            clienteObserver.update(evento, data);
        }
        loggerObserver.update(evento,data);
    }

}