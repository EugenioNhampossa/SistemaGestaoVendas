package Controller;

import Model.*;
import View.TelaVenda;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ControllerVenda {
    private final TelaVenda view;
    private final Vector<Object> vendas;
    private final Vector<Object> clientes;
    private final Vector<Object> produtos;
    private final Vector<String> prodPonto = new Vector<>();

    public ControllerVenda(TelaVenda view, Vector<Object> vendas, Vector<Object> clientes, Vector<Object> produtos) {
        this.view = view;
        this.vendas = vendas;
        this.clientes = clientes;
        this.produtos = produtos;

        view.addAccaoBtAdicionar(new AccaoBtAdicionar());
        view.addAccaoBtRemover(new AccaoBtRemover());
        view.addAccaoBtCancelar(new AccaoBtCancelar());
        view.addAccaoBtConfirmar(new AccaoBtConfirmar());
        preencherCb();
    }

    private class AccaoBtAdicionar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (pesquisarNome(String.valueOf(view.getProduto()), produtos) != 0) {
                    Produto produto = pesquisarProd(String.valueOf(view.getProduto()));
                    double preco = produto.getPrecoUnit() * view.getQty();
                    String[] linha = new String[]{
                            String.valueOf(view.getProduto()),
                            String.valueOf(view.getQty()),
                            String.format("%.2f MT", produto.getPrecoUnit()),
                            String.valueOf(preco)
                    };
                    if (produto.getQtyStock() >= view.getQty()) {
                        adicionarLinha(view.getTbProdVenda(), linha);
                        view.limparTxt();
                        calcularTotal();
                    } else {
                        view.errorMessage("Apenas " + produto.getQtyStock() + " unidades disponíveis para " + produto.getNomeProd());
                    }
                } else {
                    view.errorMessage("Seleccione o produto");
                }
            } catch (NumberFormatException ex) {
                view.errorMessage("A quantidade deve ser um número\n" + ex.getMessage());
            }
        }
    }

    public void calcularTotal() {
        double total = 0;
        for (int i = 0; i < view.getTbProdVenda().getRowCount(); i++) {
            total += Double.parseDouble((String) view.getTbProdVenda().getValueAt(i, 3));
        }
        double totalIva = total * 0.17;
        double totalLiquido = totalIva + total;
        view.setLbTotalNr(total);
        view.setLbIvaNr(totalIva);
        view.setLbTotalLiq(totalLiquido);
    }


    private class AccaoBtRemover implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTbProdVenda().getRowCount() > 0) {
                remLinha(view.getTbProdVenda(), view.getTbProdVenda().getSelectedRow());
                calcularTotal();
            } else {
                view.errorMessage("Sem linhas por remover");
            }
        }
    }

    private class AccaoBtCancelar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.limparTb();
            view.limparCalc();
        }
    }

    private class AccaoBtConfirmar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel dtm = (DefaultTableModel) view.getTbProdVenda().getModel();
            int idProd = 0;
            double qty = 0;
            int idCli = pesquisarNome(String.valueOf(view.getCliente()), clientes);
            if (view.getTbProdVenda().getRowCount() != 0) {
                if (idCli != 0) {
                    Vector<ItemVenda> itens = new Vector<>();
                    for (int i = 0; i < view.getTbProdVenda().getRowCount(); i++) {
                        idProd = pesquisarNome((String) dtm.getValueAt(i, 0), produtos);
                        qty = Double.parseDouble((String) dtm.getValueAt(i, 1));
                        actualizarQty(qty, idProd, (String) dtm.getValueAt(i, 0));
                        itens.add(new ItemVenda(qty, idProd));
                    }
                    Venda venda = new Venda(geradorId(), idCli, view.getData(), itens);
                    if (venda.getDao().escrever(venda) && vendas.add(venda)) {
                        view.message("Venda Registrada");
                        view.message("Inventário actualizado com sucesoo");
                        view.limparTb();
                        view.limparTxt();
                        view.limparCalc();
                        verificarPonto();
                    }
                } else {
                    view.errorMessage("Seleccione o Cliente");
                }
            } else {
                view.errorMessage("Sem dados por registrar");
            }
        }
    }

    public void verificarPonto() {
        StringBuilder produto = new StringBuilder();
        for (String prod : prodPonto) {
            produto.append("* ").append(prod).append("\n");
        }
        if(!produto.isEmpty()) {
            view.warningMassage("O(s) produto(s): \n" + produto + "Esta(ão) no ponto de encomenda");
        }
    }

    public void actualizarQty(double qty, int idProd, String produto) {
        for (Object obj : produtos) {
            if (((Produto) obj).getId() == idProd) {
                if (!((Produto) obj).subtrairQtyStock(qty)) {
                    view.errorMessage("Sem unidades suficientes do produto: " + produto);
                }
                ((Produto) obj).getDao().actualizar(produtos);
                if (((Produto) obj).verificarPontoEncomenda()) {
                    prodPonto.add(((Produto) obj).getNomeProd());
                }
            }
        }

    }

    public void remLinha(JTable tabela, int linha) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        if (linha != -1) {
            model.removeRow(linha);
            tabela = new JTable(model);
        } else {
            view.errorMessage("Seleccione uma linha");
        }
    }

    public void adicionarLinha(JTable tabela, String[] linha) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.addRow(linha);
        tabela = new JTable(model);
    }

    public int pesquisarNome(String nome, Vector<Object> vector) {
        for (Object obj : vector) {
            if (obj instanceof Produto) {
                if (((Produto) obj).getNomeProd().equals(nome)) {
                    return ((Produto) obj).getId();
                }
            }
            if (obj instanceof Cliente) {
                if (((Cliente) obj).getNome().equals(nome)) {
                    return ((Cliente) obj).getId();
                }
            }
        }
        return 0;
    }

    public Produto pesquisarProd(String nome) {
        for (Object obj : produtos) {
            if (((Produto) obj).getNomeProd().equals(nome)) {
                return (Produto) obj;
            }
        }
        return null;
    }

    public void preencherCb() {
        for (Object obj : produtos) {
            view.addProduto(((Produto) obj).getNomeProd());
        }
        for (Object obj : clientes) {
            view.addCliente(((Cliente) obj).getNome());
        }
    }


    private int geradorId() {
        if (vendas.isEmpty()) {
            return 1;
        } else {
            return ((Venda) vendas.lastElement()).getId() + 1;
        }
    }
}
