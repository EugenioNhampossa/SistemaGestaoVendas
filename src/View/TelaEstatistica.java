package View;

import org.jfree.chart.ChartPanel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaEstatistica extends JPanel implements MouseListener {
    private final JLabel lbTMVendidos;
    private final JLabel lbTPEncomenda;
    private final JLabel lbValidade;
    private final JPanel pnPrincipal;
    private final JPanel pnTbPEncomenda;
    private final JPanel pnTbValidade;
    private final JPanel pnCbValidade;
    private final JPanel pnCentralValidade;
    private final JPanel pnCbPEncomenda;
    private final JPanel pnGraficoArm;
    private final JPanel pnGraficoQty;
    private final JPanel pnCentralCons;
    private final JPanel pnCentralPE;
    private final JPanel pnTbMVendidos;
    private final JPanel pnCbMVendidos;
    private final JScrollPane spEstatisticas;
    private final JScrollPane spMVendidos;
    private final JScrollPane spValidade;
    private final JScrollPane spPEncomenda;
    private final JTable tbMaisVendidos;
    private final JTable tbPEncomenda;
    private final JTable tbValidade;

    public TelaEstatistica() {
        this.setLayout(new GridLayout(1, 1));

        pnPrincipal = new JPanel();
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));
        spEstatisticas = new JScrollPane(pnPrincipal);
        spEstatisticas.setBorder(BorderFactory.createEmptyBorder());
        pnTbMVendidos = new JPanel(new BorderLayout(5, 5));
        pnTbPEncomenda = new JPanel(new BorderLayout(5, 5));
        pnTbValidade = new JPanel(new BorderLayout(5, 5));
        pnCbMVendidos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbMVendidos.setBackground(new Color(255, 147, 147));
        pnCbPEncomenda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbPEncomenda.setBackground(new Color(255, 147, 147));
        pnCbValidade = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCbValidade.setBackground(new Color(255, 147, 147));
        pnCentralCons = new JPanel();
        pnCentralCons.setLayout(new BoxLayout(pnCentralCons, BoxLayout.Y_AXIS));
        pnCentralPE = new JPanel(new GridLayout(1, 1));
        pnCentralValidade = new JPanel(new GridLayout(1, 1));
        pnGraficoQty = new JPanel(new GridLayout(1, 1));
        pnGraficoArm = new JPanel(new GridLayout(1, 1));

        lbTMVendidos = new JLabel("Mais Vendidos");
        lbTMVendidos.setForeground(Color.WHITE);
        lbTPEncomenda = new JLabel("Produtos no ponto de encomenda");
        lbTPEncomenda.setForeground(Color.WHITE);
        lbValidade = new JLabel("Validade dos produtos");
        lbValidade.setForeground(Color.WHITE);

        tbMaisVendidos = new JTable(new DefaultTableModel());
        tbMaisVendidos.setRowHeight(25);
        estiloTabela(tbMaisVendidos);
        tbPEncomenda = new JTable(new DefaultTableModel());
        tbPEncomenda.setRowHeight(25);
        estiloTabela(tbPEncomenda);
        tbValidade = new JTable(new DefaultTableModel());
        tbValidade.setRowHeight(25);
        estiloTabela(tbValidade);

        spMVendidos = new JScrollPane(tbMaisVendidos);
        spMVendidos.setBorder(BorderFactory.createEmptyBorder());
        spMVendidos.setPreferredSize(new Dimension(0, 150));
        spPEncomenda = new JScrollPane(tbPEncomenda);
        spPEncomenda.setBorder(BorderFactory.createEmptyBorder());
        spPEncomenda.setPreferredSize(new Dimension(0, 300));
        spValidade = new JScrollPane(tbValidade);
        spValidade.setBorder(BorderFactory.createEmptyBorder());
        spValidade.setPreferredSize(new Dimension(0, 300));

        pnCbMVendidos.add(lbTMVendidos);
        pnCbPEncomenda.add(lbTPEncomenda);
        pnCbValidade.add(lbValidade);

        pnCentralCons.add(spMVendidos);
        pnCentralCons.add(pnGraficoQty);
        pnCentralCons.add(pnGraficoArm);
        pnTbMVendidos.add(new JLabel(" "), BorderLayout.SOUTH);
        pnTbMVendidos.add(pnCentralCons, BorderLayout.CENTER);
        pnTbMVendidos.add(new JLabel(" "), BorderLayout.EAST);
        pnTbMVendidos.add(new JLabel(" "), BorderLayout.WEST);

        pnCentralPE.add(spPEncomenda);
        pnTbPEncomenda.add(new JLabel(" "), BorderLayout.SOUTH);
        pnTbPEncomenda.add(pnCentralPE, BorderLayout.CENTER);
        pnTbPEncomenda.add(new JLabel(" "), BorderLayout.EAST);
        pnTbPEncomenda.add(new JLabel(" "), BorderLayout.WEST);

        pnCentralValidade.add(spValidade);
        pnTbValidade.add(new JLabel(" "), BorderLayout.SOUTH);
        pnTbValidade.add(pnCentralValidade, BorderLayout.CENTER);
        pnTbValidade.add(new JLabel(" "), BorderLayout.EAST);
        pnTbValidade.add(new JLabel(" "), BorderLayout.WEST);

        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnCbMVendidos);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnTbMVendidos);
        pnPrincipal.add(pnCbPEncomenda);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnTbPEncomenda);
        pnPrincipal.add(pnCbValidade);
        pnPrincipal.add(new JLabel(" "));
        pnPrincipal.add(pnTbValidade);

        this.add(spEstatisticas);
    }

    private void estiloTabela(JTable tb) {
        tb.setShowGrid(false);
        tb.setSelectionBackground(new Color(136, 224, 200));
        tb.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 183, 164)));
        tb.getTableHeader().setBackground(new Color(0, 183, 164));
        tb.setShowHorizontalLines(true);
        tb.setAutoCreateRowSorter(true);
    }

    public void addGraficoQty(ChartPanel cp) {
        pnGraficoQty.removeAll();
        pnGraficoQty.add(cp);
    }

    public void addGraficoArm(ChartPanel cp) {
        pnGraficoArm.removeAll();
        pnGraficoArm.add(cp);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public JTable getTbMaisVendidos() {
        return tbMaisVendidos;
    }

    public JTable getTbPEncomenda() {
        return tbPEncomenda;
    }

    public JTable getTbValidade() {
        return tbValidade;
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
