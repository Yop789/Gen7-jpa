package com.lopez.app.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IDetalleVentasDao;
import com.lopez.app.jpa.dao.IProductoDao;
import com.lopez.app.jpa.dao.IVentaDao;
import com.lopez.app.jpa.dtos.DetalleVentaDTO;
import com.lopez.app.jpa.models.DetalleVenta1;

@Component
public class DetallesVentaService implements IService<DetalleVenta1,DetalleVentaDTO>{


    @Autowired
    private IDetalleVentasDao detallesDao;

    @Autowired
    private IVentaDao ventaDao;

    @Autowired
    private IProductoDao productoDao;
    

    
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
    public void guardar(DetalleVentaDTO t) {
        this.detallesDao.save(convertirDTOADetalles(t));   
    }

    @Override
    public void eliminar(Long id) {
        this.detallesDao.deleteById(id);
    } 

    private DetalleVenta1 convertirDTOADetalles(DetalleVentaDTO d){
        DetalleVenta1 detalle = new DetalleVenta1();
        detalle.setId(d.getId());
        detalle.setVenta(ventaDao.findById(d.getVenta()).get());
        detalle.setProducto(productoDao.findById(d.getProducto()).get());
        detalle.setCantidad(d.getCantidad());
        return detalle;
    }

    @Override
    public List<DetalleVenta1> findByCliente(Long nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCliente'");
    }

}
