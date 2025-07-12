package com.hotel.command;

import com.hotel.core.GestorReservas;
import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.EstadoReservaInvalidoException;
import com.hotel.exceptions.PagoNoProcesadoException;

public class CancelarReservaCommand implements Command{
    private GestorReservas gestor;
    private String idReserva;

    public CancelarReservaCommand(GestorReservas gestor, String idReserva) {
        this.gestor = gestor;
        this.idReserva = idReserva;
    }

    @Override
    public void execute() throws ConfirmacionReservaException, PagoNoProcesadoException, EstadoReservaInvalidoException {
        gestor.cancelarReserva(idReserva);
    }
}