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
import javax.swing.JTable;
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

import Model.Fornecedor;
import Model.Fornecimento;
import Model.ItemFornecido;
import Model.Produto;
import View.TelaFornecimento;

public class ControllerFornecimento {
    private final TelaFornecimento view;
    private final Vector<Object> fornecimentos;
    private final Vector<Object> produtos;
    private final Vector<Object> fornecedores;

    public ControllerFornecimento(TelaFornecimento view, Vector<Object> fornecimentos, Vector<Object> produtos,
            Vector<Object> fornecedores) {
        this.view = view;
        this.fornecimentos = fornecimentos;
        this.fornecedores = fornecedores;
        this.produtos = produtos;

        view.addAccaoBtAdicionar(new AccaoBtAdicionar());
        view.addAccaoBtRemover(new AccaoBtRemover());
        view.addAccaoBtCancelar(new AccaoBtCancelar());
        view.addAccaoBtConfirmar(new AccaoBtConfirmar());
        view.addAccaoTxtPesquisar(new AccaoTxtPesquisar());
        view.addAccaoBtExportar(new AccaoExportar());
        preencherCb();
        preencherTabela();
    }

    private class AccaoBtAdicionar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (pesquisarNome(String.valueOf(view.getProduto()), produtos) != 0) {
                    String[] linha = new String[] { String.valueOf(view.getProduto()), String.valueOf(view.getQty()) };
                    adicionarLinha(view.getTbProdForn(), linha);
                    view.limparTxt();
                } else {
                    view.errorMessage("Seleccione o produto");
                }
            } catch (NumberFormatException ex) {
                view.errorMessage("A quantidade deve ser um número\n" + ex.getMessage());
            }
        }
    }

    private class AccaoBtRemover implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTbProdForn().getRowCount() > 0) {
                remLinha(view.getTbProdForn(), view.getTbProdForn().getSelectedRow());
            } else {
                view.errorMessage("Nenhuma linha por remover");
            }
        }
    }

    private class AccaoBtCancelar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.limparTb();
        }
    }

    private class AccaoBtConfirmar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            DefaultTableModel dtm = (DefaultTableModel) view.getTbProdForn().getModel();
            int idProd = 0;
            double qty = 0;
            int idForn = pesquisarNome(String.valueOf(view.getFornecedor()), fornecedores);
            if (view.getTbProdForn().getRowCount() != 0) {
                if (idForn != 0) {
                    Vector<ItemFornecido> itens = new Vector<>();
                    for (int i = 0; i < view.getTbProdForn().getRowCount(); i++) {
                        idProd = pesquisarNome((String) dtm.getValueAt(i, 0), produtos);
                        qty = Double.parseDouble((String) dtm.getValueAt(i, 1));
                        actualizarQty(qty, idProd);
                        itens.add(new ItemFornecido(idProd, qty));
                    }

                    Fornecimento fornecimento = new Fornecimento(geradorId(), idForn, view.getData(), itens);
                    if (fornecimento.getDao().escrever(fornecimento) && fornecimentos.add(fornecimento)) {
                        view.message("Fornecimento Registrado");
                        view.message("Invenário actualizado com sucesoo");
                    }
                    view.limparTxt();
                    view.limparTb();
                    preencherTabela();
                } else {
                    view.errorMessage("Seleccione o fornecedor");
                }
            } else {
                view.errorMessage("Sem dados por registrar");
            }
        }
    }

    public void actualizarQty(double qty, int idProd) {
        for (Object obj : produtos) {
            if (((Produto) obj).getId() == idProd) {
                ((Produto) obj).somarQtyStock(qty);
                ((Produto) obj).getDao().actualizar(produtos);
            }
        }
    }

    private class AccaoTxtPesquisar implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(view.getTbFornecimento().getModel());
            view.getTbFornecimento().setRowSorter(rowSorter);
            rowSorter.setRowFilter(RowFilter.regexFilter(view.getPesquisa()));
        }
    }

    public void remLinha(JTable tabela, int linha) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        if (linha != -1) {
            model.removeRow(linha);
        } else {
            view.errorMessage("Seleccione uma linha");
        }
        tabela = new JTable(model);
    }

    public void adicionarLinha(JTable tabela, String[] linha) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.addRow(linha);
        tabela = new JTable(model);
    }

    public String pesquisarId(int id, Vector<Object> vector) {
        for (Object obj : vector) {
            if (obj instanceof Produto) {
                if (((Produto) obj).getId() == id) {
                    return ((Produto) obj).getNomeProd();
                }
            }
            if (obj instanceof Fornecedor) {
                if (((Fornecedor) obj).getId() == id) {
                    return ((Fornecedor) obj).getNome();
                }
            }
        }
        return null;
    }

    public int pesquisarNome(String nome, Vector<Object> vector) {
        for (Object obj : vector) {
            if (obj instanceof Produto) {
                if (((Produto) obj).getNomeProd().equals(nome)) {
                    return ((Produto) obj).getId();
                }
            }
            if (obj instanceof Fornecedor) {
                if (((Fornecedor) obj).getNome().equals(nome)) {
                    return ((Fornecedor) obj).getId();
                }
            }
        }
        return 0;
    }

    public void preencherCb() {
        for (Object obj : produtos) {
            view.addProduto(((Produto) obj).getNomeProd());
        }
        for (Object obj : fornecedores) {
            view.addFornecedor(((Fornecedor) obj).getNome());
        }
    }

    public void preencherTabela() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(
                Arrays.asList("Código", "Fornecedor", "Produto", "Qty Fornecida", "Data"));

        for (Object forn : fornecimentos) {
            for (ItemFornecido item : ((Fornecimento) forn).getItensFornecidos()) {
                Vector<String> v = new Vector<>();
                v.add(new DecimalFormat("0000").format(((Fornecimento) forn).getId()));
                v.add(pesquisarId(((Fornecimento) forn).getIdForn(), fornecedores));
                v.add(pesquisarId(item.getIdProd(), produtos));
                v.add(String.valueOf(item.getQtyForn()));
                v.add(new SimpleDateFormat("MMMM, dd, yyyy").format(((Fornecimento) forn).getData()));
                linhas.add(v);
            }
        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbFornecimento().setModel(model);
    }

    private int geradorId() {
        if (fornecimentos.isEmpty()) {
            return 1;
        } else {
            return ((Fornecimento) fornecimentos.lastElement()).getId() + 1;
        }
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
            Paragraph p = new Paragraph("Relatório de Fornecimentos", fonte);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            Paragraph p2 = new Paragraph("   ");
            doc.add(p2);

            PdfPTable tabela = new PdfPTable(5);

            PdfPCell codigo = new PdfPCell(new Paragraph("Código", fonte2));
            PdfPCell fornecedor = new PdfPCell(new Paragraph("Fornecedor", fonte2));
            PdfPCell produto = new PdfPCell(new Paragraph("Produto", fonte2));
            PdfPCell qtyFornecida = new PdfPCell(new Paragraph("Qty Fornecida", fonte2));
            PdfPCell data = new PdfPCell(new Paragraph("Data do Fornecimento", fonte2));

            tabela.addCell(codigo);
            tabela.addCell(fornecedor);
            tabela.addCell(produto);
            tabela.addCell(qtyFornecida);
            tabela.addCell(data);

            for (int i = 0; i < view.getTbFornecimento().getRowCount(); i++) {
                tabela.addCell((String) view.getTbFornecimento().getValueAt(i, 0));
                tabela.addCell((String) view.getTbFornecimento().getValueAt(i, 1));
                tabela.addCell((String) view.getTbFornecimento().getValueAt(i, 2));
                tabela.addCell((String) view.getTbFornecimento().getValueAt(i, 3));
                tabela.addCell((String) view.getTbFornecimento().getValueAt(i, 4));
            }

            doc.add(tabela);
            doc.close();
            view.message("Ficheiro criado em com sucesso!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

}
