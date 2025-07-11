package com.hotel.state;

import com.hotel.models.Reserva;
import com.hotel.exceptions.ConfirmacionReservaException;

public class ReservaPendiente implements ReservaState {
    @Override
    public void confirmar(Reserva reserva) throws ConfirmacionReservaException {
        if (reserva.getHabitacion().esDisponible()) {
            reserva.setEstado(new ReservaConfirmada());
            logEstadoCambio(reserva, obtenerNombreEstado(), "Confirmada");
        } else {
            logAccionNoPermitida(reserva, "Confirmar (habitación no disponible)");
            throw new ConfirmacionReservaException("No se puede confirmar: la habitación " + reserva.getHabitacion().getNumero() + " no está disponible.");
        }
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new ReservaCancelada());
        logEstadoCambio(reserva, obtenerNombreEstado(), "Cancelada");
    }

    @Override
    public void pendificar(Reserva reserva) {
        logAccionNoPermitida(reserva, "Pendificar (ya está en este estado)");
    }

    @Override
    public String obtenerNombreEstado() {
        return "Pendiente";
    }
}