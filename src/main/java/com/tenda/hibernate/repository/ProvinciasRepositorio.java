package com.tenda.hibernate.repository;

import com.tenda.hibernate.entity.ProvinciaEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * ProvinciasRepositorio
 */
public class ProvinciasRepositorio {
    
    /**
     * Buscar provincia por nome
     * @param session
     * @param nombreProvincia
     * @return 
     */
    public static ProvinciaEntity buscarProvinciaPorNombre (final Session session, final String nombreProvincia) {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT p FROM ProvinciaEntity p WHERE p.nome = :nome", ProvinciaEntity.class);
        q1.setParameter("nome", nombreProvincia);
        
        return (ProvinciaEntity) q1.uniqueResult();
    }
    
}
