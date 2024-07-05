package com.lopez.app.jpa.controllers;

import java.util.ArrayList;
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

import com.lopez.app.jpa.dtos.VentaDTO;
import com.lopez.app.jpa.models.Venta;
import com.lopez.app.jpa.services.IService;

@RestController
@RequestMapping("api/ventas")
public class VentaController {

    @Autowired
    IService<Venta,VentaDTO> ventasService;


    @GetMapping
    public List<Venta> getAll() {
        return ventasService.listar();
    }


    @PostMapping
    public Map<String, String> guardar(@RequestBody VentaDTO v) {

        ventasService.guardar(v);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Venta guardada");
        return response;
    }

    @GetMapping("/obtener/{id}")
    public Venta optenerporId(@RequestParam(name = "id") Long id) {
        Optional<Venta> venta = ventasService.getById(id);
        if (venta.isPresent()) {
            return venta.get();
        } else {
            return null;
        }
    }



    @DeleteMapping("/eliminar")
    public Map<String, String> eliminar(@RequestParam(name = "id") Long id) {
        ventasService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Venta eliminada");
        return response;
    }

    @PutMapping("/actualizar")
    public Map<String, String> actualizar(@RequestBody VentaDTO v, @RequestParam(name = "id") Long id) {

        Optional<Venta> venta = ventasService.getById(id);
        if (venta.isPresent()) {
            v.setId(id);
            ventasService.guardar(v);
        } else {
            throw new RuntimeException("Venta no encontrada");
    }

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Venta actualizada");
        return response;
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Venta> obtenerVentasPorClienteId(@PathVariable (name = "clienteId") Long clienteId) {
        return ventasService.findByCliente(clienteId);
    }

}
