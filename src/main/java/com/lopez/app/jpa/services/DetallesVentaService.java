package com.lopez.app.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IDetalleVentasDao;
import com.lopez.app.jpa.models.DetalleVenta1;

@Component
public class DetallesVentaService implements IService<DetalleVenta1>{


    @Autowired
    private IDetalleVentasDao detallesDao;

    @Override
    public List<DetalleVenta1> listar() {
        List <DetalleVenta1> detalles = new ArrayList<>();
        detalles= (List<DetalleVenta1>) detallesDao.findAll();
        return detalles;
    }

    @Override
    public Optional<DetalleVenta1> getById(Long id) {
        Optional<DetalleVenta1> detalle = detallesDao.findById(id);
        return detalle;
    }

    @Override
    public void guardar(DetalleVenta1 t) {
        this.detallesDao.save(t);   
    }

    @Override
    public void eliminar(Long id) {
        this.detallesDao.deleteById(id);
    } 

}
