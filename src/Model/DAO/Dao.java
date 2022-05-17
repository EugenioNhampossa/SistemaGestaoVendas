/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Vector;

/**
 * @author eugenio
 */
public class Dao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String dir;
    private final String ficheiro;
    private File file;

    public Dao(String ficheiro) {
        this.ficheiro = ficheiro;
        dir = System.getProperty("user.home") + "/Venda Facil/Files/";
        File file = new File(dir);
        var mkdirs = file.mkdirs();
    }

    public String getCaminho() {
        return this.dir + this.ficheiro + ".dat";
    }

    public boolean escrever(Object obj) {
        try {
            FileOutputStream fOut = new FileOutputStream(getCaminho(), true);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(obj);
            oOut.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro na escrita dos dados\n");
            e.printStackTrace();
            return false;
        }
    }


    public Vector<Object> ler() {
        Vector<Object> v = new Vector<>();
        file = new File(getCaminho());
        if (file.exists()) {
            try {
                FileInputStream fIn = new FileInputStream(getCaminho());
                Object o;
                while (fIn.available() > 0) {
                    ObjectInputStream oIn = new ObjectInputStream(fIn);
                    o = oIn.readObject();
                    v.add(o);
                }
                return v;
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return v;
    }

    public boolean actualizar(Vector<Object> v) {
        try {
            file = new File(getCaminho());
            var delete = file.delete();
            
            for (Object object : v) {
                escrever(object);
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao Actualizar\n" + ex);
            ex.printStackTrace();
            return false;
        }

    }

}
