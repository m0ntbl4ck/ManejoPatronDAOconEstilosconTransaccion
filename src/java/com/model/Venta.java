
package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Venta implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;
    
    
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    private double totalVenta;
    public Venta() {
    }
  
    
    
    

    public Venta(int idVenta, Date fechaVenta, double totalVenta) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
    
    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }


    public int getIdVenta() {
        return idVenta;
    }

}
