package com.tenda.hibernate.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * ProductoStockEntity
 */
@Entity
@Table(name="ProductoStock")
public class ProductoStockEntity implements Serializable {
    
    @EmbeddedId
    private ProductoStockPK productoStockPK;
    private Integer stock;

    public ProductoStockEntity() {
    }

    public ProductoStockEntity(ProductoStockPK productoStockPK, Integer stock) {
        this.productoStockPK = productoStockPK;
        this.stock = stock;
    }

    public ProductoStockPK getProductoStockPK() {
        return productoStockPK;
    }

    public void setProductoStockPK(ProductoStockPK productoStockPK) {
        this.productoStockPK = productoStockPK;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}
