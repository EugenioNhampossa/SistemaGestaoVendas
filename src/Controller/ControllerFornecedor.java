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

import Model.Fornecedor;
import Model.Fornecimento;
import View.TelaFornecedor;

public class ControllerFornecedor {

    private final TelaFornecedor view;
    private final Vector<Object> fornecedores;
    private final Vector<Object> fornecimentos;

    public ControllerFornecedor(TelaFornecedor view, Vector<Object> fornecedores, Vector<Object> fornecimentos) {
        this.view = view;
        this.fornecedores = fornecedores;
        this.fornecimentos = fornecimentos;
        view.addAccaoBtGravar(new ActionBtGravar());
        view.addAccaoTxtPesquisar(new ActionTxtPesquisar());
        view.addAccaoBtApagar(new ActionBtApagar());
        view.addAccaoBtEditar(new ActionBtEditar());

        preencherTabela();
    }

    private class ActionBtGravar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getNome().equals("")) {
                try {
                    if (!verificarNome(view.getNome())) {
                        Fornecedor forn = new Fornecedor(geradorId(), view.getNome(), view.getEmail(), view.getCell(),
                                view.getNuit());
                        if (forn.getDao().escrever(forn) && fornecedores.add(forn)) {
                            preencherTabela();
                            view.message("Fornecedor Registrado com sucesso!");
                            view.limparTxt();
                        } else {
                            view.errorMessage("Falha no Registro do Fornecedor");
                        }
                    } else {
                        view.errorMessage("O Fornecedor [" + view.getNome() + "] já está registrado");
                    }
                } catch (NumberFormatException ex) {
                    view.errorMessage("Formato de Nuit Inválido!");
                }
            } else {
                view.errorMessage("Preencha o campo obrigatório [Nome]");
            }
        }
    }

    public boolean verificarNome(String nome) {
        for (Object obj : fornecedores) {
            if (((Fornecedor) obj).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private class ActionTxtPesquisar implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbFornecedores().getModel());
            view.getTbFornecedores().setRowSorter(rowSorter);
            rowSorter.setRowFilter(RowFilter.regexFilter(view.getPesquisa()));
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }
    }

    private class ActionBtApagar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTbFornecedores().getSelectedRow() != -1) {
                int codigo = Integer.parseInt(
                        (String) view.getTbFornecedores().getValueAt(view.getTbFornecedores().getSelectedRow(), 0));
                if (!verificar(codigo)) {
                    Fornecedor f = pesquisar(codigo);
                    if (view.confirmationMessage("Deseja apagar??") == JOptionPane.YES_OPTION) {
                        fornecedores.remove(f);
                        f.getDao().actualizar(fornecedores);
                        view.message("Apagado com sucesso");
                        preencherTabela();
                    }
                } else {
                    view.errorMessage(
                            "Os Dados do fornecedor não podem ser apagados\nO fornecedor está relacionado a um fornecimento");
                }
            } else {
                view.errorMessage("Nenhama linha seleccionada!");
            }

        }
    }

    public boolean verificar(int codigo) {
        for (Object obj : fornecimentos) {
            if (codigo == ((Fornecimento) obj).getIdForn()) {
                return true;
            }
        }
        return false;
    }

    public Fornecedor pesquisar(int id) {
        for (Object obj : fornecedores) {
            if (((Fornecedor) obj).getId() == id) {
                return (Fornecedor) obj;
            }
        }
        return null;
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Nuit", "Celular", "Email"));

        for (Object forn : fornecedores) {
            Vector<String> v = new Vector<>();

            v.add(new DecimalFormat("0000").format(((Fornecedor) forn).getId()));
            v.add(((Fornecedor) forn).getNome());
            v.add(String.valueOf(((Fornecedor) forn).getNuit()));
            v.add(((Fornecedor) forn).getCell());
            v.add(((Fornecedor) forn).getEmail());

            linhas.add(v);

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbFornecedores().setModel(model);
    }

    private int geradorId() {
        if (fornecedores.isEmpty()) {
            return 1;
        } else {
            return ((Fornecedor) fornecedores.lastElement()).getId() + 1;
        }
    }

    private class ActionBtEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Object obj : fornecedores) {
                Fornecedor fornecedor = pesquisar(view.getCodigo());
                if (fornecedor.equals(obj)) {
                    int confirm = view.confirmationMessage(
                            "Deseja actualizar os dados do fornecedor [" + fornecedor.getNome() + "] ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((Fornecedor) obj).setNome(view.getNome());
                        ((Fornecedor) obj).setEmail(view.getEmail());
                        ((Fornecedor) obj).setCell(view.getCell());
                        ((Fornecedor) obj).setNuit(view.getNuit());
                        ((Fornecedor) obj).getDao().actualizar(fornecedores);
                        view.message("Dados do Fornecedor actualizados com sucesso");
                        view.limparTxt();
                        preencherTabela();
                    }
                    view.desabilitarBtEditar();
                    view.hobilitarBtGravar();
                    view.limparTxt();
                    break;
                }
            }
        }
    }
}
