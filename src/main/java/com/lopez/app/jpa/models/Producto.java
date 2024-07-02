package com.lopez.app.jpa.models;

import com.lopez.app.jpa.enums.Categorias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//1
//jpa y Hibernate 
@Table(name = "jpa_productos")
@Entity
public class Producto {
    // Atribu -> variables -> caracteristicas
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ2")
    @SequenceGenerator(sequenceName = "customer_seq2", allocationSize = 1, name = "CUST_SEQ2")
    private Long id;


    //2 
    @Column(name = "name")
    private String name;


    //2
    @Column(name = "description")
    private String description;


    //2
    @Column(name = "stock")
    private Integer stock;


    //2
    @Column(name = "precio")
    private Float precio;


    //2
    @Column(name = "categoria")
    private Categorias categoria;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getStock() {
        return stock;
    }


    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public Float getPrecio() {
        return precio;
    }


    public void setPrecio(Float precio) {
        this.precio = precio;
    }


    public Categorias getCategoria() {
        return categoria;
    }


    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    // Getters y Setters

    

}
