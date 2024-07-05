package com.lopez.app.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.DetalleVenta1;


public interface IDetalleVentasDao extends CrudRepository<DetalleVenta1, Long> {

   @Query("SELECT v FROM DetalleVenta1 v WHERE v.venta.id = ?1")
    List<DetalleVenta1> findByClienteNombreContaining(Long id);


}
