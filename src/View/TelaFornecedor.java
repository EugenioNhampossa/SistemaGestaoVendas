package View;

import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class TelaFornecedor extends JPanel implements MouseListener {
    private final JLabel lbTituloRegistro;
    private final JLabel lbTituloConsulta;
    private final JLabel lbImgLupa;
    private final JLabel lbNome;
    private final JLabel lbNuit;
    private final JLabel lbEmail;
    private final JLabel lbCelular;
    private final JTextField txtNome;
    private final JTextField txtCell;
    private final JTextField txtEmail;
    private final JTextField txtNuit;
    private final JTextArea txtPesquisar;
    private final JPanel pnPrincipal;
    private final JPanel pnBt;
    private final JPanel pnBtApagar;
    private final JPanel pnPesquisa;
    private final JPanel pnCentralCons;
    private final JPanel pnCentralInser;
    private final JPanel pnConsultaSup;
    private final JPanel pnConsulta;
    private final JPanel pnCbInsercao;
    private final JPanel pnCbConsulta;
    private final JButton btGravar;
    private final JButton btApagar;
    private final JButton btEditar;
    private final JButton btColapse;
    private final JScrollPane spProdutos;
    private final JScrollPane spTabela;
    private final JTable tbFornecedores;
    private final JXCollapsiblePane  pnInsercao;
    private int codigo;

    public TelaFornecedor() {
        this.setLayout(new GridLayout(1, 1));

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));
        spProdutos = new JScrollPane(pnPrincipal);
        spProdutos.setBorder(BorderFactory.createEmptyBorder());
        pnInsercao = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);
        pnInsercao.setLayout(new BorderLayout(5, 5));
        pnInsercao.setCollapsed(true);
        pnBt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnConsulta = new JPanel(new BorderLayout(5, 5));
        pnCbConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbConsulta.setBackground(new Color(255, 147, 147));
        pnConsultaSup = new JPanel(new GridLayout(1, 2));
        pnBtApagar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbInsercao = new JPanel(new BorderLayout());
        pnCbInsercao.setBackground(new Color(255, 147, 147));
        pnCbInsercao.setMaximumSize(new Dimension(90000, pnCbInsercao.getHeight()));
        pnCentralInser = new JPanel(new GridLayout(2, 4, 10, 10));
        pnCentralCons = new JPanel(new GridLayout(1, 1));

        lbTituloRegistro = new JLabel("Registro de Fornecedores");
        lbTituloRegistro.setForeground(Color.WHITE);
        lbTituloConsulta = new JLabel("Lista de Fornecedores");
        lbTituloConsulta.setForeground(Color.WHITE);
        lbImgLupa =  new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/search.png"))));
        lbNome = new JLabel("Nome do Fornecedor: ");
        lbNuit = new JLabel("Nuit: ");
        lbEmail = new JLabel("Email: ");
        lbEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lbCelular = new JLabel("Celular: ");
        lbCelular.setHorizontalAlignment(SwingConstants.CENTER);

        tbFornecedores = new JTable(new DefaultTableModel());
        tbFornecedores.setShowGrid(false);
        tbFornecedores.setSelectionBackground(new Color(136, 224, 200));
        tbFornecedores.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 183, 164)));
        tbFornecedores.getTableHeader().setBackground(new Color(0, 183, 164));
        tbFornecedores.setShowHorizontalLines(true);
        tbFornecedores.setRowHeight(25);
        tbFornecedores.setAutoCreateRowSorter(true);
        spTabela = new JScrollPane(tbFornecedores);
        spTabela.setBorder(BorderFactory.createEmptyBorder());

        txtNome = new JTextField();
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtCell = new JTextField();
        txtCell.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtPesquisar = new JTextArea(2, 15);
        txtPesquisar.setLineWrap(true);
        txtPesquisar.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtEmail = new JTextField();
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtNuit = new JTextField();
        txtNuit.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        btApagar = new JButton("Apagar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/delete.png"))));
        btApagar.setBackground(new Color(255, 129, 101));
        btApagar.setForeground(Color.WHITE);
        btEditar = new JButton("Editar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/edit.png"))));
        btEditar.setBackground(new Color(79, 195, 247));
        btEditar.setForeground(Color.WHITE);
        btEditar.setEnabled(false);
        btGravar = new JButton("Gravar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/save.png"))));
        btGravar.setForeground(Color.WHITE);
        btGravar.setBackground(new Color(0, 229, 202));
        btColapse = new JButton(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowDown.png"))));
        btColapse.setBorderPainted(false);
        btColapse.setBackground(new Color(255, 147, 147));
        btColapse.setHorizontalAlignment(SwingConstants.RIGHT);

        pnCbInsercao.add(new JLabel(" "), BorderLayout.WEST);
        pnCbInsercao.add(lbTituloRegistro, BorderLayout.CENTER);
        pnCbInsercao.add(btColapse, BorderLayout.EAST);

        pnCentralInser.add(lbNome);
        pnCentralInser.add(txtNome);
        pnCentralInser.add(lbCelular);
        pnCentralInser.add(txtCell);
        pnCentralInser.add(lbNuit);
        pnCentralInser.add(txtNuit);
        pnCentralInser.add(lbEmail);
        pnCentralInser.add(txtEmail);

        pnBt.add(btEditar);
        pnBt.add(btGravar);

        pnInsercao.add(new JLabel(" "), BorderLayout.NORTH);
        pnInsercao.add(pnCentralInser, BorderLayout.CENTER);
        pnInsercao.add(new JLabel(" "), BorderLayout.EAST);
        pnInsercao.add(new JLabel(" "), BorderLayout.WEST);
        pnInsercao.add(pnBt, BorderLayout.SOUTH);


        pnCbConsulta.add(lbTituloConsulta);
        pnPesquisa.add(txtPesquisar);
        pnPesquisa.add(lbImgLupa);
        pnBtApagar.add(btApagar);
        pnConsultaSup.add(pnPesquisa);
        pnConsultaSup.add(pnBtApagar);

        pnCentralCons.add(spTabela);
        pnConsulta.add(new JLabel(" "), BorderLayout.SOUTH);
        pnConsulta.add(pnCentralCons, BorderLayout.CENTER);
        pnConsulta.add(pnConsultaSup, BorderLayout.NORTH);
        pnConsulta.add(new JLabel(" "), BorderLayout.EAST);
        pnConsulta.add(new JLabel(" "), BorderLayout.WEST);

        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnCbInsercao);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnInsercao);
        pnPrincipal.add(pnCbConsulta);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnConsulta);

        this.add(spProdutos);

        btColapse.addMouseListener(this);
        btColapse.addActionListener(pnInsercao.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        tbFornecedores.addMouseListener(this);

    }

    public void fecharPnInsercao() {
        btColapse.setIcon(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowDown.png"))));
        limparTxt();
        btEditar.setEnabled(false);
        btGravar.setEnabled(true);
    }

    public void abrirPnInsercao() {
        btColapse.setIcon(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowUp.png"))));
        btGravar.setEnabled(true);
    }
    public void hobilitarBtGravar(){btGravar.setEnabled(true);}


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btColapse) {
            if (pnInsercao.isCollapsed()) {
                fecharPnInsercao();
            } else {
                abrirPnInsercao();

            }
        }

        if (e.getClickCount() == 2 && e.getSource() == tbFornecedores) {
            codigo = Integer.parseInt((String) tbFornecedores.getValueAt(tbFornecedores.getSelectedRow(), 0));
            abrirPnInsercao();
            txtNome.setText((String) tbFornecedores.getValueAt(tbFornecedores.getSelectedRow(), 1));
            txtNuit.setText((String) tbFornecedores.getValueAt(tbFornecedores.getSelectedRow(), 2));
            txtCell.setText((String) tbFornecedores.getValueAt(tbFornecedores.getSelectedRow(), 3));
            txtEmail.setText((String) tbFornecedores.getValueAt(tbFornecedores.getSelectedRow(), 4));
            btEditar.setEnabled(true);
            btGravar.setEnabled(false);
            pnInsercao.setCollapsed(false);
        }

        pnPrincipal.updateUI();

    }

    public int getCodigo() {
        return codigo;
    }

    public void addAccaoBtEditar(ActionListener accao) {
        btEditar.addActionListener(accao);
    }

    public void desabilitarBtEditar() {
        btEditar.setEnabled(false);
    }

    public void addAccaoBtGravar(ActionListener action) {
        btGravar.addActionListener(action);
    }

    public void addAccaoBtApagar(ActionListener action) {
        btApagar.addActionListener(action);
    }

    public void addAccaoTxtPesquisar(KeyListener action) {
        txtPesquisar.addKeyListener(action);
    }

    public JTable getTbFornecedores() {
        return tbFornecedores;
    }

    public void message(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmationMessage(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirmação", JOptionPane.YES_NO_OPTION);
    }

    public void limparTxt() {
        txtNome.setText(null);
        txtEmail.setText(null);
        txtCell.setText(null);
        txtNuit.setText(null);
    }

    public String getNome() {
        return txtNome.getText().trim();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

    public String getCell() {
        return txtCell.getText().trim();
    }

    public long getNuit() throws NumberFormatException {
        return Long.parseLong(txtNuit.getText().trim());
    }


    public String getPesquisa() {
        return txtPesquisar.getText().trim();
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

