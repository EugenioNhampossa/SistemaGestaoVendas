package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Model.Armazem;
import Model.Fornecimento;
import Model.ItemFornecido;
import Model.ItemVenda;
import Model.Produto;
import Model.Venda;
import View.TelaProdutos;

public class ControllerProduto {
    private final TelaProdutos view;
    private final Vector<Object> produtos;
    private final Vector<Object> armazens;
    private final Vector<Object> vendas;
    private final Vector<Object> fornecimentos;

    public ControllerProduto(TelaProdutos view, Vector<Object> produtos, Vector<Object> armazens, Vector<Object> vendas,
            Vector<Object> fornecimentos) {
        this.view = view;
        this.produtos = produtos;
        this.armazens = armazens;
        this.vendas = vendas;
        this.fornecimentos = fornecimentos;

        view.addAccaoBtGravar(new AccaoBtGravar());
        view.addAccaoTxtPesquisar(new AccaoTxtPesquisar());
        view.addAccaoBtApagar(new AccaoBtApagar());
        view.addAccaoBtEditar(new AccaoBtEditar());
        preencherTabela();
        preencherArmazens();
    }

    private class AccaoBtGravar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int codArm = codigoArmazem();
            if (codArm != 0) {
                if (!view.getNome().equals("")) {
                    if (verificarNome(view.getNome())) {
                        try {
                            Produto produto = new Produto(geradorId(), view.getNome(), view.getPreco(), codArm,
                                    view.getStockMinimo(), view.getData());
                            if (view.getStockMinimo() >= 5) {
                                if (produto.getDao().escrever(produto) && produtos.add(produto)) {
                                    view.message("Produto Registrado com sucesso");
                                    actualizarArm(codArm, 1);
                                    view.limparTxt();
                                    preencherTabela();
                                } else {
                                    view.errorMessage("Falha no regitro do produto");
                                }
                            } else {
                                view.errorMessage("O stock mínimo não pode ser menor que 5");
                            }
                        } catch (NumberFormatException ex) {
                            view.errorMessage(
                                    "Formato Incorrecto\nIntroduza números nos campos de Preço e/ou Stock mínimo ");
                        }
                    } else {
                        view.errorMessage(
                                "O produto [" + view.getNome() + "] já está registrado\nInsira um nome diferente");
                    }
                } else {
                    view.errorMessage("Insira o nome do produto");
                }
            } else {
                view.errorMessage("Seleccione o Armazém");
            }
        }
    }

    public void actualizarArm(int codArm, int accao) {
        for (Object a : armazens) {

            if (((Armazem) a).getId() == codArm) {
                if (accao == 1) {
                    ((Armazem) a).adicionarProduto();
                } else if (accao == 0) {
                    ((Armazem) a).removerProduto();
                }
                ((Armazem) a).getDao().actualizar(armazens);
            }

        }

    }

    public boolean verificarNome(String nome) {
        for (Object obj : produtos) {
            if (((Produto) obj).getNomeProd().equals(nome)) {
                return false;
            }
        }
        return true;
    }

    private class AccaoTxtPesquisar implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbProdutos().getModel());
            view.getTbProdutos().setRowSorter(rowSorter);
            rowSorter.setRowFilter(RowFilter.regexFilter(view.getPesquisa()));

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

    }

    private int codigoArmazem() {
        for (Object a : armazens) {
            if (((Armazem) a).getNome().equals(view.getArmazem())) {
                return ((Armazem) a).getId();
            }
        }
        return 0;
    }

    private Object buscarArmazem(int codigo) {
        for (Object a : armazens) {
            if (((Armazem) a).getId() == codigo) {
                return a;
            }
        }
        return "";
    }

    public void preencherArmazens() {
        for (Object a : armazens) {
            view.addArmazem(((Armazem) a).getNome());
        }
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Preço Unitário", "Armazém",
                "Qty em Stock", "Stock mínimo", "Prazo de Validade"));

        for (Object produto : produtos) {
            Vector<String> v = new Vector<>();

            v.add(new DecimalFormat("0000").format(((Produto) produto).getId()));
            v.add(((Produto) produto).getNomeProd());
            v.add(String.valueOf(((Produto) produto).getPrecoUnit()));
            v.add(((Armazem) buscarArmazem(((Produto) produto).getCodArmazem())).getNome());
            v.add(String.valueOf(((Produto) produto).getQtyStock()));
            v.add(String.valueOf(((Produto) produto).getStockMinimo()));
            v.add(new SimpleDateFormat("MMMM, dd, yyyy").format(((Produto) produto).getDataValidade()));
            linhas.add(v);

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbProdutos().setModel(model);
    }

    private int geradorId() {
        if (produtos.isEmpty()) {
            return 1;
        } else {
            return ((Produto) produtos.lastElement()).getId() + 1;
        }
    }

    private class AccaoBtApagar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTbProdutos().getSelectedRow() != -1) {
                int codigo = Integer
                        .parseInt((String) view.getTbProdutos().getValueAt(view.getTbProdutos().getSelectedRow(), 0));
                if (!verificar(codigo)) {
                    if (view.confirmationMessage("Deseja apagar??") == JOptionPane.YES_OPTION) {
                        Produto p = pesquisar(codigo);
                        produtos.remove(p);
                        p.getDao().actualizar(produtos);
                        view.message("Apagado com sucesso");
                        actualizarArm(p.getCodArmazem(), 0);
                    }
                    preencherTabela();
                } else {
                    view.errorMessage("Não pode apagar os dados do Produto\nHá registros relacionados ao produto");
                }
            } else {
                view.errorMessage("Nenhuma Linha Seleccionada!");
            }
        }
    }

    public void actualizarArmazem(Armazem armazem) {

    }

    private boolean verificar(int codigo) {
        for (Object obj : vendas) {
            for (ItemVenda item : ((Venda) obj).getItensVenda()) {
                if (item.getIdProd() == codigo) {
                    return true;
                }
            }
        }

        for (Object obj : fornecimentos) {
            for (ItemFornecido item : ((Fornecimento) obj).getItensFornecidos())
                if (item.getIdProd() == codigo) {
                    return true;
                }
        }
        return false;
    }

    public Produto pesquisar(int id) {
        for (Object obj : produtos) {
            if (((Produto) obj).getId() == id) {
                return (Produto) obj;
            }
        }
        return null;
    }

    private class AccaoBtEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getStockMinimo() >= 5) {
                for (Object obj : produtos) {
                    Produto produto = pesquisar(view.getCodigo());
                    if (produto.equals(obj)) {
                        int confirm = view
                                .confirmationMessage("Deseja actualizar o produto [" + produto.getNomeProd() + "] ?");
                        if (confirm == JOptionPane.YES_OPTION) {
                            ((Produto) obj).setNomeProd(view.getNome());
                            ((Produto) obj).setPrecoUnit(view.getPreco());
                            ((Produto) obj).setStockMinimo(view.getStockMinimo());
                            ((Produto) obj).setCodArmazem(codArmazem((String) view.getArmazem()));
                            ((Produto) obj).setDataValidade(view.getData());
                            ((Produto) obj).getDao().actualizar(produtos);
                            view.message("Dados do Produto actualizados com sucesso");
                            preencherTabela();
                        }
                        view.desabilitarBtEditar();
                        view.hobilitarBtGravar();
                        view.limparTxt();
                    }
                }
            } else {
                view.errorMessage("O stock mínimo não pode ser menor que 5");
            }
        }
    }

    private int codArmazem(String armazem) {
        for (Object obj : armazens) {
            if (((Armazem) obj).getNome().equals(armazem)) {
                return ((Armazem) obj).getId();
            }
        }
        return 0;
    }
}
