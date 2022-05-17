/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.Dao;

import java.io.Serial;

/**
 *
 * @author eugenio
 */
public class Fornecedor extends Pessoa {

    @Serial
    private static final long serialVersionUID = 1L;
    private long nuit;
    private final Dao dao;

    public Fornecedor(){
        dao = new Dao("Fornecedores");
    }

    public Fornecedor(int id, String nome, String email, String cell,long nuit) {
        super(id, nome, email, cell);
        this.nuit = nuit;
        dao = new Dao("Fornecedores");
    }
    public Dao getDao() {
        return dao;
    }

    public long getNuit() {
        return nuit;
    }

    public void setNuit(long nuit) {
        this.nuit = nuit;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nuit=" + nuit +
                '}';
    }
}
