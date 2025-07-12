package com.hotel.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerObserver {
    private final static Logger logger = LoggerFactory.getLogger(ClienteObserver.class);

    public void update(String evento, Object data){
        logger.info("[AUDITORIA] Evento: {}, Data: ", evento, data);
    }

}