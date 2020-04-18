package com.tenda.hibernate;

import com.tenda.exception.ADException;
import com.tenda.hibernate.entity.ClienteEntity;
import com.tenda.hibernate.entity.EmpregadoEntity;
import com.tenda.hibernate.entity.HorasEmpregadoEntity;
import com.tenda.hibernate.entity.ProductoEntity;
import com.tenda.hibernate.entity.ProductoStockEntity;
import com.tenda.hibernate.entity.ProvinciaEntity;
import com.tenda.hibernate.entity.TendaEntity;
import com.tenda.json.pojo.DatosDriver;
import com.tenda.json.pojo.DbConnection;
import com.tenda.utiles.Constantes;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * HibernateUtil
 */
public class HibernateUtil {
 
    private static SessionFactory sessionFactory;
    
    private static final String CADENA_STRING_MYSQL = "jdbc:mysql://";
    
    /**
     * Crear Sesion Factory Hibernate
     * @param datosDriver
     * @return
     * @throws ADException 
     */
    public static SessionFactory getSessionFactory(final DatosDriver datosDriver) throws ADException{
        
        //Se a sesion non se configurou, creamolo
        if(sessionFactory == null){
            try{
                
                Configuration conf = new Configuration();
                
                //Engadimos as propiedades
                Properties settings = new Properties();
                
                //Indicamos o conector da base de datos que vamos a usar
                settings.put(Environment.DRIVER, datosDriver.getHibernate().getDriver());
                
                //Indicamos a localización da base de datos que vamos a utilizar
                settings.put(Environment.URL, crearStringConexion(datosDriver.getDbConnection()));
                
                //Indicamos o usuario da base de datos
                settings.put(Environment.USER, datosDriver.getDbConnection().getUser());
                settings.put(Environment.PASS, datosDriver.getDbConnection().getPassword());
                
                //Indicamos o dialecto que ten que usar Hibernate 
                settings.put(Environment.DIALECT, datosDriver.getHibernate().getDialect());
                
                //Indicamos que se as táboas todas se borren e se volvan crear
                settings.put(Environment.HBM2DDL_AUTO, datosDriver.getHibernate().getHBM2DDLAUTO());
                
                //Indicamos que se mostre as operacións SQL que Hibernate leva a cabo
                settings.put(Environment.SHOW_SQL, datosDriver.getHibernate().getSHOWSQL());
                conf.setProperties(settings);
                
                //Engaidmos aquelas clases nas que queremos facer persistencia
                conf.addAnnotatedClass(ProvinciaEntity.class);
                conf.addAnnotatedClass(ProductoEntity.class);
                conf.addAnnotatedClass(TendaEntity.class);
                conf.addAnnotatedClass(ClienteEntity.class);
                conf.addAnnotatedClass(EmpregadoEntity.class);
                conf.addAnnotatedClass(ProductoStockEntity.class);
                conf.addAnnotatedClass(HorasEmpregadoEntity.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
                
            }catch(HibernateException e){
                throw new ADException("Error al crear la conexion", e);
            }
        }
        return sessionFactory;
    }
    
    /**
     * Crear Str para conexion con Mysql
     * @param dbConnection
     * @return
     * @throws ADException 
     */
    private static String crearStringConexion (final DbConnection dbConnection) throws ADException {
    
        StringBuilder connectionStr = new StringBuilder();
        
        //comprobar campos validos necesarios
        if (dbConnection == null || StringUtils.isBlank(dbConnection.getAddress()) 
                || StringUtils.isBlank(dbConnection.getName())) {
            throw new ADException("Error al crear la conexion por campos necesarios vacios.");
        }
        
        //Crear String Conexion Mysql
        connectionStr.append(CADENA_STRING_MYSQL);
        connectionStr.append(dbConnection.getAddress());
        //Comprobar Puerto
        if (StringUtils.isNotBlank(dbConnection.getPort())) {
            connectionStr.append(Constantes.DOS_PUNTOS);
            connectionStr.append(dbConnection.getPort());
        }
        connectionStr.append(Constantes.BARRA);
        connectionStr.append(dbConnection.getName());
        
        return connectionStr.toString();
        
    }
    
}
