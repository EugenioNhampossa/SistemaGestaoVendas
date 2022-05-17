package View;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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

public class TelaFornecimento extends JPanel implements MouseListener {
    private final JLabel lbTituloRegistro;
    private final JLabel lbTituloConsulta;
    private final JLabel lbImgLupa;
    private final JLabel lbProduto;
    private final JLabel lbFornecedor;
    private final JLabel lbQty;
    private final JLabel lbData;
    private final JTextField txtQty;
    private final JTextArea txtPesquisar;
    private final JComboBox<String> cbProduto;
    private final JComboBox<String> cbFornecedor;
    private final JPanel pnPrincipal;
    private final JPanel pnBt;
    private final JPanel pnTb;
    private final JPanel pnBtTb;
    private final JPanel pnLateralInser;
    private final JPanel pnPesquisa;
    private final JPanel pnCentralCons;
    private final JPanel pnCentralInser;
    private final JPanel pnExportar;
    private final JPanel pnConsultaSup;
    private final JPanel pnConsulta;
    private final JPanel pnTbForn;
    private final JPanel pnCbInsercao;
    private final JPanel pnCbConsulta;
    private final JPanel pntxt;
    private final JButton btConfirmar;
    private final JButton btExportar;
    private final JButton btAdicionar;
    private final JButton btCancelar;
    private final JButton btColapse;
    private final JButton btRemover;
    private final JScrollPane spFornecimento;
    private final JScrollPane spTabela;
    private final JScrollPane spProdForn;
    private final JTable tbFornecimento;
    private final JTable tbProdForn;
    private final JXDatePicker datePicker;
    private final JXCollapsiblePane pnInsercao;

    public TelaFornecimento() {
        this.setLayout(new GridLayout(1, 1));

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));
        spFornecimento = new JScrollPane(pnPrincipal);
        spFornecimento.setBorder(BorderFactory.createEmptyBorder());
        pnInsercao = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);
        pnInsercao.setLayout(new BorderLayout(10, 5));
        pnInsercao.setCollapsed(true);
        pntxt = new JPanel(new GridLayout(5, 2, 5, 40));
        pnBt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnBtTb = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTb = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTbForn = new JPanel(new BorderLayout());
        pnConsulta = new JPanel(new BorderLayout(5, 5));
        pnCbConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbConsulta.setBackground(new Color(255, 147, 147));
        pnConsultaSup = new JPanel(new GridLayout(1, 2));
        pnPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbInsercao = new JPanel(new BorderLayout());
        pnCbInsercao.setBackground(new Color(255, 147, 147));
        pnCbInsercao.setMaximumSize(new Dimension(90000, pnCbInsercao.getHeight()));
        pnCentralInser = new JPanel();
        pnCentralInser.setLayout(new BoxLayout(pnCentralInser, BoxLayout.Y_AXIS));
        pnLateralInser = new JPanel();
        pnLateralInser.setLayout(new BorderLayout());
        pnCentralCons = new JPanel(new GridLayout(1, 1));
        pnExportar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        lbTituloRegistro = new JLabel("Registro de Fornecimentos");
        lbTituloRegistro.setForeground(Color.WHITE);
        lbTituloConsulta = new JLabel("Hstórico de Fornecimentos");
        lbTituloConsulta.setForeground(Color.WHITE);
        lbImgLupa = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/search.png"))));
        lbProduto = new JLabel("Produto: ");
        lbFornecedor = new JLabel("Fornecedor: ");
        lbQty = new JLabel("Qty Fornecida: ");
        lbData = new JLabel("Data do Fornecimento: ");

        tbFornecimento = new JTable(new DefaultTableModel());
        tbFornecimento.setRowHeight(25);
        estiloTabela(tbFornecimento);
        tbProdForn = new JTable(new DefaultTableModel(new String[]{"Produto", "Quantidade"}, 0)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        estiloTabela(tbProdForn);
        spTabela = new JScrollPane(tbFornecimento);
        spTabela.setBorder(BorderFactory.createEmptyBorder());
        spProdForn = new JScrollPane(tbProdForn);
        spProdForn.setBorder(BorderFactory.createEmptyBorder());
        spProdForn.setBackground(Color.WHITE);
        spProdForn.getViewport().setBackground(Color.WHITE);


        txtPesquisar = new JTextArea(2, 15);
        txtPesquisar.setLineWrap(true);
        txtPesquisar.setSize(new Dimension(15, 20));
        txtPesquisar.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtQty = new JTextField();
        txtQty.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        cbProduto = new JComboBox<>(new String[]{"Seleccione o produto"});
        cbProduto.setEditable(true);
        AutoCompleteDecorator.decorate(cbProduto);
        cbProduto.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        cbProduto.setBackground(Color.WHITE);
        cbFornecedor = new JComboBox<>(new String[]{"Seleccione o Fornecedor"});
        cbFornecedor.setEditable(true);
        AutoCompleteDecorator.decorate(cbFornecedor);
        cbFornecedor.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        cbFornecedor.setBackground(Color.WHITE);
        datePicker = new JXDatePicker(new Date());
        datePicker.setFormats("MMMM, dd, yyyy");
        datePicker.getEditor().setEditable(false);
        datePicker.getEditor().setBackground(Color.WHITE);
        datePicker.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        btConfirmar = new JButton("Confirmar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/confirm.png"))));
        btConfirmar.setForeground(Color.WHITE);
        btConfirmar.setBackground(new Color(127, 232, 129));
        btAdicionar = new JButton("Adicionar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/add.png"))));
        btAdicionar.setForeground(Color.WHITE);
        btAdicionar.setBackground(new Color(0, 229, 202));
        btRemover = new JButton("Remover",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/remove.png"))));
        btRemover.setForeground(Color.WHITE);
        btRemover.setBackground(new Color(224, 79, 95));
        btCancelar = new JButton("Cancelar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/cancel.png"))));
        btCancelar.setForeground(Color.WHITE);
        btCancelar.setBackground(new Color(255, 129, 101));
        btColapse = new JButton(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowDown.png"))));
        btColapse.setBorderPainted(false);
        btColapse.setBackground(new Color(255, 147, 147));
        btColapse.setHorizontalAlignment(SwingConstants.RIGHT);
        btExportar = new JButton("Exportar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/export.png"))));
        btExportar.setForeground(Color.WHITE);
        btExportar.setBackground(new Color(96, 125, 139));


        pnCbInsercao.add(new JLabel(" "), BorderLayout.WEST);
        pnCbInsercao.add(lbTituloRegistro, BorderLayout.CENTER);
        pnCbInsercao.add(btColapse, BorderLayout.EAST);

        pnBt.add(btAdicionar);

        pntxt.add(lbProduto);
        pntxt.add(cbProduto);
        pntxt.add(lbFornecedor);
        pntxt.add(cbFornecedor);
        pntxt.add(lbQty);
        pntxt.add(txtQty);
        pntxt.add(lbData);
        pntxt.add(datePicker);
        pntxt.add(new JLabel());
        pntxt.add(pnBt);

        pnTbForn.add(spProdForn, BorderLayout.CENTER);
        pnTbForn.add(pnBtTb, BorderLayout.SOUTH);


        pnCentralInser.add(new JPanel());
        pnCentralInser.add(pntxt);
        pnCentralInser.add(new JPanel());
        pnCentralInser.add(new JPanel());
        pnCentralInser.add(new JPanel());

        pnTb.add(pnTbForn);
        pnBtTb.add(btCancelar);
        pnBtTb.add(btRemover);
        pnBtTb.add(btConfirmar);

        pnLateralInser.add(pnTb, BorderLayout.CENTER);

        pnInsercao.add(pnCentralInser, BorderLayout.CENTER);
        pnInsercao.add(pnLateralInser, BorderLayout.EAST);
        pnInsercao.add(new JLabel(" "), BorderLayout.WEST);

        pnExportar.add(btExportar);

        pnCbConsulta.add(lbTituloConsulta);
        pnPesquisa.add(txtPesquisar);
        pnPesquisa.add(lbImgLupa);
        pnConsultaSup.add(pnPesquisa);
        pnConsultaSup.add(pnExportar);

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

        this.add(spFornecimento);

        btColapse.addMouseListener(this);
        btColapse.addActionListener(pnInsercao.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));

    }

    private void estiloTabela(JTable tb) {
        tb.setShowGrid(false);
        tb.setSelectionBackground(new Color(136, 224, 200));
        tb.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 183, 164)));
        tb.getTableHeader().setBackground(new Color(0, 183, 164));
        tb.setShowHorizontalLines(true);
        tb.setAutoCreateRowSorter(true);
    }

    public void fecharPnInsercao() {
        btColapse.setIcon(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowDown.png"))));
    }

    public void abrirPnInsercao() {
        btColapse.setIcon(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/triangle-arrowUp.png"))));
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
    }

    public void addAccaoBtConfirmar(ActionListener action) {
        btConfirmar.addActionListener(action);
    }

    public void addAccaoBtRemover(ActionListener action) {
        btRemover.addActionListener(action);
    }

    public void addAccaoBtAdicionar(ActionListener action) {
        btAdicionar.addActionListener(action);
    }

    public void addAccaoBtCancelar(ActionListener action) {
        btCancelar.addActionListener(action);
    }

    public void addAccaoBtExportar(ActionListener action) {
        btExportar.addActionListener(action);
    }


    public void addAccaoTxtPesquisar(KeyListener action) {
        txtPesquisar.addKeyListener(action);
    }

    public JTable getTbFornecimento() {
        return tbFornecimento;
    }

    public int getQty() throws NumberFormatException {
        return Integer.parseInt(txtQty.getText().trim());
    }

    public Object getProduto() {
        return cbProduto.getSelectedItem();
    }

    public Date getData() {
        return datePicker.getDate();

    }

    public Object getFornecedor() {
        return cbFornecedor.getSelectedItem();
    }

    public DefaultTableModel getProForn() {
        return (DefaultTableModel) tbProdForn.getModel();
    }

    public JTable getTbProdForn() {
        return tbProdForn;
    }

    public String getPesquisa() {
        return txtPesquisar.getText().trim();
    }

    public void limparTxt() {
        txtQty.setText(null);
    }

    public void limparTb() {
        tbProdForn.setModel(new DefaultTableModel(new String[]{"Produto", "Quantidade"}, 0));
    }

    public void addProduto(String produto) {
        cbProduto.addItem(produto);
    }

    public void addFornecedor(String fornecedor) {
        cbFornecedor.addItem(fornecedor);
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

