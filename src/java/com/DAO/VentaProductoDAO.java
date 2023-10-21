
package com.DAO;


import com.model.VentaProducto;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Model
public class VentaProductoDAO implements Serializable{
  //  @Resource(lookup = "mysql/javasupermercadodb")
  //  private DataSource dataSource;
    
    @PersistenceContext
    private EntityManager em;
    public void agregarRegistroVentaProducto(VentaProducto registroVentas)throws SQLException{
        
      /*  try(Connection conn = dataSource.getConnection()) {
            PreparedStatement pStmt = conn.prepareCall("INSERT INTO ventaproducto VALUES (?,?,?)");
            pStmt.setInt(1, registroVentas.getIdVenta());
             pStmt.setString(2, registroVentas.getIdProducto());
              pStmt.setInt(3, registroVentas.getCantidadVendida());
              pStmt.executeUpdate();
              pStmt.close();        
    }*/
        em.persist(registroVentas);
    }  
    
    public List<VentaProducto> obtenerVentasProductoPorId(int idVenta) throws SQLException{
   /*     List<VentaProducto> ventasProductos = new ArrayList<>();
        
        try(Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ventaproducto WHERE idVenta = "+idVenta);
            while(rs.next()){
                int id = rs.getInt("idVenta");
                String idProducto = rs.getString("idProducto");
                int cantidadVendida = rs.getInt("cantidad");
               
                ventasProductos.add(new VentaProducto(id,idProducto,cantidadVendida));
            }
    }*/
   String queryConsulta = "SELECT p FROM ventaproducto WHERE p.idVenta ="+idVenta;
   TypedQuery<VentaProducto> query = em.createQuery(queryConsulta,VentaProducto.class);
       List<VentaProducto> ventasProductos =  query.getResultList();
   
   return ventasProductos;
    }
}
