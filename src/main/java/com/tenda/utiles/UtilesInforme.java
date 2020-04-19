package com.tenda.utiles;

import com.tenda.hibernate.entity.ProductoEntity;
import com.tenda.hibernate.repository.ProductosRepositorio;
import com.tenda.json.JsonUtiles;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

/**
 *
 * UtilesInforme
 */
public class UtilesInforme {
    
    /**
     * Crear Informe en json
     * @param sc
     * @param session
     */
    public static void crearInforme (final Scanner sc, final Session session) {
    
        System.out.print("Nombre Archivo: ");
        String nombreArchivoStr = sc.nextLine();
        
        if (StringUtils.isNotBlank(nombreArchivoStr)) {
        
            StringBuilder nombreArchivo = new StringBuilder(nombreArchivoStr);
            nombreArchivo.append(Constantes.JSON);
            
            List<ProductoEntity> listaProductos =
                    ProductosRepositorio.listarProductos(session);
            
            //Guardar Json
            JsonUtiles.guardarDatosEnArchivo(listaProductos, nombreArchivo.toString());
            
        } else {
            System.err.println("El nombre del archivo no es correcto");
        }
    
    }
    
}
