package com.lopez.app.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.lopez.app.jpa.models.Cliente;

public interface IClientesDao extends CrudRepository<Cliente, Long> {

}
