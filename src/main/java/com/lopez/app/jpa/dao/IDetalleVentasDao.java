package com.lopez.app.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.DetalleVenta1;

public interface IDetalleVentasDao extends CrudRepository<DetalleVenta1, Long> {

}
