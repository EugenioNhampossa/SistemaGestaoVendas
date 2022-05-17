package View;

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Objects;

public class TelaVenda extends JPanel implements MouseListener {
    private final JLabel lbMTLiq;
    private final JLabel lbIvaMoeda;
    private final JLabel lbTituloRegistro;
    private final JLabel lbTLiq;
    private final JLabel lbTLiqNr;
    private final JLabel lbIva;
    private final JLabel lbIvaNr;
    private final JLabel lbTotal;
    private final JLabel lbTotalNr;
    private final JLabel lbMoeda;
    private final JLabel lbProduto;
    private final JLabel lbCliente;
    private final JLabel lbQty;
    private final JLabel lbData;
    private final JTextField txtQty;
    private final JComboBox<String> cbProduto;
    private final JComboBox<String> cbCliente;
    private final JPanel pnPrincipal;
    private final JPanel pnTotal;
    private final JPanel pnIVA;
    private final JPanel pnBt;
    private final JPanel pnTb;
    private final JPanel pnTLiq;
    private final JPanel pnInsercao;
    private final JPanel pnBtTb;
    private final JPanel pnLateralInser;
    private final JPanel pnCentralInser;
    private final JPanel pnTbVenda;
    private final JPanel pnCbInsercao;
    private final JPanel pntxt;
    private final JButton btConfirmar;
    private final JButton btAdicionar;
    private final JButton btCancelar;
    private final JButton btRemover;
    private final JScrollPane spProdVenda;
    private final JScrollPane spVenda;
    private final JTable tbProdVenda;
    private final JXDatePicker datePicker;

    public TelaVenda() {
        this.setLayout(new GridLayout(1, 1));

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));
        spVenda = new JScrollPane(pnPrincipal);
        spVenda.setBorder(BorderFactory.createEmptyBorder());
        pnInsercao = new JPanel(new BorderLayout(10, 5));
        pntxt = new JPanel(new GridLayout(5, 2, 5, 40));
        pnBt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnBtTb = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTb = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnIVA = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnIVA.setBackground(Color.WHITE);
        pnTLiq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTbVenda = new JPanel();
        pnTbVenda.setLayout(new BoxLayout(pnTbVenda, BoxLayout.Y_AXIS));
        pnCbInsercao = new JPanel(new BorderLayout());
        pnCbInsercao.setBackground(new Color(255, 147, 147));
        pnCbInsercao.setMaximumSize(new Dimension(90000, 30));
        pnCbInsercao.setPreferredSize(new Dimension(0, 30));
        pnCentralInser = new JPanel();
        pnCentralInser.setLayout(new BoxLayout(pnCentralInser, BoxLayout.Y_AXIS));
        pnLateralInser = new JPanel();
        pnLateralInser.setLayout(new BorderLayout());

        lbTituloRegistro = new JLabel("Nova Venda");
        lbTituloRegistro.setForeground(Color.WHITE);
        lbProduto = new JLabel("Produto: ");
        lbCliente = new JLabel("Cliente: ");
        lbQty = new JLabel("Quantidade: ");
        lbData = new JLabel("Data da Venda: ");
        lbTotal = new JLabel("Total: ");
        estiloLabel(lbTotal, 35);
        lbTotalNr = new JLabel("0,00");
        estiloLabel(lbTotalNr, 35);
        lbMoeda = new JLabel("MT");
        estiloLabel(lbMoeda, 35);
        lbMTLiq = new JLabel("MT");
        estiloLabel(lbMTLiq, 35);
        lbIva = new JLabel("Total IVA: ");
        estiloLabel(lbIva, 20);
        lbIvaNr = new JLabel("0,00");
        estiloLabel(lbIvaNr, 20);
        lbIvaMoeda = new JLabel("MT");
        estiloLabel(lbIvaMoeda, 20);
        lbTLiq = new JLabel("T.Líquido: ");
        estiloLabel(lbTLiq, 35);
        lbTLiqNr = new JLabel("0,00");
        estiloLabel(lbTLiqNr, 35);

        tbProdVenda = new JTable(new DefaultTableModel(new String[]{"Produto", "Quantidade", "P/unidade", "Preço"}, 0)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        estiloTabela(tbProdVenda);
        spProdVenda = new JScrollPane(tbProdVenda);
        spProdVenda.setBorder(BorderFactory.createEmptyBorder());
        spProdVenda.setBackground(Color.WHITE);
        spProdVenda.getViewport().setBackground(Color.WHITE);

        txtQty = new JTextField();
        txtQty.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        cbProduto = new JComboBox<>(new String[]{"Seleccione o produto"});
        cbProduto.setEditable(true);
        AutoCompleteDecorator.decorate(cbProduto);
        cbProduto.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        cbProduto.setBackground(Color.WHITE);
        cbCliente = new JComboBox<>(new String[]{"Seleccione o Cliente"});
        cbCliente.setEditable(true);
        AutoCompleteDecorator.decorate(cbCliente);
        cbCliente.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        cbCliente.setBackground(Color.WHITE);
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

        pnCbInsercao.add(new JLabel(" "), BorderLayout.WEST);
        pnCbInsercao.add(lbTituloRegistro, BorderLayout.CENTER);

        pnBt.add(btAdicionar);

        pntxt.add(lbProduto);
        pntxt.add(cbProduto);
        pntxt.add(lbCliente);
        pntxt.add(cbCliente);
        pntxt.add(lbQty);
        pntxt.add(txtQty);
        pntxt.add(lbData);
        pntxt.add(datePicker);
        pntxt.add(new JLabel());
        pntxt.add(pnBt);

        pnIVA.add(lbIva);
        pnIVA.add(lbIvaNr);
        pnIVA.add(lbIvaMoeda);

        pnTbVenda.add(spProdVenda);
        pnTbVenda.add(pnIVA);
        pnTbVenda.add(pnBtTb);

        pnTotal.add(lbTotal);
        pnTotal.add(lbTotalNr);
        pnTotal.add(lbMoeda);

        pnTLiq.add(lbTLiq);
        pnTLiq.add(lbTLiqNr);
        pnTLiq.add(lbMTLiq);

        pnCentralInser.add(new JPanel());
        pnCentralInser.add(pntxt);
        pnCentralInser.add(new JPanel());
        pnCentralInser.add(pnTotal);
        pnCentralInser.add(pnTLiq);

        pnTb.add(pnTbVenda);
        pnBtTb.add(btCancelar);
        pnBtTb.add(btRemover);
        pnBtTb.add(btConfirmar);

        pnLateralInser.add(pnTb, BorderLayout.CENTER);

        pnInsercao.add(pnCentralInser, BorderLayout.CENTER);
        pnInsercao.add(pnLateralInser, BorderLayout.EAST);
        pnInsercao.add(new JLabel(" "), BorderLayout.WEST);


        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnCbInsercao);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnInsercao);
        pnPrincipal.add(new JLabel(" "));

        this.add(spVenda);

    }

    private void estiloLabel(JLabel lb, int tamanho) {
        lb.setForeground(new Color(255, 5, 5));
        lb.setFont(new Font(lbCliente.getFont().getFontName(), Font.BOLD, tamanho));
    }

    private void estiloTabela(JTable tb) {
        tb.setShowGrid(false);
        tb.setSelectionBackground(new Color(136, 224, 200));
        tb.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 183, 164)));
        tb.getTableHeader().setBackground(new Color(0, 183, 164));
        tb.setShowHorizontalLines(true);
        tb.setAutoCreateRowSorter(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

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


    public int getQty() throws NumberFormatException {
        return Integer.parseInt(txtQty.getText().trim());
    }

    public Object getProduto() {
        return cbProduto.getSelectedItem();
    }

    public Date getData() {
        return datePicker.getDate();

    }

    public Object getCliente() {
        return cbCliente.getSelectedItem();
    }

    public JTable getTbProdVenda() {
        return tbProdVenda;
    }


    public void limparTxt() {
        txtQty.setText(null);
        cbProduto.setSelectedIndex(0);
    }
    public  void limparCalc(){
        setLbTotalNr(0.00);
        setLbTotalLiq(0.00);
        setLbIvaNr(0.00);
    }

    public void limparTb() {
        tbProdVenda.setModel(new DefaultTableModel(new String[]{"Produto", "Quantidade", "P/unidade", "Preço"}, 0));
    }

    public void addProduto(String produto) {
        cbProduto.addItem(produto);
    }

    public void addCliente(String cliente) {
        cbCliente.addItem(cliente);
    }

    public void setLbTotalNr(double nr) {
        lbTotalNr.setText(String.format("%.2f", nr));
    }

    public void setLbTotalLiq(double nr) {
        lbTLiqNr.setText(String.format("%.2f", nr));
    }

    public void setLbIvaNr(double nr) {

        lbIvaNr.setText(String.format("%.2f", nr));
    }

    public void message(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void warningMassage(String message){
        JOptionPane.showMessageDialog(this, message, "Ateção", JOptionPane.WARNING_MESSAGE);
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
