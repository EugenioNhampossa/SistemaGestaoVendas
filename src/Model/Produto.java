/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.Dao;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Produto.
 *
 * @author eugenio
 */
public class Produto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String nomeProd;
    private double qtyStock;
    private double precoUnit;
    private int qtyVendida;
    private int codArmazem;
    private int stockMinimo;
    private Date dataValidade;
    private final Dao dao;

    public Produto() {
        dao = new Dao("Produtos");
    }

    public Produto(int id, String nomeProd, double precoUnit, int codArmazem, int stockMinimo, Date dataValidade) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.qtyStock = 0;
        this.qtyVendida = 0;
        this.precoUnit = precoUnit;
        this.codArmazem = codArmazem;
        this.stockMinimo = stockMinimo;
        this.dataValidade = dataValidade;
        dao = new Dao("Produtos");
    }

    public int getQtyVendida() {
        return qtyVendida;
    }

    public void setQtyVendida(int qtyVendida) {
        this.qtyVendida = qtyVendida;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nomeProd
     */
    public String getNomeProd() {
        return nomeProd;
    }

    /**
     * @param nomeProd the nomeProd to set
     */
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    /**
     * @return the qtyStock
     */
    public double getQtyStock() {
        return qtyStock;
    }

    public boolean verificarPontoEncomenda() {
        return qtyStock <= stockMinimo;
    }

    public boolean verificarValidade() {
        Calendar dataValidade = Calendar.getInstance();
        Calendar dataActual = Calendar.getInstance();
        dataValidade.setTime(this.getDataValidade());
        if (dataActual.get(Calendar.YEAR) >= dataValidade.get(Calendar.YEAR)) {
            return dataActual.get(Calendar.MONTH) >= dataValidade.get(Calendar.MONTH) || dataActual.get(Calendar.MONTH) + 1 >= dataValidade.get(Calendar.MONTH);
        }
        return false;
    }

    public String verificarCondicao() {
        Calendar dataActual = Calendar.getInstance();
        Calendar dataValidade = Calendar.getInstance();
        dataValidade.setTime(this.getDataValidade());
        if (dataActual.get(Calendar.YEAR) >= dataValidade.get(Calendar.YEAR)) {
            if (dataActual.get(Calendar.MONTH) >= dataValidade.get(Calendar.MONTH)) {
                if (dataActual.get(Calendar.DAY_OF_MONTH) >= dataValidade.get(Calendar.DAY_OF_MONTH)) {
                    return "Expirou";
                }
            }
        }
        return "Próximo da Data";
    }

    /**
     * @return the precoUnit
     */
    public double getPrecoUnit() {
        return precoUnit;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public Dao getDao() {
        return dao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getCodArmazem() {
        return codArmazem;
    }

    public void setCodArmazem(int codArmazem) {
        this.codArmazem = codArmazem;
    }

    /**
     * @param precoUnit the precoUnit to set
     */
    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public void somarQtyStock(double qty) {
        this.qtyStock = this.qtyStock + qty;
    }

    public boolean subtrairQtyStock(double qty) {
        if (qty <= qtyStock) {
            this.qtyStock = this.qtyStock - qty;
            this.qtyVendida += qty;
            return true;
        } else {
            return false;
        }
    }


    public boolean verificarStock() {
        return this.qtyStock <= stockMinimo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nomeProd='" + nomeProd + '\'' +
                ", qtyStock=" + qtyStock +
                ", precoUnit=" + precoUnit +
                ", stockMinimo=" + stockMinimo +
                '}';
    }
}
