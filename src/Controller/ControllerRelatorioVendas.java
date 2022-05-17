package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.Cliente;
import Model.ItemVenda;
import Model.Produto;
import Model.Venda;
import View.TelaRelatorioVendas;

public class ControllerRelatorioVendas {
    private final TelaRelatorioVendas view;
    private final Vector<Object> vendas;
    private final Vector<Object> clientes;
    private final Vector<Object> produtos;

    public ControllerRelatorioVendas(TelaRelatorioVendas view, Vector<Object> vendas, Vector<Object> clientes,
            Vector<Object> produtos) {
        this.view = view;
        this.vendas = vendas;
        this.clientes = clientes;
        this.produtos = produtos;
        view.addAccaoTxtPesquisar(new AccaoPesquisar());
        view.addAccaoBtExportar(new AccaoExportar());
        preencherTabela();
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(
                Arrays.asList("Código", "Cliente", "P.Vendido", "Qty Vendida", "Preço", "P.Líquido", "Data da Venda"));

        for (Object venda : vendas) {
            for (ItemVenda item : ((Venda) venda).getItensVenda()) {
                Vector<String> v = new Vector<>();
                double preco = pesquisar(item.getIdProd()).getPrecoUnit() * item.getQtyVendida();
                v.add(new DecimalFormat("0000").format(((Venda) venda).getId()));
                v.add(pesquisarId(((Venda) venda).getIdCli(), clientes));
                v.add(pesquisarId(item.getIdProd(), produtos));
                v.add(String.valueOf(item.getQtyVendida()));
                v.add(String.format("%.2f MT", preco));
                v.add(String.format("%.2f MT", preco + (preco * 0.17)));
                v.add(new SimpleDateFormat("MMMM, dd, yyyy").format(((Venda) venda).getDataVenda()));
                linhas.add(v);
            }

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbRelVendas().setModel(model);
    }

    private class AccaoExportar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                criarPDF();
            } catch (NullPointerException ex) {
                view.message("Nenhum ficheiro seleccionado");
            }
        }
    }

    public String escolherDirectoria() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int resultado = fileChooser.showOpenDialog(view);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File ficheiro = fileChooser.getSelectedFile();
            return ficheiro.getAbsolutePath();
        }
        return null;
    }

    public void criarPDF() throws NullPointerException {
        Document doc = new Document(new Rectangle(900, 900));

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(escolherDirectoria()));
            doc.open();
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(255, 129, 101));
            Font fonte2 = new Font(Font.FontFamily.UNDEFINED, 13, Font.BOLD);
            Paragraph p = new Paragraph("Relatório de Vendas", fonte);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            Paragraph p2 = new Paragraph("   ");
            doc.add(p2);

            PdfPTable tabela = new PdfPTable(7);

            PdfPCell codigo = new PdfPCell(new Paragraph("Código", fonte2));
            PdfPCell cliente = new PdfPCell(new Paragraph("Cliente", fonte2));
            PdfPCell pVendido = new PdfPCell(new Paragraph("P.Vendido", fonte2));
            PdfPCell qtyVendida = new PdfPCell(new Paragraph("Qty Vendida", fonte2));
            PdfPCell preco = new PdfPCell(new Paragraph("Preço", fonte2));
            PdfPCell pLiq = new PdfPCell(new Paragraph("Preço Líquido", fonte2));
            PdfPCell data = new PdfPCell(new Paragraph("Data da Venda", fonte2));

            tabela.addCell(codigo);
            tabela.addCell(cliente);
            tabela.addCell(pVendido);
            tabela.addCell(qtyVendida);
            tabela.addCell(preco);
            tabela.addCell(pLiq);
            tabela.addCell(data);

            for (int i = 0; i < view.getTbRelVendas().getRowCount(); i++) {
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 0));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 1));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 2));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 3));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 4));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 5));
                tabela.addCell((String) view.getTbRelVendas().getValueAt(i, 6));
            }

            doc.add(tabela);
            doc.close();
            view.message("Ficheiro criado em com sucesso!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

    public Produto pesquisar(int id) {
        for (Object obj : produtos) {
            if (((Produto) obj).getId() == id) {
                return (Produto) obj;
            }
        }
        return null;
    }

    private String pesquisarId(int id, Vector<Object> vector) {
        for (Object obj : vector) {
            if (obj instanceof Produto) {
                if (((Produto) obj).getId() == id) {
                    return ((Produto) obj).getNomeProd();
                }
            }
            if (obj instanceof Cliente) {
                if (((Cliente) obj).getId() == id) {
                    return ((Cliente) obj).getNome();
                }
            }
        }
        return null;
    }

    private class AccaoPesquisar implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbRelVendas().getModel());
            view.getTbRelVendas().setRowSorter(rowSorter);
            rowSorter.setRowFilter(RowFilter.regexFilter(view.getPesquisa()));
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
    }

}
