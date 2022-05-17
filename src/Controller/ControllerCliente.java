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

import Model.Cliente;
import Model.Venda;
import View.TelaClientes;

public class ControllerCliente {
    private final TelaClientes view;
    private final Vector<Object> clientes;
    private final Vector<Object> vendas;

    public ControllerCliente(TelaClientes view, Vector<Object> clientes, Vector<Object> vendas) {
        this.view = view;
        this.clientes = clientes;
        this.vendas = vendas;
        view.addActionBtGravar(new ActionBtGravar());
        view.addActionTxtPesquisar(new ActionTxtPesquisar());
        view.addActionBtApagar(new ActionBtApagar());
        view.addAccaoBtEditar(new ActionBtEditar());
        preencherTabela();
    }

    private class ActionBtGravar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getNome().equals("")) {
                if (!verificarNome(view.getNome())) {
                    Cliente cliente = new Cliente(geradorId(), view.getNome(), view.getEmail(), view.getCell());
                    if (cliente.getDao().escrever(cliente) && clientes.add(cliente)) {
                        preencherTabela();
                        view.message("Cliente Registrado com sucesso!");
                        view.limparTxt();
                    } else {
                        view.errorMessage("Falha no Registro do cliente");
                    }
                } else {
                    view.errorMessage("O Cliente [" + view.getNome() + "] já está registrado");
                }
            } else {
                view.errorMessage("Preencha o campo obrigatório [Nome]");
            }
        }
    }

    public boolean verificarNome(String nome) {
        for (Object obj : clientes) {
            if (((Cliente) obj).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private class ActionTxtPesquisar implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbClientes().getModel());
            view.getTbClientes().setRowSorter(rowSorter);
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
            if (view.getTbClientes().getSelectedRow() != -1) {
                int codigo = Integer
                        .parseInt((String) view.getTbClientes().getValueAt(view.getTbClientes().getSelectedRow(), 0));
                if (!verificar(codigo)) {
                    Cliente c = pesquisar(codigo);
                    if (view.confirmationMessage("Deseja apagar??") == JOptionPane.YES_OPTION) {
                        clientes.remove(c);
                        c.getDao().actualizar(clientes);
                        view.message("Apagado com sucesso");
                        preencherTabela();
                    }
                } else {
                    view.errorMessage("Não pode apagar os dados do Cliente\nO cliente está relacionado a uma venda");
                }
            } else {
                view.errorMessage("Nenhuma linha seleccionada");
            }

        }
    }

    private boolean verificar(int codigo) {
        for (Object obj : vendas) {
            if (((Venda) obj).getIdCli() == codigo) {
                return true;
            }
        }
        return false;
    }

    public Cliente pesquisar(int id) {
        for (Object obj : clientes) {
            if (((Cliente) obj).getId() == id) {
                return (Cliente) obj;
            }
        }
        return null;
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Celular", "Email"));

        for (Object cliente : clientes) {
            Vector<String> v = new Vector<>();

            v.add(new DecimalFormat("0000").format(((Cliente) cliente).getId()));
            v.add(((Cliente) cliente).getNome());
            v.add(((Cliente) cliente).getEmail());
            v.add(((Cliente) cliente).getCell());

            linhas.add(v);

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbClientes().setModel(model);
    }

    private int geradorId() {
        if (clientes.isEmpty()) {
            return 1;
        } else {
            return ((Cliente) clientes.lastElement()).getId() + 1;
        }
    }

    private class ActionBtEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Object obj : clientes) {
                Cliente c = pesquisar(view.getCodigo());
                if (c.equals(obj)) {
                    int confirm = view.confirmationMessage("Deseja actualizar o cliente [" + c.getNome() + "] ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((Cliente) obj).setNome(view.getNome());
                        ((Cliente) obj).setEmail(view.getEmail());
                        ((Cliente) obj).setCell(view.getCell());
                        ((Cliente) obj).getDao().actualizar(clientes);
                        view.message("Dados do Cliente actualizados com sucesso");
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
