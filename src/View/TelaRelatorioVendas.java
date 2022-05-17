package View;

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

public class TelaRelatorioVendas extends JPanel implements MouseListener {
    private final JLabel lbTituloConsulta;
    private final JLabel lbImgLupa;
    private final JTextArea txtPesquisar;
    private final JPanel pnPrincipal;
    private final JPanel pnExportar;
    private final JPanel pnPesquisa;
    private final JPanel pnCentralCons;
    private final JPanel pnConsultaSup;
    private final JPanel pnConsulta;
    private final JPanel pnCbConsulta;
    private final JScrollPane spRelVendas;
    private final JScrollPane spTabela;
    private final JTable tbRelVendas;
    private final JButton btExportar;

    public TelaRelatorioVendas() {
        this.setLayout(new GridLayout(1, 1));

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));
        spRelVendas = new JScrollPane(pnPrincipal);
        spRelVendas.setBorder(BorderFactory.createEmptyBorder());
        pnConsulta = new JPanel(new BorderLayout(5, 5));
        pnCbConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbConsulta.setBackground(new Color(255, 147, 147));
        pnConsultaSup = new JPanel(new GridLayout(1, 2));
        pnPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCentralCons = new JPanel(new GridLayout(1, 1));
        pnExportar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        lbTituloConsulta = new JLabel("Relatório de Vendas");
        lbTituloConsulta.setForeground(Color.WHITE);
        lbImgLupa =new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/search.png"))));

        tbRelVendas = new JTable(new DefaultTableModel());
        tbRelVendas.setRowHeight(25);
        estiloTabela(tbRelVendas);

        btExportar = new JButton("Exportar",new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/View/Icons/export.png"))));
        btExportar.setForeground(Color.WHITE);
        btExportar.setBackground(new Color(96, 125, 139));

        spTabela = new JScrollPane(tbRelVendas);
        spTabela.setBorder(BorderFactory.createEmptyBorder());

        txtPesquisar = new JTextArea(2, 15);
        txtPesquisar.setLineWrap(true);
        txtPesquisar.setSize(new Dimension(15, 20));
        txtPesquisar.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

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
        pnPrincipal.add(pnCbConsulta);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnConsulta);

        this.add(spRelVendas);
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

    public void addAccaoTxtPesquisar(KeyListener action) {
        txtPesquisar.addKeyListener(action);
    }

    public void addAccaoBtExportar(ActionListener action){
        btExportar.addActionListener(action);
    }

    public JTable getTbRelVendas() {
        return tbRelVendas;
    }

    public String getPesquisa() {
        return txtPesquisar.getText();
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
