package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.TendaEntity;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * TendasRepositorio
 */
public class TendasRepositorio {
    
    /**
     * Buscar Tenda por nome
     * @param session
     * @param nomeTenda
     * @return 
     */
    public static TendaEntity buscarTendaPorNome (final Session session, final String nomeTenda) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM TendaEntity t WHERE t.nome = :nome", TendaEntity.class);
        q1.setParameter("nome", nomeTenda);
        
        return (TendaEntity) q1.uniqueResult();
    }
    
    /**
     * Listar tendas
     * @param session 
     */
    public static List<TendaEntity> listarTendas (final Session session) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM TendaEntity t WHERE 1 = 1", TendaEntity.class);
        
        return q1.list();
        
    }
    
    /**
     * Insertar datos en DB de tenda
     * @param session
     * @param tenda 
     */
    public static void insertarDatos (final Session session, final TendaEntity tenda) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(tenda);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Eliminar tenda
     * @param session
     * @param tenda 
     */
    public static void eliminar (final Session session, final TendaEntity tenda) {
        
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.delete(tenda);
        
        //Commit
        transaction.commit();
        
    }
    
}
