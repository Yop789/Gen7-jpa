package com.lopez.app.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IClientesDao;
import com.lopez.app.jpa.dao.IVentaDao;
import com.lopez.app.jpa.dtos.VentaDTO;
import com.lopez.app.jpa.models.Venta;

@Component
public class VentaService implements IService<Venta,VentaDTO>{

    @Autowired  
    private IVentaDao ventasDao;

    @Autowired
    private IClientesDao clienteDao;

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
    public void guardar(VentaDTO t) {
        //Convertir el DTO a Venta

        this.ventasDao.save(convertirDTOAVentas(t));
    }

    @Override
    public void eliminar(Long id) {
        this.ventasDao.deleteById(id);
    }

    private Venta convertirDTOAVentas(VentaDTO v){
        Venta venta = new Venta();
        venta.setId(v.getId());
        venta.setFecha(v.getFecha());
        venta.setSubtotal(v.getSubtotal());
        venta.setDescuento(v.getDescuento());
        venta.setTotal(v.getTotal());
        venta.setFormaPago(v.getFormaPago());
        venta.setCliente(clienteDao.findById(v.getCliente()).get());
        return venta;
    }

}
