package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.EmpregadoEntity;
import com.tenda.hibernate.entity.HorasEmpregadoEntity;
import com.tenda.hibernate.entity.TendaEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * HorasEmpregadoRepositorio
 */
public class HorasEmpregadoRepositorio {
    
    /**
     * Insertar datos
     * @param session
     * @param horasEmpregadoEntity 
     */
    public static void insertarDatos (final Session session, final HorasEmpregadoEntity horasEmpregadoEntity) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(horasEmpregadoEntity);
        
        //Commit
        transaction.commit();
        
    }
    
    /**
     * Buscar por empregado e tenda
     * @param session
     * @param empregadoEntity
     * @param tendaEntity 
     */
    public static HorasEmpregadoEntity buscarPorEmpregadoTenda (final Session session, final EmpregadoEntity empregadoEntity, final TendaEntity tendaEntity) {
        
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM HorasEmpregadoEntity t WHERE t.horasEmpregadoPK.empregado = :empregado and t.horasEmpregadoPK.tenda = :tenda", HorasEmpregadoEntity.class);
        q1.setParameter("empregado", empregadoEntity);
        q1.setParameter("tenda", tendaEntity);
        
        return (HorasEmpregadoEntity) q1.getResultList();
        
    }
    
    /**
     * Eliminar
     * @param session
     * @param horasEmpregadoEntity 
     */
    public static void eliminar (final Session session, final HorasEmpregadoEntity horasEmpregadoEntity) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.save(horasEmpregadoEntity);
        
        //Commit
        transaction.commit();
        
    }
    
}
