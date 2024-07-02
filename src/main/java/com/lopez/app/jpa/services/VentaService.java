package com.lopez.app.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IVentaDao;
import com.lopez.app.jpa.models.Venta;

@Component
public class VentaService implements IService<Venta>{

    @Autowired  
    private IVentaDao ventasDao;

    @Override
    public List<Venta> listar() {
        List <Venta> ventas = new ArrayList<>();
        ventas= (List<Venta>) ventasDao.findAll();
        return ventas;
        
    }

    @Override
    public Optional<Venta> getById(Long id) {
        Optional<Venta> venta = ventasDao.findById(id);
        return venta;
    }

    @Override
    public void guardar(Venta t) {
        this.ventasDao.save(t);
    }

    @Override
    public void eliminar(Long id) {
        this.ventasDao.deleteById(id);
    }

}
