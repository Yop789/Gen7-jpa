package com.lopez.app.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
