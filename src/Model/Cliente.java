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
public class Cliente extends  Pessoa{

    @Serial
    private static final long serialVersionUID = 1L;
    private final  Dao dao;

    public Cliente(int id, String nome, String email, String cell) {
        super(id, nome, email, cell);
        dao = new Dao("Clientes");
    }
    public Cliente(){
        dao = new Dao("Clientes");
    }

    public Dao getDao() {
        return dao;
    }
}
