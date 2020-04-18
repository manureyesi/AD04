package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * ClienteEntity
 */
@Entity
@Table(name="Cliente")
public class ClienteEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nome;
    private String apelidos;
    @Column(unique=true)
    private String email;

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String apelidos, String email) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
