package Controller;

import Model.User;
import View.TelaUsuario;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

public class ControllerUser {
    private final TelaUsuario view;
    protected Vector<Object> users;

    public ControllerUser(TelaUsuario view, Vector<Object> users) {
        this.view = view;
        this.users = users;

        view.addActionBtGravar(new ActionBtGravar());
        view.addActionTxtPesquisar(new ActionTxtPesquisar());
        view.addActionBtApagar(new ActionBtApagar());
        view.addAccaoBtEditar(new ActionBtEditar());
        preencherTabela();
    }

    private class ActionBtGravar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getNome().equals("") || view.getSenha().equals("")) {
                if (!verificarNome(view.getNome())) {
                    User user = new User(view.getSenha(), view.getNome(), view.getNvAcesso(), geradorId());
                    if (user.getDao().escrever(user) && users.add(user)) {
                        preencherTabela();
                        view.message("Usuário  Registrado com sucesso!");
                        view.limparTxt();
                    } else {
                        view.errorMessage("Falha no Registro do usuário");
                    }
                } else {
                    view.errorMessage("O Usuário [" + view.getNome() + "] já está registrado");
                }
            } else {
                view.errorMessage("Preencha todos campos!");
            }
        }
    }

    public boolean verificarNome(String nome) {
        for (Object obj : users) {
            if (((User) obj).getNomeUsuario().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    private class ActionTxtPesquisar implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbUsuarios().getModel());
            view.getTbUsuarios().setRowSorter(rowSorter);
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
            if (view.getTbUsuarios().getSelectedRow() != -1) {
                int codigo = Integer.parseInt((String) view.getTbUsuarios().getValueAt(view.getTbUsuarios().getSelectedRow(), 0));
                User u = pesquisar(codigo);
                if (view.confirmationMessage("Deseja apagar??") == JOptionPane.YES_OPTION) {
                    users.remove(u);
                    u.getDao().actualizar(users);
                    view.message("Apagado com sucesso");
                    preencherTabela();
                }
            } else {
                view.errorMessage("Nenhuma linha seleccionada");
            }

        }
    }

    public User pesquisar(int id) {
        for (Object obj : users) {
            if (((User) obj).getId() == id) {
                return (User) obj;
            }
        }
        return null;
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome do Usuário", "Senha", "Nível de Acesso"));

        for (Object user : users) {
            Vector<String> v = new Vector<>();

            v.add(new DecimalFormat("0000").format(((User) user).getId()));
            v.add(((User) user).getNomeUsuario());
            v.add(((User) user).getSenha());
            v.add(((User) user).getAcesso());

            linhas.add(v);
        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbUsuarios().setModel(model);
    }

    private int geradorId() {
        if (users.isEmpty()) {
            return 1;
        } else {
            return ((User) users.lastElement()).getId() + 1;
        }
    }

    private class ActionBtEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Object obj : users) {
                User user = pesquisar(view.getCodigo());
                if (user.equals(obj)) {
                    int confirm = view.confirmationMessage("Deseja actualizar o usuário [" + user.getNomeUsuario() + "] ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((User) obj).setNomeUsuario(view.getNome());
                        ((User) obj).setSenha(view.getSenha());
                        ((User) obj).setAcesso(view.getNvAcesso());
                        ((User) obj).getDao().actualizar(users);
                        view.message("Dados do Usuario actualizados com sucesso");
                        preencherTabela();
                    }
                    view.desabilitarBtEditar();
                    view.hobilitarBtGravar();
                    view.limparTxt();
                }
            }
        }
    }
}
