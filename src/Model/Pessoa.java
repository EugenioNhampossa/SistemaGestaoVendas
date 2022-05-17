/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.Serial;
import java.io.Serializable;

/**
 * The type Pessoa.
 *
 * @author eugenio
 */
public abstract class Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String nome;
    protected String email;
    protected String cell;

    /**
     * Instantiates a new Pessoa.
     *
     * @param id       the id
     * @param nome     the nome
     * @param email    the email
     * @param cell     the cell
     */
    public Pessoa(int id, String nome, String email, String cell) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cell = cell;
    }
    public Pessoa(){
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
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets cell.
     *
     * @return the cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * Sets cell.
     *
     * @param cell the cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cell='" + cell + '\'' +
                '}';
    }
}
