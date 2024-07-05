package com.lopez.app.jpa.services;

import java.util.List;
import java.util.Optional;

public interface IService<T,S> {
    List<T> listar();
    Optional<T> getById(Long id);
    void guardar(S t);
    void eliminar(Long id);
    List<T> findByCliente(Long nombre);

}
