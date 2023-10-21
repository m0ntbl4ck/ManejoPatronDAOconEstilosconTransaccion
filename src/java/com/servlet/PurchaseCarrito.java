
package com.servlet;

import com.beans.CarritoDeCompras;
import com.model.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilidades.PurchaseException;

/**
 *
 * @author mont_
 */
@WebServlet(name = "PurchaseCarrito", urlPatterns = {"/PurchaseCarrito"})
public class PurchaseCarrito extends HttpServlet {

  private static final Logger logger = Logger.getLogger("com.servlet.PruchaseCarrito");
  
  @Inject
  private CarritoDeCompras carritoDeCompras;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
         
            out.println("<title>Java carrito</title>");            
            out.println("</head>");
            out.println("<body>");
          
             
            try {
                mostrarCarritoVentas(out);
                
            } catch (SQLException e) {
                out.println("<p class=\"text-danger\">"+e+"<p>");
            }
            out.println("<div class=\"d-flex container my-5 justify-content-center\">");
             out.println("<form method=\"post\">");
             out.println("<button class=\"btn btn-success\" type=\"submit\" name=\"action\" value=Comprar>Comprar</button>");
             out.println("<button class=\"btn btn-danger\" type=\"submit\" name=\"action\" value=Cancelar>Cancelar</button>");
             out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
public void mostrarCarritoVentas(PrintWriter out) throws SQLException{
    out.println("<header class=\"container mt-5\">");
             out.println(" <h1 class=\" h3 text-center\">Su carrito Contiene: </h1>");
             out.println("</header>");
              
             out.println("<div class=\"container mt-5\">");
    out.println("<table class=\"table table-striped table-hover\">");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th class=\"text-center\" scope=\"col\">ID</th>");
    out.println("<th class=\"text-center\" scope=\"col\">Nombre</th>");
    out.println("<th class=\"text-center\" scope=\"col\">Descripcion</th>");
    out.println("<th class=\"text-center\" scope=\"col\">Precio</th>");
    out.println("<th class=\"text-center\" scope=\"col\">Cantidad</th>");
    out.println("<th class=\"text-center\" scope=\"col\">SubTotal</th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
    Map<Producto,Integer> carrito =carritoDeCompras.obtenercarrito();
    Set<Producto> productos = carrito.keySet();
    double ventaTotal = 0, subTotal = 0;
    for(Producto p : productos){
        
        out.println("<tr>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getIdProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getNombreProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getDescripcionProducto()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">" + p.getPrecio()+"</td>");
             out.println("<td class=\"text-center\" scope=\"col\">"+carrito.get(p)+"</td>");
             subTotal = carrito.get(p) *p.getPrecio();
             
             out.println("<td class=\"text-center\" scope=\"col\">"+ subTotal + "</td>");
            out.println("<tr>");
            ventaTotal += subTotal;
    }
    out.println("</tbody>");
     out.println("</table>");
      out.println("</div>");
      out.println("<div class=\"d-flex container mt-5 justify-content-end\">");
     out.println("<h1 class=h4 >Venta Total : "+String.format("$%.2f",ventaTotal)+"</h1>");
     out.println("</div>");
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try(PrintWriter out = response.getWriter()){
              out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
           out.println("<meta charset=\"utf-8\">");
            out.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<!-- Bootstrap CSS -->");   
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
            out.println("<!-- Bootstrap CSS -->");
         
            out.println("<title>Java carrito</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
            switch(action){
                case "Comprar":
                    carritoDeCompras.purchaseCarrito();
                        out.println("<div class=\" col container mt-5 \">");
                        out.println("<div class=\"row d-flex justify-content-center\">");
                    out.println("<h1 class=\"h3 text-center\">Exito! Su pedido sera enviado pronto</h1>");
                     out.println("</div>");
                    break;
                    case "Cancelar":
                    carritoDeCompras.reiniciarCarrito();
                    out.println("<div class=\"col container mt-5 \"\">");
                    out.println("<div class=\" row d-flex justify-content-center\">");
                    out.println("<h1 class=\"h3 text-center\">Su pedido fue cancelado</h1>");
                    out.println("</div>");
                    break;
            }
        }catch(PurchaseException ex){
            logger.log(Level.INFO, ex.getMessage());
            out.println("<h2 class=text-danger>"+ex.getMessage()+"</h2>");
        }       catch (Exception ex) {
    out.println("<h2 class=text-danger>Excepción al completar su transacción</h2>");
    logger.log(Level.INFO, ex.getMessage());
}          
            
            out.println("<div class=\" row d-flex justify-content-center \">");
                        out.println("<form action=\""+request.getContextPath()+"/SuperMartServlet\"><button class=\"btn btn-primary\" type=\"submit\" value=\"Volver a Comprar\">Volver a Comprar</button></form>");
                        out.println("</div>");
                        out.println("</div>");
                       out.println("</body>");
            out.println("</html>"); 
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
