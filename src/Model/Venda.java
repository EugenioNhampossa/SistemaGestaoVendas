/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.Dao;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
 * @author eugenio
 */
public class Venda implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private int idCli;
    private Date dataVenda;
    private Vector<ItemVenda> itensVenda;
    private final Dao dao;

    public Venda(){
        dao = new Dao("Vendas");
    }

    public Venda(int id,int idCli, Date dataVenda, Vector<ItemVenda> itensVenda) {
        this.id = id;
        this.idCli = idCli;
        this.dataVenda = dataVenda;
        this.itensVenda = itensVenda;
        dao = new Dao("Vendas");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idCli
     */
    public int getIdCli() {
        return idCli;
    }

    /**
     * @param idCli the idCli to set
     */
    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    /**
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda the dataVenda to set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * @return the itensVenda
     */
    public Vector<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    /**
     * @param itensVenda the itensVenda to set
     */
    public void setItensVenda(Vector<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Dao getDao() {
        return dao;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", idCli=" + idCli +
                ", dataVenda=" + dataVenda +
                ", itensVenda=" + itensVenda +
                '}';
    }
}
