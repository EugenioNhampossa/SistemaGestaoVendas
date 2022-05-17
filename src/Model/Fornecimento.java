/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.Dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
 * The type Fornecimento.
 *
 * @author eugenio
 */
public class Fornecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idForn;
    private Date data;
    private Vector<ItemFornecido> itensFornecidos;
    private final Dao dao;

    /**
     * Instantiates a new Fornecimento.
     *
     * @param id              the id
     * @param idForn          the id forn
     * @param data            the data
     * @param itensFornecidos the itens fornecidos
     */
    public Fornecimento(int id, int idForn, Date data, Vector<ItemFornecido> itensFornecidos) {
        this.id = id;
        this.idForn = idForn;
        this.data = data;
        this.itensFornecidos = itensFornecidos;
        dao = new Dao("Fornecimentos");
    }
    public Fornecimento(){
        dao = new Dao("Fornecimentos");
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
     * Gets id forn.
     *
     * @return the id forn
     */
    public int getIdForn() {
        return idForn;
    }

    /**
     * Sets id forn.
     *
     * @param idForn the id forn
     */
    public void setIdForn(int idForn) {
        this.idForn = idForn;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Gets itens fornecidos.
     *
     * @return the itens fornecidos
     */
    public Vector<ItemFornecido> getItensFornecidos() {
        return itensFornecidos;
    }

    /**
     * Sets itens fornecidos.
     *
     * @param itensFornecidos the itens fornecidos
     */
    public void setItensFornecidos(Vector<ItemFornecido> itensFornecidos) {
        this.itensFornecidos = itensFornecidos;
    }

    public Dao getDao() {
        return dao;
    }

    @Override
    public String toString() {
        return "Fornecimento{" +
                "id=" + id +
                ", idForn=" + idForn +
                ", data=" + data +
                ", itensFornecidos=" + itensFornecidos +
                '}';
    }
}
