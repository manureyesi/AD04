package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.ProductoEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * ProvinciasRepositorio
 */
public class ProductosRepositorio {
       
    /**
     * Insetar Producto
     * @param session
     * @param producto
     */
    public static void insertarDatos (final Session session, final ProductoEntity producto) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(producto);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Listar todos os productos
     * @param session
     * @return 
     */
    public static List<ProductoEntity> listarProductos (final Session session) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoEntity t WHERE 1 = 1", ProductoEntity.class);
        
        return q1.list();
    }
    
    /**
     * Buscar Producto por nome
     * @param session
     * @param nombreProducto
     * @return 
     */
    public static ProductoEntity buscarProductoPorNome (final Session session, final String nombreProducto) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ProductoEntity t WHERE t.nome = :nome", ProductoEntity.class);
        q1.setParameter("nome", nombreProducto);
        
        return (ProductoEntity) q1.uniqueResult();
    }
    
    /**
     * Eliminar producto
     * @param session
     * @param producto 
     */
    public static void eliminar (final Session session, final ProductoEntity producto) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.delete(producto);
        
        //Commit
        transaction.commit();
        
    }
    
}
