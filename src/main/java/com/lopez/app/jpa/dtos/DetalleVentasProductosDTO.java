package com.lopez.app.jpa.dtos;

import java.util.List;

import com.lopez.app.jpa.models.Producto;

public class DetalleVentasProductosDTO {

    String nombre;
    VentaDTO venta;
    List<Producto> productos;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public VentaDTO getVenta() {
        return venta;
    }
    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    

}
