package Controller;

import Model.Armazem;
import Model.Cliente;
import Model.Fornecedor;
import Model.Fornecimento;
import Model.Produto;
import Model.User;
import Model.Venda;
import View.TelaLogin;
import View.TelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ControllerLogin {
    private final TelaLogin view;
    private final Vector<Object> users;
    private User userAutenticado;

    public ControllerLogin(TelaLogin view, Vector<Object> users) {
        this.view = view;
        this.users = users;
        view.addAccaoBtEntrar(new AccaoBtEntrar());
        view.addAccaoBtCancelar(new AccaoBtCancelar());
    }


    private class AccaoBtEntrar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNome().equals("admin") && view.getSenha().equals("admin")) {
                userAutenticado = new User("", "Adiministrador", "Administrador", -1);
                acessar();
            } else {
                int control = 0;
                for (Object user : users) {
                    if (((User) user).autenticar(view.getNome(), view.getSenha())) {
                        userAutenticado = ((User) user);
                        acessar();
                        control = 1;
                        break;
                    }
                }
                if (control == 0) {
                    view.errorMessage("Senha e/ou nome de usáro incorrecto(s)\nTente Novamente!");
                }
            }
        }
    }

    private void acessar() {
        Armazem a = new Armazem();
        Vector<Object> armazens = a.getDao().ler();

        Produto p = new Produto();
        Vector<Object> produtos = p.getDao().ler();

        Cliente c = new Cliente();
        Vector<Object> clientes = c.getDao().ler();

        Fornecedor f = new Fornecedor();
        Vector<Object> fornecedores = f.getDao().ler();

        Fornecimento forn = new Fornecimento();
        Vector<Object> fornecimentos = forn.getDao().ler();

        Venda venda = new Venda();
        Vector<Object> vendas = venda.getDao().ler();

        TelaPrincipal tp = new TelaPrincipal(userAutenticado.getNomeUsuario(), userAutenticado.getAcesso());
        MainController mc = new MainController(tp, armazens, produtos, clientes, fornecedores, fornecimentos, vendas, users);
        tp.setVisible(true);

        view.dispose();
    }

    private class AccaoBtCancelar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
}
