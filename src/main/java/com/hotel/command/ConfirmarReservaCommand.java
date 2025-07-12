package com.hotel.command;

import com.hotel.core.GestorReservas;
import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.PagoNoProcesadoException;
import com.hotel.strategy.PagoStrategy;

public class ConfirmarReservaCommand implements Command {
    private GestorReservas gestor;
    private String idReserva;
    private PagoStrategy estrategiaPago;

    public ConfirmarReservaCommand(GestorReservas gestor, String idReserva, PagoStrategy estrategiaPago) {
        this.gestor = gestor;
        this.idReserva = idReserva;
        this.estrategiaPago = estrategiaPago;
    }

    @Override
    public void execute() throws ConfirmacionReservaException, PagoNoProcesadoException {
        gestor.confirmarReserva(idReserva, estrategiaPago);
    }
}