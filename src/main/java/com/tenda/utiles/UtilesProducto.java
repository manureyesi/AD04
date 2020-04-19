package com.tenda.utiles;

import com.tenda.hibernate.entity.ProductoEntity;
import com.tenda.hibernate.entity.ProductoStockEntity;
import com.tenda.hibernate.entity.ProductoStockPK;
import com.tenda.hibernate.entity.TendaEntity;
import com.tenda.hibernate.repository.ProductoStockRepositorio;
import com.tenda.hibernate.repository.ProductosRepositorio;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

/**
 *
 * UtilesProducto
 */
public class UtilesProducto {
    
    /**
     * Añadir producto
     * @param sc
     * @param session 
     */
    public static void anadirProducto (final Scanner sc, final Session session) {
    
        System.out.print("Nome Producto: ");
        String nomeProducto = sc.nextLine();

        System.out.print("Descripcion Producto: ");
        String decripcionProducto = sc.nextLine();

        System.out.print("Prezo Producto: ");
        String prezoProducto = sc.nextLine();

        try{

            ProductoEntity productoEngadir = 
                    new ProductoEntity(nomeProducto);
            productoEngadir.setPrezo(Double.valueOf(prezoProducto.replace(',', '.')));
            productoEngadir.setDescripcion(StringUtils.isNotBlank(decripcionProducto) ? decripcionProducto : null);

            if (ProductosRepositorio.buscarProductoPorNome(session, nomeProducto)==null) {
                ProductosRepositorio.insertarDatos(session, productoEngadir);
                System.out.println("Producto engadido correctamente.");
            } else{
                System.out.println("El producto ya existe.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Error ao engadir producto.");
        }
        
    }
    
    /**
     * Añadir producto a tenda
     * @param sc
     * @param session 
     */
    public static void anadirProductoTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para engadir Productos:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {

            System.out.print("Buscar producto para engadir stock:");
            String nomeProducto = sc.nextLine();

            ProductoEntity producto = ProductosRepositorio.buscarProductoPorNome(session, nomeProducto);

            if (producto != null) {

                System.out.print("Stock Producto: ");
                String stockProducto = sc.nextLine();

                try {

                    Integer stock = Integer.valueOf(stockProducto);
                    
                    ProductoStockPK productoStockPK =
                            new ProductoStockPK(tenda, producto);
                    
                    ProductoStockEntity productoStock = new ProductoStockEntity(
                            productoStockPK,
                            Integer.valueOf(stockProducto));

                    //Comprobar se non se ten datos de Stock para esa tenda
                    if (ProductoStockRepositorio.buscarProductoPorTenda(session, tenda, producto)==null) {
                        //Insertar datos
                        ProductoStockRepositorio.insertarDatos(session, productoStock);
                    } else {
                        System.err.println("Error, o producto xa tiña stock");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error ao engadir producto.");
                }

            } else {
                System.out.println("Producto non encontrado.");
            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
    
    }
    
    /**
     * Ver productos
     * @param sc
     * @param session 
     */
    public static void verProductos (final Scanner sc, final Session session) {
    
        List<ProductoEntity> listaProductos = 
                ProductosRepositorio.listarProductos(session);

        //Ver Tendas
        System.out.println("Lista de Productos:");

        for (ProductoEntity productoAux : listaProductos) {
            //Datos Productos
            System.out.print("Nombre: ");
            System.out.println(productoAux.getNome());

            System.out.print("Descripcion: ");
            System.out.println(productoAux.getDescripcion());

            System.out.print("Precio: ");
            System.out.println(productoAux.getPrezo());
            System.out.println("");

        }
        
    }
    
    /**
     * Ver productos por tenda
     * @param sc
     * @param session 
     */
    public static void verProductosPorTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para ver Productos:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {

            List<ProductoStockEntity> listaProductosStock = 
                    ProductoStockRepositorio.buscarProductosPorTenda(session, tenda);

            //Ver Tendas
            System.out.println("Lista de Productos:");

            for (ProductoStockEntity productoStockAux : listaProductosStock) {
                //Datos Productos
                System.out.print("Nombre: ");
                System.out.println(productoStockAux.getProductoStockPK().getProductoEntity().getNome());

                System.out.print("Descripcion: ");
                System.out.println(productoStockAux.getProductoStockPK().getProductoEntity().getDescripcion());

                System.out.print("Precio: ");
                System.out.println(productoStockAux.getProductoStockPK().getProductoEntity().getPrezo());

                System.out.print("  Stock: ");
                System.out.println(productoStockAux.getStock());
                System.out.println("");

            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
        
    }
    
    /**
     * Actualizar stock por tenda
     * @param sc
     * @param session 
     */
    public static void actualizarStockProductoTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para modificar Productos:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {

            System.out.println("Buscar producto para modificar stock:");
            String nomeProducto = sc.nextLine();

            ProductoEntity producto = ProductosRepositorio.buscarProductoPorNome(session, nomeProducto);

            if (producto != null) {

                ProductoStockEntity productoStock =
                        ProductoStockRepositorio.buscarProductoPorTenda(session, tenda, producto);

                if (productoStock != null) {

                    System.out.print("Stock Producto ("+productoStock.getStock()+"): ");
                    String stockProducto = sc.nextLine();

                    if (StringUtils.isNotBlank(stockProducto)) {
                        try {

                            productoStock.setStock(Integer.valueOf(stockProducto));

                            //Modificar datos
                            ProductoStockRepositorio.update(
                                    session,
                                    productoStock);

                        } catch (NumberFormatException e) {
                            System.err.println("Error ao engadir producto.");
                        }
                    }

                } else {
                    System.err.println("Error, o producto non ten stock nesta tenda.");
                }

            } else {
                System.out.println("Producto non encontrado.");
            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
    
    }
    
    /**
     * Ver Stock por producto e tenda
     * @param sc
     * @param session 
     */
    public static void verStockPorProdcutoTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para ver Producto:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {

            System.out.println("Buscar producto para ver stock:");
            String nomeProducto = sc.nextLine();

            ProductoEntity producto = ProductosRepositorio.buscarProductoPorNome(session, nomeProducto);

            if (producto != null) {

                //Buscar Stock Producto
                ProductoStockEntity productoStock =
                        ProductoStockRepositorio.buscarProductoPorTenda(session, tenda, producto);
                
                //Datos Productos
                System.out.print("Nombre: ");
                System.out.println(productoStock.getProductoStockPK().getProductoEntity().getNome());

                System.out.print("Descripcion: ");
                System.out.println(productoStock.getProductoStockPK().getProductoEntity().getDescripcion());

                System.out.print("Precio: ");
                System.out.println(productoStock.getProductoStockPK().getProductoEntity().getPrezo());

                System.out.print("  Stock: ");
                System.out.println(productoStock.getStock());
                System.out.println("");

            } else {
                System.out.println("Producto non encontrado.");
            } 

        } else {
            System.out.println("Tenda non encontrada.");
        }
    
    }
    
    /**
     * Eliminar prodcuto por tenda
     * @param sc
     * @param session 
     */
    public static void eliminarProductoPorTenda (final Scanner sc, final Session session) {
    
        System.out.println("Buscar tenda para eliminar Producto:");
        System.out.print("Nome Tenda: ");

        TendaEntity tenda = UtilesTenda.buscarTenda(sc, session);

        if (tenda != null) {

            System.out.print("Buscar producto para eliminar: ");
            String nomeProducto = sc.nextLine();

            ProductoEntity producto = ProductosRepositorio.buscarProductoPorNome(session, nomeProducto);

            if (producto != null) {

                ProductoStockEntity productoStock =
                        ProductoStockRepositorio.buscarProductoPorTenda(session, tenda, producto);

                if (productoStock != null) {
                    if (UtilesTenda.comprobarBorrado(sc)) {
                        ProductoStockRepositorio.eliminar(session, productoStock);
                    }

                } else {
                    System.err.println("Error, o producto non ten stock nesta tenda.");
                }

            } else {
                System.out.println("Producto non encontrado.");
            }

        } else {
            System.out.println("Tenda non encontrada.");
        }
        
    }
    
    /**
     * Eliminar producto
     * @param sc
     * @param session 
     */
    public static void eliminarProducto (final Scanner sc, final Session session) {
    
        System.out.print("Nombre Producto: ");
        String nombreProducto = sc.nextLine();

        try {

            ProductoEntity producto = ProductosRepositorio.buscarProductoPorNome(session, nombreProducto);

            if (producto != null) {
                if (UtilesTenda.comprobarBorrado(sc)) {
                    
                    //Borrar Stock por tendas
                    for (ProductoStockEntity productoStockEntity: ProductoStockRepositorio.buscarProductos(session, producto)) {
                        //Eliminar referencia
                        ProductoStockRepositorio.eliminar(session, productoStockEntity);
                    }
                    
                   ProductosRepositorio.eliminar(session, producto);
                }

            } else {
                System.out.println("Producto non encontrado.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Error ao buscar producto a borrar.");
        }
        
    }
    
}
