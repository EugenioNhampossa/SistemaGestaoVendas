package View;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.Date;
import java.util.Objects;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class TelaProdutos extends JPanel implements MouseListener {
    private final JLabel lbTituloRegistro;
    private final JLabel lbTituloConsulta;
    private final JLabel lbImgLupa;
    private final JLabel lbValidade;
    private final JLabel lbNome;
    private final JLabel lbArmazem;
    private final JLabel lbStockMinimo;
    private final JLabel lbPrecoUnit;
    private final JTextField txtNome;
    private final JTextField txtPreco;
    private final JTextField txtStockminimo;
    private final JTextArea txtPesquisar;
    private final JComboBox<String> cbArmazem;
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
    private final JTable tbProdutos;
    private final JXCollapsiblePane pnInsercao;
    private final JXDatePicker datePicker;
    private int codigo;

    public TelaProdutos() {
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
        pnCentralInser = new JPanel(new GridLayout(2, 5, 10, 10));
        pnCentralCons = new JPanel(new GridLayout(1, 1));

        lbTituloRegistro = new JLabel("Registro de Produtos");
        lbTituloRegistro.setForeground(Color.WHITE);
        lbTituloConsulta = new JLabel("Inventário");
        lbTituloConsulta.setForeground(Color.WHITE);
        lbImgLupa = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/search.png"))));
        lbNome = new JLabel("Nome do produto: ");
        lbArmazem = new JLabel("Armazém: ");
        lbArmazem.setHorizontalAlignment(SwingConstants.CENTER);
        lbStockMinimo = new JLabel("Stock Mínimo: ");
        lbStockMinimo.setHorizontalAlignment(SwingConstants.CENTER);
        lbPrecoUnit = new JLabel("Preço Unitário: ");
        lbPrecoUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbValidade = new JLabel("Prazo de Validade: ");

        tbProdutos = new JTable(new DefaultTableModel());
        tbProdutos.setShowGrid(false);
        tbProdutos.setShowHorizontalLines(true);
        tbProdutos.setRowHeight(25);
        tbProdutos.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 183, 164)));
        tbProdutos.getTableHeader().setBackground(new Color(0, 183, 164));
        tbProdutos.setSelectionBackground(new Color(208, 247, 237));
        tbProdutos.setAutoCreateRowSorter(true);
        spTabela = new JScrollPane(tbProdutos);
        spTabela.setBorder(BorderFactory.createEmptyBorder());

        txtNome = new JTextField();
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtPreco = new JTextField();
        txtPreco.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtPesquisar = new JTextArea(2, 15);
        txtPesquisar.setLineWrap(true);
        txtPesquisar.setSize(new Dimension(15, 20));
        txtPesquisar.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtStockminimo = new JTextField();
        txtStockminimo.setText("5");
        txtStockminimo.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        datePicker = new JXDatePicker(new Date());
        datePicker.setFormats("MMMM, dd, yyyy");
        datePicker.getEditor().setEditable(false);
        datePicker.getEditor().setBackground(Color.WHITE);
        datePicker.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        cbArmazem = new JComboBox<>(new String[]{"Escolha o Armazém"});
        cbArmazem.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        cbArmazem.setBackground(Color.WHITE);
        cbArmazem.setEditable(true);
        AutoCompleteDecorator.decorate(cbArmazem);

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
        pnCentralInser.add(lbPrecoUnit);
        pnCentralInser.add(txtPreco);
        pnCentralInser.add(lbArmazem);
        pnCentralInser.add(cbArmazem);
        pnCentralInser.add(lbStockMinimo);
        pnCentralInser.add(txtStockminimo);
        pnCentralInser.add(lbValidade);
        pnCentralInser.add(datePicker);
        pnCentralInser.add(new JLabel());
        pnCentralInser.add(new JLabel());


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
        tbProdutos.addMouseListener(this);

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


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btColapse) {
            if (pnInsercao.isCollapsed()) {
                fecharPnInsercao();
            } else {
                abrirPnInsercao();

            }
        }

        if (e.getClickCount() == 2 && e.getSource() == tbProdutos) {
            codigo = Integer.parseInt((String) tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 0));
            abrirPnInsercao();
            txtNome.setText((String) tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 1));
            txtPreco.setText((String) tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 2));
            cbArmazem.setSelectedItem(tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 3));
            txtStockminimo.setText((String) tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 5));
            datePicker.getEditor().setText((String) tbProdutos.getValueAt(tbProdutos.getSelectedRow(), 6));
            btGravar.setEnabled(false);
            btEditar.setEnabled(true);
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

    public JTable getTbProdutos() {
        return tbProdutos;
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
        txtStockminimo.setText("5");
        txtPreco.setText(null);
        cbArmazem.setSelectedIndex(0);
    }

    public String getNome() {
        return txtNome.getText().trim();
    }

    public int getStockMinimo() throws NumberFormatException {
        return Integer.parseInt(txtStockminimo.getText().trim());
    }

    public double getPreco() throws NumberFormatException {
        return Double.parseDouble(txtPreco.getText().trim());
    }

    public Object getArmazem() {
        return cbArmazem.getSelectedItem();
    }

    public String getPesquisa() {
        return txtPesquisar.getText().trim();
    }

    public void addArmazem(String armazem) {
        cbArmazem.addItem(armazem);
    }

    public Date getData() {
        return datePicker.getDate();

    }
    public void hobilitarBtGravar(){btGravar.setEnabled(true);}

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
