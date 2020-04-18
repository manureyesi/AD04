package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * ProductoStockPK
 */
@Embeddable
public class ProductoStockPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="idTenda")
    private TendaEntity tenda;
    @ManyToOne
    @JoinColumn(name="idProducto")
    private ProductoEntity productoEntity;

    public ProductoStockPK() {
    }

    public ProductoStockPK(TendaEntity tenda, ProductoEntity productoEntity) {
        this.tenda = tenda;
        this.productoEntity = productoEntity;
    }

    public TendaEntity getTenda() {
        return tenda;
    }

    public void setTenda(TendaEntity tenda) {
        this.tenda = tenda;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }
    
}
