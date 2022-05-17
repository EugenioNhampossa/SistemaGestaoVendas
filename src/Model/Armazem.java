package Model;

import Model.DAO.Dao;

import java.io.Serial;
import java.io.Serializable;

public class Armazem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private final Dao dao;
    private int nrProdutos;

    public Armazem() {
        dao = new Dao("Armazens");
    }

    public Armazem(int id, String nome) {
        this.id = id;
        this.nome = nome;
        nrProdutos = 0;
        dao = new Dao("Armazens");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getNrProdutos() {
        return nrProdutos;
    }

    public void adicionarProduto() {
        this.nrProdutos = this.nrProdutos + 1;
    }

    public void removerProduto() {
        this.nrProdutos = this.nrProdutos - 1;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dao getDao() {
        return dao;
    }

    @Override
    public String toString() {
        return "Armazem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dao=" + dao +
                ", nrProdutos=" + nrProdutos +
                '}';
    }
}
