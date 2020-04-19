package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.EmpregadoEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * EmpregadoRepositorio
 */
public class EmpregadoRepositorio {
    
    /**
     * Consultar empregado por email
     * @param session
     * @param email
     * @return 
     */
    public static EmpregadoEntity buscarEmpregadoPorEmail (final Session session, final String email) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM EmpregadoEntity t WHERE t.email = :email", EmpregadoEntity.class);
        q1.setParameter("email", email);
        
        return (EmpregadoEntity) q1.uniqueResult();
    }
    
    /**
     * Lista empregados
     * @param session
     * @return 
     */
    public static List<EmpregadoEntity> listarEmpregados (final Session session) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM EmpregadoEntity t WHERE 1 = 1", EmpregadoEntity.class);
        
        return q1.list();
        
    }
    
    /**
     * Gardar empregado
     * @param session
     * @param empregadoEntity 
     */
    public static void gardar (final Session session, final EmpregadoEntity empregadoEntity) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(empregadoEntity);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Eliminar empregado
     * @param session
     * @param empregadoEntity 
     */
    public static void eliminar (final Session session, final EmpregadoEntity empregadoEntity) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.delete(empregadoEntity);
        
        //Commit
        transaction.commit();
        
    }
    
}
