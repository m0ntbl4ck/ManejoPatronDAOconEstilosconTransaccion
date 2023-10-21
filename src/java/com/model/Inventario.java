
package com.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventario implements Serializable {
    
    @Id
   private String idProducto;
    @Column(name= "disponible")
   private int cantidadDisponible;

    
    public Inventario() {
    }
    
    public Inventario(String idProducto, int cantidadDisponible) {
        this.idProducto = idProducto;
        this.cantidadDisponible = cantidadDisponible;
    }

    
   
   public void agregar(int cantidad){
       cantidadDisponible +=cantidad;
   }
   
   public boolean restar(int cantidad){
       if(cantidadDisponible - cantidad >= 0){
           cantidadDisponible -= cantidad;
           return true;
       }else{
           return false;
       }
   }
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public String getIdProducto() {
        return idProducto;
    }
   
   
}
