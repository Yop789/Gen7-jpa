package com.lopez.app.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long> {

}
