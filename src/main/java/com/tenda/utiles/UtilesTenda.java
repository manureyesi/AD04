package com.tenda.utiles;

import com.tenda.hibernate.entity.ProvinciaEntity;
import com.tenda.hibernate.entity.TendaEntity;
import com.tenda.hibernate.repository.ProvinciasRepositorio;
import com.tenda.hibernate.repository.TendasRepositorio;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * UtilesTenda
 */
public class UtilesTenda {
    
    /**
     * Crear tenda
     * @param sc Scanner
     * @param session 
     */
    public static void crearTenda (final Scanner sc, final Session session) {
    
        System.out.println("Añadir Tienda:");

        //Engadir tenda                        
        System.out.print("Nome: ");
        String nomeTenda = sc.nextLine();

        System.out.print("Cidade: ");
        String cidadeTenda = sc.nextLine();

        TendaEntity tendaEntity = TendasRepositorio.buscarTendaPorNome(session, nomeTenda);
        if (tendaEntity == null) {

            System.out.print("Provincia: ");
            String nomeProvincia = sc.nextLine();

            //Buscar si existe Provincia
            ProvinciaEntity provincia = 
                    ProvinciasRepositorio.buscarProvinciaPorNombre(session, nomeProvincia);

            if (provincia != null) {
                //En caso de non existir Tenda crease
                TendasRepositorio.insertarDatos(session, new TendaEntity(nomeTenda, provincia, cidadeTenda));
            } else {
                System.err.println("Error al buscar Provincia.");
            }

        } else {
            System.err.println("Tenda duplicada, non se creara.");
        }
        
    }
    
    /**
     * Listar Tendas
     * @param sc
     * @param session 
     */
    public static void listarTendas (final Scanner sc, final Session session) {
    
        System.out.println("Lista de Tiendas:");

        for (TendaEntity tendaAux : TendasRepositorio.listarTendas(session)) {
            //Datos tiendas
            System.out.print("Nombre: ");
            System.out.println(tendaAux.getNome());

            System.out.print("Cidade: ");
            System.out.println(tendaAux.getCidade());

            System.out.print("  Codigo Provincia: ");
            System.out.println(tendaAux.getProvincia().getId());

            System.out.print("  Nombre Provincia: ");
            System.out.println(tendaAux.getProvincia().getNome());
            System.out.println("");

        }
    
    }
    
    /**
     * Eliminar tenda
     * @param sc
     * @param session 
     */
    public static void eliminarTenda (final Scanner sc, final Session session) {
    
        //Buscar Tenda a eliminar
        System.out.print("Nome Tenda: ");

        //Buscar Tenda
        TendaEntity tenda = buscarTenda(sc, session);

        if (tenda != null) {
            if (comprobarBorrado(sc)) {
                TendasRepositorio.eliminar(session, tenda);
            }
        } else {
            System.err.println("A tenda a borrar non existe.");
        }
        
    }
    
    /**
     * Buscar tenda
     * @param sc
     * @param session
     * @return 
     */
    public static TendaEntity buscarTenda (final Scanner sc, final Session session) {
    
        String nomeTenda = sc.nextLine();

        //Buscar Tenda        
        return TendasRepositorio.buscarTendaPorNome(session, nomeTenda);
        
    }
    
    /**
     * Comprobar Borrado
     * @param sc
     * @return 
     */
    public static Boolean comprobarBorrado (final Scanner sc) {
    
        Boolean permitirBorrado = Boolean.FALSE;
        
        System.out.println("Seguro que desexa borrar (S/N)?");
                            
        String comprobarBorrar = sc.nextLine();
        
        if (!comprobarBorrar.equalsIgnoreCase("N")) {
            
            System.out.println("Borrado Correctamente.");
            permitirBorrado = Boolean.TRUE;
            
        } else {

            System.out.println("Cancelando Borrado.");

        }
        
        return permitirBorrado;
        
    }
    
}
