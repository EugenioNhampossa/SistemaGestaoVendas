package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import View.TelaArmazens;
import View.TelaClientes;
import View.TelaEstatistica;
import View.TelaFornecedor;
import View.TelaFornecimento;
import View.TelaPrincipal;
import View.TelaProdutos;
import View.TelaRelatorioVendas;
import View.TelaUsuario;
import View.TelaVenda;

public class MainController {
    private final TelaPrincipal view;
    private final Vector<Object> armazens;
    private final Vector<Object> produtos;
    private final Vector<Object> clientes;
    private final Vector<Object> fornecedores;
    private final Vector<Object> fornecimentos;
    private final Vector<Object> vendas;
    private final Vector<Object> users;

    public MainController(TelaPrincipal view, Vector<Object> armazens, Vector<Object> produtos, Vector<Object> clientes,
            Vector<Object> fornecedores, Vector<Object> fornecimentos, Vector<Object> vendas, Vector<Object> users) {
        this.view = view;
        this.armazens = armazens;
        this.produtos = produtos;
        this.clientes = clientes;
        this.fornecedores = fornecedores;
        this.fornecimentos = fornecimentos;
        this.vendas = vendas;
        this.users = users;

        view.addActionBtProdutos(new AccaoBtProdutos());
        view.addActionBtArmazens(new AccaoBtArmazens());
        view.addActionBtClientes(new AccaoBtClientes());
        view.addActionBtFornecedor(new AccaoBtFornecedor());
        view.addActionBtEstatisticas(new AccaoBtEstatistica());
        view.addActionBtVendas(new AccaoBtVendas());
        view.addActionBtNovaVenda(new AccaoBtNovaVenda());
        view.addActionBtFornecimento(new AccaoBtFornecimentos());
        view.addActionBtUsers(new AccaoBtUsers());
    }

    private class AccaoBtProdutos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaProdutos tp = new TelaProdutos();
                view.getPg().setValue(30);
                ControllerProduto cp = new ControllerProduto(tp, produtos, armazens, vendas, fornecimentos);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tp);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
                System.out.println("executado");
            }).start();
        }
    }

    private class AccaoBtArmazens implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaArmazens ta = new TelaArmazens();
                view.getPg().setValue(30);
                ControllerArmazem ca = new ControllerArmazem(ta, armazens, produtos);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(ta);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();
        }
    }

    private class AccaoBtClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaClientes tc = new TelaClientes();
                view.getPg().setValue(30);
                ControllerCliente cc = new ControllerCliente(tc, clientes, vendas);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tc);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();
        }
    }

    private class AccaoBtFornecimentos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaFornecimento tf = new TelaFornecimento();
                view.getPg().setValue(30);
                ControllerFornecimento cf = new ControllerFornecimento(tf, fornecimentos, produtos, fornecedores);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tf);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();

        }
    }

    private class AccaoBtFornecedor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaFornecedor tf = new TelaFornecedor();
                view.getPg().setValue(30);
                ControllerFornecedor cf = new ControllerFornecedor(tf, fornecedores, fornecimentos);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tf);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();

        }
    }

    private class AccaoBtEstatistica implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaEstatistica te = new TelaEstatistica();
                view.getPg().setValue(30);
                ControllerEstatistica ce = new ControllerEstatistica(te, produtos, armazens);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(te);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);

            }).start();

        }
    }

    private class AccaoBtVendas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaRelatorioVendas trv = new TelaRelatorioVendas();
                view.getPg().setValue(30);
                ControllerRelatorioVendas cv = new ControllerRelatorioVendas(trv, vendas, clientes, produtos);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(trv);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();

        }
    }

    private class AccaoBtNovaVenda implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaVenda tv = new TelaVenda();
                view.getPg().setValue(30);
                ControllerVenda cv = new ControllerVenda(tv, vendas, clientes, produtos);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tv);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();
        }
    }

    private class AccaoBtUsers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(() -> {
                view.getPg().setVisible(true);
                TelaUsuario tu = new TelaUsuario();
                view.getPg().setValue(30);
                ControllerUser uc = new ControllerUser(tu, users);
                view.getPg().setValue(60);
                view.getPg().setValue(80);
                view.addTela(tu);
                view.getPg().setValue(100);
                view.getPg().setVisible(false);
            }).start();
        }
    }
}
