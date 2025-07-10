package com.hotel.state;

public interface ReservaState {
        void confirmar();
        void cancelar();
        void pendificar();
        String obtenerNombreEstado();
}