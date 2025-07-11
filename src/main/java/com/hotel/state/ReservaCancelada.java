package com.hotel.state;

import com.hotel.models.Reserva;
import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.EstadoReservaInvalidoException;

public class ReservaCancelada implements ReservaState {
    @Override
    public void confirmar(Reserva reserva) throws ConfirmacionReservaException {
        logAccionNoPermitida(reserva, "Confirmar (ya está cancelada)");
        throw new ConfirmacionReservaException("Una reserva cancelada no puede ser confirmada directamente.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        logAccionNoPermitida(reserva, "Cancelar (ya está cancelada)");
    }

    @Override
    public void pendificar(Reserva reserva) throws EstadoReservaInvalidoException {
        logAccionNoPermitida(reserva, "Pendificar (no se puede volver a pendiente desde cancelada)");
        throw new EstadoReservaInvalidoException("No se puede volver a estado 'Pendiente' desde 'Cancelada'.");
    }

    @Override
    public String obtenerNombreEstado() {
        return "Cancelada";
    }
}