package com.lopez.app.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long> {


    @Query("SELECT v FROM Venta v WHERE v.cliente.id = ?1")
    List<Venta> findByClienteNombreContaining(Long nombre);


}
