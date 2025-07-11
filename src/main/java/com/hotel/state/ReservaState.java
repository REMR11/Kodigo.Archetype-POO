package com.hotel.state;

import com.hotel.exceptions.EstadoReservaInvalidoException;
import com.hotel.models.Reserva;
import com.hotel.exceptions.ConfirmacionReservaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ReservaState {

        Logger logger = LoggerFactory.getLogger(ReservaState.class);

        void confirmar(Reserva reserva) throws ConfirmacionReservaException;
        void cancelar(Reserva reserva);
        void pendificar(Reserva reserva) throws EstadoReservaInvalidoException;
        String obtenerNombreEstado();

        default void logEstadoCambio(Reserva reserva, String estadoOrigen, String estadoDestino) {
                logger.info("Reserva {} (ID: {}) cambió de estado de {} a {}.",
                        reserva.getCliente().getNombre(), reserva.getIdReserva(), estadoOrigen, estadoDestino);
        }

        default void logAccionNoPermitida(Reserva reserva, String accion) {
                logger.warn("Acción no permitida: {} para la reserva {} (ID: {}) en el estado {}.",
                        accion, reserva.getCliente().getNombre(), reserva.getIdReserva(), obtenerNombreEstado());
        }
}