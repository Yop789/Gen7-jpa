package com.lopez.app.jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lopez.app.jpa.models.DetalleVenta1;
import com.lopez.app.jpa.services.IService;

@RestController
@RequestMapping("api/detalleVentas")
public class DetalleVentasController {

    @Autowired
    IService<DetalleVenta1> detalleVentasService;



    @GetMapping
    public List<DetalleVenta1> getAll() {
        return detalleVentasService.listar();
    }

    @PostMapping
    public Map<String, String> guardar(@RequestBody DetalleVenta1 d) {
        detalleVentasService.guardar(d);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Detalle guardada");
        return response;
    }


    @GetMapping("/obtener/{id}")
    public DetalleVenta1 optenerporId(@RequestParam(name = "id") Long id) {
        Optional<DetalleVenta1> detalle = detalleVentasService.getById(id);
        if (detalle.isPresent()) {
            return detalle.get();
        } else {
            return null;
        }
    }


    @DeleteMapping("/eliminar")
    public Map<String, String> eliminar(@RequestParam(name = "id") Long id) {
        detalleVentasService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Detalle eliminada");
        return response;
    }

    @PutMapping("/actualizar")
    public Map<String, String> actualizar(@RequestBody DetalleVenta1 d, @RequestParam(name = "id") Long id) {
        Optional<DetalleVenta1> detalle = detalleVentasService.getById(id);
        if (detalle.isPresent()) {
            d.setId(id);
            detalleVentasService.guardar(d);
        } else {
            throw new RuntimeException("Detalle no encontrada");
        }
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Detalle actualizada");
        return response;
    }

}
