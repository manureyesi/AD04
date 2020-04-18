/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * ProductoEntity
 */
@Entity
@Table(name = "Producto")
public class ProductoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nome;
    private String descripcion;
    private Double prezo;

    public ProductoEntity() {
    }

    public ProductoEntity(int id, String nome, String descripcion, Double prezo) {
        this.id = id;
        this.nome = nome;
        this.descripcion = descripcion;
        this.prezo = prezo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrezo() {
        return prezo;
    }

    public void setPrezo(Double prezo) {
        this.prezo = prezo;
    }

}
