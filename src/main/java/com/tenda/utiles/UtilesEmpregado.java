package com.tenda.utiles;

import com.tenda.hibernate.entity.EmpregadoEntity;
import com.tenda.hibernate.entity.HorasEmpregadoEntity;
import com.tenda.hibernate.entity.HorasEmpregadoPK;
import com.tenda.hibernate.entity.TendaEntity;
import com.tenda.hibernate.repository.EmpregadoRepositorio;
import com.tenda.hibernate.repository.HorasEmpregadoRepositorio;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * UtilesEmpregado
 */
public class UtilesEmpregado {
    
    /**
     * Añadir empregado a tenda
     * @param sc
     * @param session 
     */
    public static void anadirEmpregadoTenda (final Scanner sc, final Session session) {
    
        System.out.println("Añadir empleado a tienda, si no existe se crea:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {
            System.out.println("Tenda encontrada, introduce email empregado:");

            System.out.print("Email Empregado: ");
            String email = sc.nextLine();

            if (EmpregadoRepositorio.buscarEmpregadoPorEmail(session, email) != null) {

                System.out.println("O empregado existe.");
                System.out.print("Horas Empregado: ");
                String horasEmpregado = sc.nextLine();

                try {

                    EmpregadoEntity empregadoEntity = EmpregadoRepositorio.buscarEmpregadoPorEmail(session, email);

                    //Añadir horas
                    HorasEmpregadoPK horasEmpregadoPK = new HorasEmpregadoPK(tenda, empregadoEntity);
                    
                    HorasEmpregadoEntity horasEmpregadoEntity = new HorasEmpregadoEntity(
                            horasEmpregadoPK,
                            Integer.valueOf(horasEmpregado));
                    
                    HorasEmpregadoRepositorio.insertarDatos(session, horasEmpregadoEntity);

                } catch (NumberFormatException e) {
                    System.out.println("Formato Hora incorrecto");
                }

            } else {
                System.out.println("O empregado non existe, crearase:");

                System.out.print("Nome Empregado: ");
                String nomeEmpregado = sc.nextLine();

                System.out.print("Apelido Empregado: ");
                String apelidoEmpregado = sc.nextLine();

                System.out.print("Horas Empregado: ");
                String horasEmpregado = sc.nextLine();

                try {

                    EmpregadoEntity empregado = new EmpregadoEntity(nomeEmpregado, apelidoEmpregado, email);
                    
                    //Crear empregado
                    EmpregadoRepositorio.gardar(session, empregado);

                    //Añadir horas
                    HorasEmpregadoPK horasEmpregadoPK = new HorasEmpregadoPK(tenda, empregado);
                    
                    HorasEmpregadoEntity horasEmpregadoEntity = new HorasEmpregadoEntity(
                            horasEmpregadoPK,
                            Integer.valueOf(horasEmpregado));
                    
                    HorasEmpregadoRepositorio.insertarDatos(session, horasEmpregadoEntity);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Formato Hora incorrecto");
                }

            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
        
    }
    
    /**
     * Ver empregados
     * @param sc
     * @param session 
     */
    public static void verEmpregado (final Scanner sc, final Session session) {
    
        System.out.println("Lista Empregados:");
                        
        for (EmpregadoEntity empregadoAux : EmpregadoRepositorio.listarEmpregados(session)) {

            //Datos Empregado
            System.out.print("Nombre: ");
            System.out.println(empregadoAux.getNome());

            System.out.print("Apelidos: ");
            System.out.println(empregadoAux.getApelidos());

            System.out.print("Email: ");
            System.out.println(empregadoAux.getEmail());

            System.out.println("[");

            for (HorasEmpregadoEntity horasAux : HorasEmpregadoRepositorio.buscarPorEmpregado(session, empregadoAux)) {

                System.out.print("  Horas Semanais: ");
                System.out.println(horasAux.getHoras());

                System.out.print("  Tenda: ");
                System.out.println(horasAux.getHorasEmpregadoPK().getTenda().getNome());

            }

            System.out.println("]");
            System.out.println("");

        }
        
    }
    
    /**
     * Eliminar empregado
     * @param sc
     * @param session 
     */
    public static void eliminarEmpregado (final Scanner sc, final Session session) {
    
        System.out.println("Buscar empregado para eliminar:");
                        
        System.out.print("Email Empregado: ");
        String email = sc.nextLine();

        EmpregadoEntity empregadoVO = EmpregadoRepositorio.buscarEmpregadoPorEmail(session, email);

        if (empregadoVO != null) {

            if (UtilesTenda.comprobarBorrado(sc)) {
                EmpregadoRepositorio.eliminar(session, empregadoVO);
                System.out.println("Empregado eliminado.");
            } else {
                System.out.println("O empregado non existe");
            }

        } else {
            System.out.println("Empregado non encontrado.");                             
        }
        
    }
    
    /**
     * Eliminar empregado tenda
     * @param sc
     * @param session 
     */
    public static void eliminarEmpregadoTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para eliminar Empregado:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {
            System.out.println("Tenda encontrada, introduce email empregado:");

            System.out.print("Email Empregado: ");
            String emailEmpregado = sc.nextLine();

            EmpregadoEntity empregado = EmpregadoRepositorio.buscarEmpregadoPorEmail(session, emailEmpregado);

            if (empregado != null) {
                if (HorasEmpregadoRepositorio.buscarPorEmpregadoTenda(session, empregado, tenda) != null) {
                    if (UtilesTenda.comprobarBorrado(sc)) {
                        HorasEmpregadoPK horasEmpregado = new HorasEmpregadoPK(empregado);
                        HorasEmpregadoRepositorio.eliminar(session, new HorasEmpregadoEntity(horasEmpregado));
                        System.out.println("Empregado eliminado.");
                    } else {
                        System.out.println("O empregado non existe");
                    }
                } else {
                    System.out.println("Empregado non ten horas nesta tenda.");     
                }                               

            } else {
                System.out.println("Empregado non encontrado.");                             
            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
    
    }
    
}
