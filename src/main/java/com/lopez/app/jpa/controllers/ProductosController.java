package com.lopez.app.jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lopez.app.jpa.models.Producto;
import com.lopez.app.jpa.services.IService;

@RestController
@RequestMapping("api/productos")
public class ProductosController  {
    @Autowired
    IService<Producto,Producto> productosService;

    @GetMapping
    public List<Producto> getAll() {
        return productosService.listar();
    }

    @PostMapping
    public Map<String, String> guardar(@RequestBody Producto p) {
        productosService.guardar(p);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Producto guardado");
        return response;
    }

    @GetMapping("/{id}")
    public Producto optenerporId(@PathVariable(name = "id") Long id) {
        Optional<Producto> producto = productosService.getById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            return null;
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminar(@PathVariable(name = "id") Long id) {
        productosService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Producto eliminado");
        return response;
    }

    @PutMapping
    public Map<String, String> actualizar(@RequestBody Producto p) {
        Optional<Producto> producto = productosService.getById(p.getId());
        if (producto.isPresent()) {
            productosService.guardar(p);
        } else {
            throw new RuntimeException("No se encontro el producto");
        }
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Producto actualizado");
        return response;
    }

}
