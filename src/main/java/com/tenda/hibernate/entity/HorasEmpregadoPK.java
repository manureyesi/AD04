package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * HorasEmpregadoPK
 */
@Embeddable
public class HorasEmpregadoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="idTenda")
    private TendaEntity tenda;
    @ManyToOne
    @JoinColumn(name="idEmpregado")
    private EmpregadoEntity empregado;

    public HorasEmpregadoPK() {
    }

    public HorasEmpregadoPK(TendaEntity tenda, EmpregadoEntity empregado) {
        this.tenda = tenda;
        this.empregado = empregado;
    }

    public TendaEntity getTenda() {
        return tenda;
    }

    public void setTenda(TendaEntity tenda) {
        this.tenda = tenda;
    }

    public EmpregadoEntity getEmpregado() {
        return empregado;
    }

    public void setEmpregado(EmpregadoEntity empregado) {
        this.empregado = empregado;
    }
    
}
