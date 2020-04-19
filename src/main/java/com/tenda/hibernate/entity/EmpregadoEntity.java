package com.tenda.hibernate.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * EmpregadoEntity
 */
@Entity
@Table(name="Empregado")
public class EmpregadoEntity implements Serializable {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nome;
    private String apelidos;
    @Column(unique=true)
    private String email;
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="idEmpregado")
    private List<HorasEmpregadoEntity> listaHoras;

    public EmpregadoEntity() {
    }

    public EmpregadoEntity(String nome, String apelidos, String email) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.email = email;
    }

    public EmpregadoEntity(String nome, String apelidos, String email, List<HorasEmpregadoEntity> listaHoras) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.email = email;
        this.listaHoras = listaHoras;
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

    public List<HorasEmpregadoEntity> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<HorasEmpregadoEntity> listaHoras) {
        this.listaHoras = listaHoras;
    }
    
}
