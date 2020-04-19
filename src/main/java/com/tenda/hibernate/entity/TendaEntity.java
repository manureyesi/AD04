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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * TendaEntity
 */
@Entity
@Table(name="Tenda")
public class TendaEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name="idProvincia")
    private ProvinciaEntity provincia;
    private String cidade;

    public TendaEntity() {
    }

    public TendaEntity(String nome, ProvinciaEntity provincia, String cidade) {
        this.nome = nome;
        this.provincia = provincia;
        this.cidade = cidade;
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

    public ProvinciaEntity getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaEntity provincia) {
        this.provincia = provincia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
}
