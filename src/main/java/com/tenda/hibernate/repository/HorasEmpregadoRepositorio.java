package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.EmpregadoEntity;
import com.tenda.hibernate.entity.HorasEmpregadoEntity;
import com.tenda.hibernate.entity.TendaEntity;
import java.util.List;
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
     * Actualizar horas
     * @param session
     * @param horasEmpregadoEntity 
     */
    public static void actualizar (final Session session, final HorasEmpregadoEntity horasEmpregadoEntity) {
    
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        //Gardar tenda
        session.update(horasEmpregadoEntity);
        
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
        
        return (HorasEmpregadoEntity) q1.uniqueResult();
        
    }
    
    /**
     * Buscar HOras de un empregado
     * @param session
     * @param empregadoEntity
     * @return 
     */
    public static List<HorasEmpregadoEntity> buscarPorEmpregado (final Session session, final EmpregadoEntity empregadoEntity) {
        
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM HorasEmpregadoEntity t WHERE t.horasEmpregadoPK.empregado = :empregado", HorasEmpregadoEntity.class);
        q1.setParameter("empregado", empregadoEntity);
        
        return q1.list();
        
    }
    
    /**
     * Buscar por Tenda
     * @param session
     * @param tendaEntity
     * @return 
     */
    public static List<HorasEmpregadoEntity> buscarPorTenda (final Session session, final TendaEntity tendaEntity) {
        
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT t FROM HorasEmpregadoEntity t WHERE t.horasEmpregadoPK.tenda = :tenda", HorasEmpregadoEntity.class);
        q1.setParameter("tenda", tendaEntity);
        
        return q1.list();
        
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
        session.delete(horasEmpregadoEntity);
        
        //Commit
        transaction.commit();
        
    }
    
}
