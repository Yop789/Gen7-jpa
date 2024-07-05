package com.lopez.app.jpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopez.app.jpa.enums.Categorias;

@RestController
@RequestMapping("/api/categoriaProducto")
public class CategoriaProducto  {

    @GetMapping
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        for(Categorias c : Categorias.values()) {
            list.add(c.toString());
        }
        return list;
    }

}
