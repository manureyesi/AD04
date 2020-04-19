package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.ProductoEntity;
import com.tenda.hibernate.entity.ProductoStockEntity;
import com.tenda.hibernate.entity.TendaEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * ProductoStockRepositorio
 */
public class ProductoStockRepositorio {
    
    /**
     * Insertar Stock de un producto
     * @param session
     * @param productoStock 
     */
    public static void insertarDatos (final Session session, final ProductoStockEntity productoStock) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(productoStock);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Buscar producto por tenda
     * @param session
     * @param tendaEntity
     * @param productoEntity
     * @return 
     */
    public static ProductoStockEntity buscarProductoPorTenda (final Session session, final TendaEntity tendaEntity, final ProductoEntity productoEntity) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoStockEntity t WHERE t.productoStockPK.tenda = :tenda and t.productoStockPK.productoEntity = :producto", ProductoStockEntity.class);
        q1.setParameter("tenda", tendaEntity);
        q1.setParameter("producto", productoEntity);
        
        return (ProductoStockEntity) q1.uniqueResult();
        
    }
    
    /**
     * Buscar productos por tenda
     * @param session
     * @param tendaEntity
     * @return 
     */
    public static List<ProductoStockEntity> buscarProductosPorTenda (final Session session, final TendaEntity tendaEntity) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoStockEntity t WHERE t.productoStockPK.tenda = :tenda", ProductoStockEntity.class);
        q1.setParameter("tenda", tendaEntity);
        
        return q1.getResultList();
    
    }
    
    /**
     * Buscar Producto
     * @param session
     * @param productoEntity
     * @return 
     */
    public static List<ProductoStockEntity> buscarProductos (final Session session, final ProductoEntity productoEntity) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoStockEntity t WHERE t.productoStockPK.productoEntity = :producto", ProductoStockEntity.class);
        q1.setParameter("producto", productoEntity);
        
        return q1.getResultList();
    
    }
    
    public static List<ProductoStockEntity> buscarTodosLosProductos (final Session session) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoStockEntity t WHERE 1 = 1", ProductoStockEntity.class);
        
        return q1.list();
    
    }
    
    /**
     * Actualizar datos
     * @param session
     * @param productoStock 
     */
    public static void update (final Session session, final ProductoStockEntity productoStock) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.update(productoStock);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Eliminar producto de Stock
     * @param session
     * @param productoStock 
     */
    public static void eliminar (final Session session, final ProductoStockEntity productoStock) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.delete(productoStock);
        
        //Commit
        transaction.commit();
        
    }
    
}
