package com.hotel.command;

import com.hotel.exceptions.ConfirmacionReservaException;
import com.hotel.exceptions.EstadoReservaInvalidoException;
import com.hotel.exceptions.PagoNoProcesadoException;

public interface Command {
    void execute() throws ConfirmacionReservaException, PagoNoProcesadoException, EstadoReservaInvalidoException;
}