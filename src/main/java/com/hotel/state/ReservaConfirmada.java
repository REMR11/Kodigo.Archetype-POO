package com.hotel.state;

import com.hotel.models.Reserva;
import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.EstadoReservaInvalidoException;

public class ReservaConfirmada implements ReservaState {
    @Override
    public void confirmar(Reserva reserva) throws ConfirmacionReservaException {
        logAccionNoPermitida(reserva, "Confirmar (ya está confirmada)");
        throw new ConfirmacionReservaException("La reserva ya está confirmada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new ReservaCancelada());
        logEstadoCambio(reserva, obtenerNombreEstado(), "Cancelada");
    }

    @Override
    public void pendificar(Reserva reserva) throws EstadoReservaInvalidoException {
        logAccionNoPermitida(reserva, "Pendificar (no se puede volver a pendiente desde confirmada)");
        throw new EstadoReservaInvalidoException("No se puede volver a estado 'Pendiente' desde 'Confirmada'.");
    }

    @Override
    public String obtenerNombreEstado() {
        return "Confirmada";
    }
}