package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * HorasEmpregadoEntity
 */
@Entity
@Table(name="HorasEmpregado")
public class HorasEmpregadoEntity implements Serializable {
    
    @EmbeddedId
    private HorasEmpregadoPK horasEmpregadoPK;
    private Integer horas;

    public HorasEmpregadoEntity() {
    }

    public HorasEmpregadoEntity(HorasEmpregadoPK horasEmpregadoPK, Integer horas) {
        this.horasEmpregadoPK = horasEmpregadoPK;
        this.horas = horas;
    }

    public HorasEmpregadoPK getHorasEmpregadoPK() {
        return horasEmpregadoPK;
    }

    public void setHorasEmpregadoPK(HorasEmpregadoPK horasEmpregadoPK) {
        this.horasEmpregadoPK = horasEmpregadoPK;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
    
}
