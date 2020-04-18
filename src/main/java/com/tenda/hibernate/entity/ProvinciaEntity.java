package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * ProvinciaEntity
 */
@Entity
@Table(name="Provincia")
public class ProvinciaEntity implements Serializable {
    
    @Id
    private Integer id;
    private String nome;

    public ProvinciaEntity() {
    }

    public ProvinciaEntity(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
