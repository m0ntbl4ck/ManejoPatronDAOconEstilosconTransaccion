
package com.DAO;

import com.model.Producto;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Model
public class ProductoDAO implements Serializable{
    
    
  //  @Resource(lookup = "mysql/javasupermercadodb")
  //  private DataSource dataSource;
    
  /*  private final String idCol = "idProducto";
    private final String nomCol = "nombreProducto";
    private final String preCol = "precio";
    private final String descCol = "descripcion";*/
    
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Producto> obtenerTodosLosProductos() throws SQLException{
     /*   List<Producto> listaProductos = new ArrayList<>();
        
        try(Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
            while(rs.next()){
                String id = rs.getString(idCol);
                String nombre = rs.getString(nomCol);
                double precio = rs.getDouble(preCol);
                String descripcion = rs.getString(descCol);
                listaProductos.add(new Producto(id,nombre,precio,descripcion));
            }
    }*/
     TypedQuery<Producto> query 
             = em.createQuery("SELECT p FROM Producto p",Producto.class);
                List<Producto> listaProductos =query.getResultList();
        return listaProductos;
    }
    
    public Producto obtenerProductoPorId(String idProducto) throws SQLException{
       /* Producto producto = null;
        
        try(Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM producto WHERE  idProducto = '"+idProducto+"'");
            rs.next();
                String id = rs.getString(idCol);
                String nombre = rs.getString(nomCol);
                double precio = rs.getDouble(preCol);
                String descripcion = rs.getString(descCol);
                producto = new Producto(id,nombre,precio,descripcion);
            
    }*/
       
       Producto producto = em.find(Producto.class, idProducto);
        return producto;
    }
}
