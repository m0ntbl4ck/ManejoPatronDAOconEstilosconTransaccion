
package com.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VentaProducto implements Serializable{
    
    @Id
    private int idVenta;
    private String idProducto;
    @Column(name="cantidad")
    private int cantidadVendida;

    public VentaProducto() {
    }

    public VentaProducto(int idVenta, String idProducto, int cantidadVendida) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadVendida = cantidadVendida;
    }

    
    public int getIdVenta() {
        return idVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    
}
