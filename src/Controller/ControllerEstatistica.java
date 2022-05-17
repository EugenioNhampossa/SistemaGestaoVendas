package Controller;

import Model.Armazem;
import View.TelaEstatistica;
import Model.Produto;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

public class ControllerEstatistica {
    private final TelaEstatistica view;
    private final Vector<Object> produtos;
    private final Vector<Object> armazens;
    private final Vector<Object> maisVendidos;
    private final Vector<Object> vectorProdutos;

    public ControllerEstatistica(TelaEstatistica view, Vector<Object> produtos, Vector<Object> armazens) {
        this.view = view;
        this.produtos = produtos;
        this.armazens = armazens;
        maisVendidos = new Vector<Object>();
        vectorProdutos = (Vector<Object>) produtos.clone();
        determinarMaisVendidos();
        preencherTbMVendidos();
        criarGraficoQty();
        criarGraficArmazem();
        preencherTbPEncomenda();
        preencherTbValidade();
    }


    public void determinarMaisVendidos() {
        Object mv;
        for (int a = 0; a < 5; a++) {
            mv = determinarMaisVendido();
            if (mv != null) {
                maisVendidos.add(mv);
            }
        }
    }

    private Object determinarMaisVendido() {
        Object max = null;
        if (!vectorProdutos.isEmpty()) {
            max = vectorProdutos.get(0);
        }
        for (int i = 1; i < vectorProdutos.size(); i++) {
            if (((Produto) vectorProdutos.get(i)).getQtyVendida() > ((Produto) max).getQtyVendida()) {
                max = vectorProdutos.get(i);
            }
        }
        vectorProdutos.remove(max);

        return max;
    }

    public void preencherTbMVendidos() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Armazém", "Stock mínimo", "Unidades Vendidas"));

        for (Object produto : maisVendidos) {
            if (((Produto) produto).getQtyVendida() > 0) {
                Vector<String> v = new Vector<>();
                v.add(new DecimalFormat("0000").format(((Produto) produto).getId()));
                v.add(((Produto) produto).getNomeProd());
                v.add(nomeArmazem(((Produto) produto).getCodArmazem()));
                v.add(String.valueOf(((Produto) produto).getStockMinimo()));
                v.add(String.valueOf(((Produto) produto).getQtyVendida()));
                linhas.add(v);
            }

        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbMaisVendidos().setModel(model);
    }

    public void preencherTbPEncomenda() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Armazém", "Stock mínimo", "Qty em Stock"));

        for (Object produto : produtos) {
            Vector<String> v = new Vector<>();
            if (((Produto) produto).verificarPontoEncomenda()) {
                v.add(String.valueOf(((Produto) produto).getId()));
                v.add(((Produto) produto).getNomeProd());
                v.add(nomeArmazem(((Produto) produto).getCodArmazem()));
                v.add(String.valueOf(((Produto) produto).getStockMinimo()));
                v.add(String.valueOf(((Produto) produto).getQtyStock()));
                linhas.add(v);
            }
        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbPEncomenda().setModel(model);
    }

    public void preencherTbValidade() {
        Vector<Vector<String>> linhas = new Vector<>();

        Vector<String> colunas = new Vector<>(Arrays.asList("Código", "Nome", "Qty", "Prazo de validade", "Condição"));

        for (Object produto : produtos) {
            Vector<String> v = new Vector<>();
            if (((Produto) produto).getQtyStock() > 0) {
                if (((Produto) produto).verificarValidade()) {
                    v.add(String.valueOf(((Produto) produto).getId()));
                    v.add(((Produto) produto).getNomeProd());
                    v.add(String.valueOf(((Produto) produto).getQtyStock()));
                    v.add(new SimpleDateFormat("MMMM, dd, yyyy").format(((Produto) produto).getDataValidade()));
                    v.add(((Produto) produto).verificarCondicao());
                    linhas.add(v);
                }
            }
        }
        DefaultTableModel model = new DefaultTableModel(linhas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTbValidade().setModel(model);
    }


    private void criarGraficoQty() {
        DefaultCategoryDataset barra = new DefaultCategoryDataset();
        for (Object produto : maisVendidos) {
            barra.setValue(((Produto) produto).getQtyStock(), ((Produto) produto).getNomeProd(), ((Produto) produto).getNomeProd());
        }
        JFreeChart grafico = ChartFactory.createStackedBarChart("Quantidade em stock dos mais vendidos", "Produto", "Quantidade", barra, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel cp = new ChartPanel(grafico);
        grafico.setBackgroundPaint(Color.WHITE);
        view.addGraficoQty(cp);

    }


    private void criarGraficArmazem() {
        DefaultCategoryDataset barra = new DefaultCategoryDataset();
        for (Object obj : armazens) {
            barra.setValue(((Armazem) obj).getNrProdutos(), ((Armazem) obj).getNome(), ((Armazem) obj).getNome());
        }
        JFreeChart grafico = ChartFactory.createStackedBarChart("Número de Produtos por Armazém", "Produto", "Quantidade", barra, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel cp = new ChartPanel(grafico);
        view.addGraficoArm(cp);
    }

    private String nomeArmazem(int codigo) {
        for (Object a : armazens) {
            if (((Armazem) a).getId() == codigo) {
                return ((Armazem) a).getNome();
            }
        }
        return null;
    }
}
