package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Model.Armazem;
import Model.Produto;
import View.TelaArmazens;

public class ControllerArmazem {

    private final TelaArmazens view;
    private final Vector<Object> armazens;
    private final Vector<Object> produtos;

    public ControllerArmazem(TelaArmazens view, Vector<Object> armazens, Vector<Object> produtos) {
        this.view = view;
        this.armazens = armazens;
        this.produtos = produtos;
        view.addAccaoBtGravar(new AccaoBtGravar());
        view.addAccaoBtApagar(new AccaoBtApagar());
        view.addAccaoTxtPesquisar(new AccaoPesquisar());
        view.addAccaoBtEditar(new AccaoBtEditar());
        preencherTabela();
    }

    private class AccaoBtGravar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getNomeArmazem().equals("")) {
                if (verificarNome(view.getNomeArmazem())) {
                    Armazem a = new Armazem(geradorId(), view.getNomeArmazem());
                    if (armazens.add(a) && a.getDao().escrever(a)) {
                        view.limparTxt();
                        view.message("Dados Registrados com sucesso");
                    } else {
                        view.errorMessage("Falha na Gravação dos dados");
                    }
                } else {
                    view.errorMessage("O armazém [" + view.getNomeArmazem() + "] já existe\nInsira um nome Diferente");
                }
            } else {
                view.errorMessage("Preencha o campo obrigatório [Nome]");
            }
            preencherTabela();
        }
    }

    public boolean verificarNome(String nome) {
        for (Object obj : armazens) {
            if (((Armazem) obj).getNome().equals(nome)) {
                return false;
            }
        }
        return true;
    }

    private class AccaoBtApagar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTbArmazens().getSelectedRow() != -1) {
                int codigo = Integer
                        .parseInt((String) view.getTbArmazens().getValueAt(view.getTbArmazens().getSelectedRow(), 0));
                if (!verificar(codigo)) {
                    Armazem a = pesquisar(codigo);
                    if (view.confirmationMessage("Deseja apagar??") == JOptionPane.YES_OPTION) {
                        armazens.remove(a);
                        a.getDao().actualizar(armazens);
                        view.message("Apagado com sucesso");
                        preencherTabela();
                    }
                } else {
                    view.errorMessage(
                            "O Registro do armazém não pode ser apagado\nO armazém está ralacionado a um produto.");
                }
            } else {
                view.errorMessage("Nenhuma linha seleccionada!");
            }
        }
    }

    private boolean verificar(int codigo) {
        for (Object obj : produtos) {
            if (((Produto) obj).getCodArmazem() == codigo) {
                return true;
            }
        }
        return false;
    }

    private class AccaoBtEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (verificarNome(view.getNomeArmazem())) {
                for (Object obj : armazens) {
                    Armazem a = pesquisar(view.getCodigo());
                    if (a.equals(obj)) {
                        int c = view.confirmationMessage("Deseja actualizar o armazém [" + a.getNome() + "] ?");
                        if (c == JOptionPane.YES_OPTION) {
                            ((Armazem) obj).setNome(view.getNomeArmazem());
                            ((Armazem) obj).getDao().actualizar(armazens);
                            view.message("Armazém actualizado com sucesso");
                            preencherTabela();
                        }
                        view.desabilitarBtEditar();
                        view.hobilitarBtGravar();
                        view.limparTxt();
                        break;
                    }
                }
            } else {
                view.errorMessage("O nome introduzido já existe!\nEscolha outro");
            }
        }
    }

    public Armazem pesquisar(int id) {
        for (Object obj : armazens) {
            if (((Armazem) obj).getId() == id) {
                return (Armazem) obj;
            }
        }
        return null;
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Codigo", "Nome"));

        for (Object armazem : armazens) {
            Vector<String> v = new Vector<>();

            v.add(new DecimalFormat("0000").format(((Armazem) armazem).getId()));
            v.add(((Armazem) armazem).getNome());

            linhas.add(v);

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbArmazens().setModel(model);

    }

    private int geradorId() {
        if (armazens.isEmpty()) {
            return 1;
        } else {
            return ((Armazem) armazens.lastElement()).getId() + 1;
        }
    }

    private class AccaoPesquisar implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbArmazens().getModel());
            view.getTbArmazens().setRowSorter(rowSorter);
            rowSorter.setRowFilter(RowFilter.regexFilter(view.getPesquisa()));
        }
    }

}
