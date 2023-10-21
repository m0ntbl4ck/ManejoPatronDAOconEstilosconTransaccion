
package com.servlet;

import com.DAO.InventorioDAO;
import com.DAO.ProductoDAO;
import com.beans.CarritoDeCompras;
import com.model.Inventario;
import com.model.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mont_
 */
@WebServlet(name = "SuperMartServlet", urlPatterns = {"/SuperMartServlet"})
public class SuperMartServlet extends HttpServlet {

    
    @Inject
   private ProductoDAO productoDAO;
    @Inject
   private InventorioDAO inventarioDAO;
   @Inject
    private CarritoDeCompras carrito;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String context = request.getContextPath();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
           out.println("<meta charset=\"utf-8\">");
            out.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<!-- Bootstrap CSS -->");   
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
            out.println("<!-- Bootstrap CSS -->");
         
            out.println("<title>Java Super</title>");            
            out.println("</head>");
            out.println("<body>");
          
             out.println("<header class=\"container mt-5\">");
             out.println(" <h1 class=\"text-center\">Bienvenido al Supermercado Java</h1>");
             out.println("</header>");
 
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>");
            try {
                List<Producto> productos = productoDAO.obtenerTodosLosProductos();
                
                mostrarProductos(context,out,productos);
                out.println("<div class=\"container\" >");
                out.println("<div class=\"d-flex justify-content-center\" >");
                out.println("<h1  class=\"h4 text-center\">Articulos en el carrito: "+ carrito.obtenerConteodeItems()+"</h1>");
                 out.println("</div>");
            } catch (SQLException e) {
                out.println("SQLException: "+e);
            }
            
            out.println("<div class=\"d-flex justify-content-center my-5\" >");
            out.println("<form action= "+context+"/PurchaseCarrito><button class=\"btn btn-primary\" type=\"submit\">Ver Carrito</button></form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null){
            carrito.agregarItem(id, 1);
        }
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
//| id | nombre   | precio|disponible |accion |
    
    private void mostrarProductos(String context, PrintWriter out,List<Producto> productos)throws SQLException{
        out.println("<div class=\"container mt-5\">");
        out.println("<table class=\"table table-striped table-hover\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th class=\"text-center\" scope=\"col\">ID</th>");
        out.println("<th class=\"text-center\" scope=\"col\">Nombre</th>");
        out.println("<th class=\"text-center\" scope=\"col\">Descripcion</th>");
        out.println("<th class=\"text-center\" scope=\"col\">Precio</th>");
        out.println("<th class=\"text-center\" scope=\"col\">Unidades Disponibles</th>");
        out.println("<th class=\"text-center\" scope=\"col\">Acción</th>");
        out.println("</tr>");
        out.println("</thead>");
        
        for(Producto p : productos ){
            Inventario i = inventarioDAO.obtenerInventario(p.getIdProducto());
            
             out.println("<tr>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getIdProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getNombreProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getDescripcionProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getPrecio()+"$</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + i.getCantidadDisponible()+"</td>");
             if(i.getCantidadDisponible() >0){
                out.println("<td scope=\"col\"><button class=\"btn btn-success\" onclick=\"window.location.href='"+ context + "/SuperMartServlet?id=" + p.getIdProducto() + "'\">Comprar</button></td>");

             }else{
                 out.println("<td class=\"text-center text-danger\" scope=\"col\" >Agotado</td>");
             }
             out.println("</tr>");
             
        }
        out.println("</table>"); 
        out.println("</div>"); 
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
