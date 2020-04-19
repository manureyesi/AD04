package com.tenda.utiles;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tenda.exception.ADException;
import com.tenda.hibernate.entity.ProvinciaEntity;
import com.tenda.mapper.ProvinciasMapper;
import com.tenda.vo.ProvinciaVO;
import com.tenda.vo.ProvinciasVO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * UtilesProvinciasJson
 */
public class UtilesProvinciasJson {
    
    public final static String NOME_ARQUIVO_JSON = "provincias.json";
    
    /**
     * Cargar en BD archivos
     * @param session
     * @throws ADException 
     */
    public static void cargarEnDBArchivoDeProvincias (final Session session) throws ADException {
    
        //Facemos unha consulta
        Query q1 = session.createQuery("SELECT p FROM ProvinciaEntity p WHERE 1 = 1", ProvinciaEntity.class);
        
        List<ProvinciaEntity> listProvinciaEntitys = q1.list();
        
        //Lista provincias JSON
        List<ProvinciaVO> listaJson = cargarDatosProvinciasDeArchivo();
        
        //Transacción
        Transaction transaction = session.beginTransaction();
        
        ProvinciaEntity provinciaEntity;
        for (ProvinciaVO provinciaVO : listaJson) {
        
            //Mapper de Provincia
            provinciaEntity = ProvinciasMapper.mapperVOToEntity(provinciaVO);
            
            Boolean existeProvincia = Boolean.FALSE;
            
            for (ProvinciaEntity provinciaEntity1: listProvinciaEntitys) {
                if (provinciaEntity1.getId() == provinciaVO.getId()) {
                    existeProvincia = Boolean.TRUE;
                }
            }
            
            //Guardar Provicia si no existe
            if (!existeProvincia) {
                //Guardar Objeto
                session.save(provinciaEntity);
            }
            
        }
        
        //Commit
        transaction.commit();
        
                
    }
    
    /**
     * Cargar datos de archivo de provincias
     * @return
     * @throws ADException 
     */
    private static List<ProvinciaVO> cargarDatosProvinciasDeArchivo () throws ADException {
        
        ProvinciasVO provinciasVO = new ProvinciasVO();
        File arquivoProvincias = new File(NOME_ARQUIVO_JSON);
        
        //Comprobar si existe
        if (arquivoProvincias.exists()) {
        
            try {
                Gson gson = new Gson();
                provinciasVO = gson.fromJson(
                        new FileReader(arquivoProvincias),
                        ProvinciasVO.class);
                
            } catch (JsonSyntaxException | IOException e) {
                throw new ADException("Error al cargar fichero de provincias", e);
            }
            
        }
        
        return provinciasVO.getProvincias();
    }
    
}
