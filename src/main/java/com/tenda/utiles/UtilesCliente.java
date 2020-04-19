package com.tenda.utiles;

import com.tenda.hibernate.entity.ClienteEntity;
import com.tenda.hibernate.repository.ClientesRepositorio;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * UtilesCliente
 */
public class UtilesCliente {
    
    /**
     * Añadir cliente
     * @param sc
     * @param session 
     */
    public static void anadirCliente (final Scanner sc, final Session session) {
    
        System.out.println("Engair Cliente:");

        System.out.print("Nome Cliente: ");
        String nomeCliente = sc.nextLine();

        System.out.print("Apelido Cliente: ");
        String apelidoCliente = sc.nextLine();

        System.out.print("Email Cliente: ");
        String emailCliente = sc.nextLine();

        ClienteEntity cliente = new ClienteEntity(
                nomeCliente, 
                apelidoCliente, 
                emailCliente);

        if (ClientesRepositorio.buscarPorEmail(session, emailCliente) == null) {
            //Añadir cliente
            ClientesRepositorio.insertarDatos(session, cliente);
            System.out.println("Cliente añadido con exito.");
        } else {
            System.out.println("El cliente ya existe.");
        }
        
    }
    
    /**
     * Ver lista de clientes
     * @param sc
     * @param session 
     */
    public static void verClientes (final Scanner sc, final Session session) {
    
        //Ver Clientes
        System.out.println("Lista de Clientes:");

        for (ClienteEntity clienteAux : ClientesRepositorio.listarClientes(session)) {
            //Datos Productos
            System.out.print("Nombre: ");
            System.out.println(clienteAux.getNome());

            System.out.print("Apellido: ");
            System.out.println(clienteAux.getApelidos());

            System.out.print("Email: ");
            System.out.println(clienteAux.getEmail());
            System.out.println("");

        }
        
    }
    
    /**
     * Eliminar cliente
     * @param sc
     * @param session
     */
    public static void eliminar (final Scanner sc, final Session session) {
    
        System.out.println("Buscar cliente a eliminar:");

        System.out.print("Mail Cliente: ");
        String mailClienteEliminar = sc.nextLine();

        ClienteEntity clienteEliminar =
                ClientesRepositorio.buscarPorEmail(session, mailClienteEliminar);

        if (clienteEliminar != null) {
            if (UtilesTenda.comprobarBorrado(sc)) {
                ClientesRepositorio.eliminar(session, clienteEliminar);
            }
        } else {
            System.err.println("El cliente no existe.");
        }
        
    }
    
}
