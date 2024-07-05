package com.lopez.app.jpa.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.lopez.app.jpa.dtos.VentaDTO;
import com.lopez.app.jpa.models.Cliente;
import com.lopez.app.jpa.services.ClienteServicesNuevo;
import com.lopez.app.jpa.services.ClientesService;
import com.lopez.app.jpa.services.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/clientes")

public class ClientesController {

    @Autowired
    @Qualifier("clientesViejo")
    IService<Cliente,Cliente> clientesService;
    
    // public ClientesController(ClientesService cs) {
    //     this.clientesService = cs;
        
    // }
    
    

    @GetMapping
    public List<Cliente> getAll() {
        return clientesService.listar();
    }

    @PostMapping
    public Map<String, String> guardar(@RequestBody Cliente c) {
        clientesService.guardar(c);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cliente guardado");
        return response;

    }

    @GetMapping("/obtener/{id}")
    public Cliente optenerporId(@PathVariable(name = "id") Long id) {
        Optional<Cliente> cliente = clientesService.getById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminar(@PathVariable(name = "id") Long id) {
        clientesService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cliente eliminado");
        return response;
    }

    @PutMapping("/actualizar")
    public Map<String, String> actualizar(@RequestBody Cliente c) {
        Optional<Cliente> cliente = clientesService.getById(c.getId());
        if (cliente.isPresent()) {
            clientesService.guardar(c);

        } else {
            throw new RuntimeException("No se encontro el cliente");
        }

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cliente actualizado");
        return response;

    }
}
