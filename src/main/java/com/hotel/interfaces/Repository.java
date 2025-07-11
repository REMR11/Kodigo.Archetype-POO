package com.hotel.interfaces;

public interface Repository<T> {
    void guardar(T entidad);
    T obtenerPorId(String id);
    void actualizar(T entidad);
    void eliminar(String id);
}