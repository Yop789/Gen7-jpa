package com.lopez.app.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IProductoDao;
import com.lopez.app.jpa.models.Producto;

@Component
public class ProductosService implements IService<Producto>{

    @Autowired
    private IProductoDao productosDao; 

    @Override
    public List<Producto> listar() {
       List <Producto> productos = new ArrayList<>();
       productos= (List<Producto>) productosDao.findAll();
       return productos;
    }

    @Override
    public Optional<Producto> getById(Long id) {
        Optional<Producto> producto = productosDao.findById(id);
        return producto;
    }

    @Override
    public void guardar(Producto t) {
        this.productosDao.save(t);
    }

    @Override
    public void eliminar(Long id) {
        this.productosDao.deleteById(id);
    }

}
