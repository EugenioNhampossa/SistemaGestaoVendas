/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serial;
import java.io.Serializable;


/**
 * The type Item fornecido.
 *
 * @author eugenio
 */
public class ItemFornecido implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private int idProd;
    private double qtyForn;

    /**
     * Instantiates a new Item fornecido.
     *
     * @param idProd  the id prod
     * @param qtyForn the qty forn
     */
    public ItemFornecido(int idProd, double qtyForn) {
        this.idProd = idProd;
        this.qtyForn = qtyForn;
    }


    /**
     * Gets id prod.
     *
     * @return the id prod
     */
    public int getIdProd() {
        return idProd;
    }

    /**
     * Sets id prod.
     *
     * @param idProd the id prod
     */
    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    /**
     * Gets qty forn.
     *
     * @return the qty forn
     */
    public double getQtyForn() {
        return qtyForn;
    }

    /**
     * Sets qty forn.
     *
     * @param qtyForn the qty forn
     */
    public void setQtyForn(double qtyForn) {
        this.qtyForn = qtyForn;
    }

    @Override
    public String toString() {
        return "ItemFornecido{" +
                " idProd=" + idProd +
                ", qtyForn=" + qtyForn +
                '}';
    }
}
