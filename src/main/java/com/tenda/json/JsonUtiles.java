package com.tenda.json;

import com.google.gson.Gson;
import com.tenda.exception.ADException;
import com.tenda.json.pojo.DatosDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * JsonUtiles
 */
public class JsonUtiles {
    
    /**
     * Leer archivo de Driver opasando File
     * @param nombreArchivoConexion
     * @return
     * @throws ADException si el File es nulo o no se puede mappear el archivo
     */
    public static DatosDriver leerArchivoJson (final File nombreArchivoConexion) throws ADException {
        
        DatosDriver datosDiver = null;
        
        //Comprobar archivo
        if (nombreArchivoConexion == null) {
            throw new ADException("El archivo es nulo");
        }
        
        //Comprobr si existe el archivo
        if (!nombreArchivoConexion.exists()) {
            throw new ADException("El archivo no existe");
        }
        
        Gson gson = new Gson();

        try {
            //Mapear file a objeto con Gson
            datosDiver = gson.fromJson(new FileReader(nombreArchivoConexion), DatosDriver.class);
        } catch (IOException ex) {
            throw new ADException("Error al leer Archivo", ex);
        }
        
        return datosDiver;
    }
    
}
