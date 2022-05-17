/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.Dao;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author eugenio
 */
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String senha;
    private String nomeUsuario;
    private String acesso;
    private final Dao dao;

    public User(){
        dao = new Dao("Users");
    }

    public User(String senha, String nomeUsuario, String acesso, int id) {
        this.id = id;
        this.senha = senha;
        this.nomeUsuario = nomeUsuario;
        this.acesso = acesso;
        dao = new Dao("Users");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public boolean autenticar(String usuario, String senha) {
        return this.nomeUsuario.equals(usuario) && this.senha.equals(senha);
    }

    public Dao getDao() {
        return dao;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", acesso='" + acesso + '\'' +
                '}';
    }
}
