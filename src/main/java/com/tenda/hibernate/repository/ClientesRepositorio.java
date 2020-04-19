package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.ClienteEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * ClientesRepositorio
 */
public class ClientesRepositorio {
    
    /**
     * Insertar datos en tabla de clientes
     * @param session
     * @param cliente 
     */
    public static void insertarDatos (final Session session, final ClienteEntity cliente) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(cliente);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Mostrar todos los clientes
     * @param session
     * @return 
     */
    public static List<ClienteEntity> listarClientes (final Session session) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ClienteEntity t WHERE 1 = 1", ClienteEntity.class);
        
        return q1.list();
    }
    
    /**
     * Buscar cliente por mail
     * @param session
     * @param emailCliente
     * @return
     */
    public static ClienteEntity buscarPorEmail (final Session session, final String emailCliente) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM ClienteEntity t WHERE t.email = :email", ClienteEntity.class);
        q1.setParameter("email", emailCliente);
        
        return (ClienteEntity) q1.uniqueResult();
    }
    
    /**
     * Eliminar Cliente
     * @param session
     * @param cliente
     */
    public static void eliminar (final Session session, final ClienteEntity cliente) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.delete(cliente);
        
        //Commit
        transaction.commit();
        
    }
    
}
