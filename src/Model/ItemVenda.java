/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author eugenio
 */
public class ItemVenda implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private double qtyVendida;
    private int idProd;

    public ItemVenda(double qtyVendida, int idProd) {
        this.qtyVendida = qtyVendida;
        this.idProd = idProd;
    }

    public double getQtyVendida() {
        return qtyVendida;
    }

    public void setQtyVendida(double qtyVendida) {
        this.qtyVendida = qtyVendida;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                ", qtyVendida=" + qtyVendida +
                ", idProd=" + idProd +
                '}';
    }
}