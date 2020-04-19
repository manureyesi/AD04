package com.tenda.main;

import com.rss.elPais.LeerRSSElPais;
import com.tenda.exception.ADException;
import com.tenda.hibernate.HibernateUtil;
import com.tenda.json.JsonUtiles;
import com.tenda.json.pojo.DatosDriver;
import com.tenda.utiles.DatosMenuEnum;
import com.tenda.utiles.MenuUtiles;
import com.tenda.utiles.UtilesCliente;
import com.tenda.utiles.UtilesEmpregado;
import com.tenda.utiles.UtilesInforme;
import com.tenda.utiles.UtilesProducto;
import com.tenda.utiles.UtilesProvinciasJson;
import com.tenda.utiles.UtilesTenda;
import java.io.File;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * Tenda
 */
public class Tenda {

    private static final Scanner sc = new Scanner (System.in);
    
    private static final String NOMBRE_ARCHIVO_CONFIGURACION_JSON = "Datos_driver.json";
    
    private static SessionFactory sessionFactory;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {

            // Cargar configuracion
            DatosDriver datosDriver = JsonUtiles.leerArchivoJson(new File(NOMBRE_ARCHIVO_CONFIGURACION_JSON));

            //Cargar Conexion
            sessionFactory = HibernateUtil.getSessionFactory(datosDriver);

            Session session = sessionFactory.openSession();
            
            //Insertar Provincias
            UtilesProvinciasJson.cargarEnDBArchivoDeProvincias(session);
            
            Integer posicionMenu = 0;

            while (!posicionMenu.equals(DatosMenuEnum.SAIR.getIdMenu())) {

                //Pintar Menu
                MenuUtiles.mostrarMenu();

                try {

                    posicionMenu = sc.nextInt();
                    sc.nextLine();

                    switch (DatosMenuEnum.buscarPorId(posicionMenu)) {

                        case ENGADIR_TENDA:

                            UtilesTenda.crearTenda(sc, session);
                            pararProgramaAtaEnter();

                            break;

                        case VER_TENDAS:

                            //Ver Tendas
                            UtilesTenda.listarTendas(sc, session);
                            pararProgramaAtaEnter();

                            break;

                        case ELIMINAR_TENDA:

                            //Eliminar Tenda
                            UtilesTenda.eliminarTenda(sc, session);
                            pararProgramaAtaEnter();

                            break;

                        case ENGADIR_PRODUCTO:
                            
                            //ENGADIR PRODUCTO
                            UtilesProducto.anadirProducto(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                           
                        case ENGADIR_PRODUCTOS_TENDA:
                            
                            //engadir producto a tenda
                            UtilesProducto.anadirProductoTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                        
                        case VER_PRODUCTOS:
                            
                            //Ver Productos
                            UtilesProducto.verProductos(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case VER_PRODUCTOS_TENDA:
                            
                            //Ver productos por tenda
                            UtilesProducto.verProductosPorTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ACTUALIZAR_STOCK_PRODUCTO_TENDA:
                            
                            //Actualizar Stock
                            UtilesProducto.actualizarStockProductoTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case MOSTRAR_STOCK_TENDA:
                            
                            //Mostrar stock producto por tenda
                            UtilesProducto.verStockPorProdcutoTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ELIMINAR_PRODUCTO_TENDA:
                            
                            //Eliminar producto de tenda
                            UtilesProducto.eliminarProductoPorTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ELIMINAR_PRODUCTO:
                            
                            //Eliminar producto
                            UtilesProducto.eliminarProducto(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ENGADIR_CLIENTE:

                            //Engadir Cliente
                            UtilesCliente.anadirCliente(sc, session);
                            pararProgramaAtaEnter();                  
                            
                            break;
                        
                        case MOSTRAR_CLIENTES:
                            
                            //Engadir Cliente
                            UtilesCliente.verClientes(sc, session);
                            pararProgramaAtaEnter(); 
                            
                            break;
                        
                        case ELIMINAR_CLIENTE:
                            
                            //Eliminar cliente
                            UtilesCliente.eliminar(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ANADIR_EMPREGADO_TENDA:
                            
                            //engadir empregado a tenda
                            UtilesEmpregado.anadirEmpregadoTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case VER_EMPREGADOS:
                            
                            //Ver empregados
                            UtilesEmpregado.verEmpregado(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ELIMINAR_EMPREADO:
                            
                            //Eliminar empregados
                            UtilesEmpregado.eliminarEmpregado(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case ELIMINAR_EMPREADO_TENDA:
                            
                            //Eliminar empregado tenda
                            UtilesEmpregado.eliminarEmpregadoTenda(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case XERRAR_INFORME:
                            
                            //Generar informe
                            UtilesInforme.crearInforme(sc, session);
                            pararProgramaAtaEnter();
                            
                            break;
                            
                        case LER_TITULARES_PERIODICO:

                            LeerRSSElPais.leerPortadaElPais();
                            pararProgramaAtaEnter();

                            break;

                        case SAIR:
                            System.err.println("Saindo do programa.");
                            break;

                        default:
                            throw new NumberFormatException();

                    }

                } catch (NumberFormatException | NullPointerException e) {
                    System.err.println("O número introducido non é valido.");
                    posicionMenu = 0;
                }

            }

        } catch (ADException e) {
            System.err.println(e.getDescripcionError());
            e.printStackTrace();
        }
    }
    
    private static void pararProgramaAtaEnter () {
        
        //Parar Ejecucion ata enter
        System.out.println("Presione Enter para continuar.");
        
        sc.nextLine();
        
    }
    
}