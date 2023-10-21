
package com.DAO;

import com.model.Inventario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Model
public class InventorioDAO implements Serializable {
   
    //@Resource(lookup = "mysql/javasupermercadodb")
    //private DataSource dataSource;
    
    @PersistenceContext
    private EntityManager em;
    
    public Inventario obtenerInventario(String idProducto) throws SQLException{
        
      //  Inventario inventario = null;
     /*   try(Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventario WHERE idProducto = '"+idProducto + "'");
            rs.next();
            inventario = new Inventario(idProducto,rs.getInt("disponible"));
            stmt.close();
            rs.close();
        }
        finally{
           
        }*/
     Inventario inventario = em.find(Inventario.class, idProducto);
        return inventario;
    }
    
    public void actualizarInventario(String idProducto,int cantidad) throws SQLException{
    /*   try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE inventario SET disponible = "+cantidad+" WHERE idProducto = '"+idProducto+"'");
            
            stmt.close();
        }*/
    Inventario inventario = new Inventario(idProducto,cantidad);
    em.merge(inventario);
    }
    
}
