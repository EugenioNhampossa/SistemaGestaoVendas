package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class TelaPrincipal extends JFrame implements MouseListener {
    private final JLabel lbTitulo;
    private final JLabel lbBack;
    private final JLabel lbUser;
    private final JLabel lbHora;
    private final JLabel lbImgTitulo;
    private final JLabel lbImgUser;
    private final JPanel pnRodape;
    private final JPanel pnCentral;
    private final JPanel pnLateral;
    private final JPanel pnLateralCentral;
    private final JPanel pnCentral3;
    private final JPanel pnCentral2;
    private final JPanel pnTitulo;
    private final JPanel pnUsuario;
    private final JButton btNovaVenda;
    private final JButton btUsers;
    private final JButton btFornecedores;
    private final JButton btEstatistica;
    private final JButton btProdutos;
    private final JButton btVendas;
    private final JButton btFornecimento;
    private final JButton btArmazens;
    private final JButton btClientes;
    private final JProgressBar pg;
    private final JMenuBar pnCabecalho;

    public TelaPrincipal(String nomeUsuario,String nvAcesso) {
        this.setTitle("Venda Fácil");
        this.setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/shop.png"))).getImage());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        criarFonte();
        pnCabecalho = new JMenuBar();
        pnCabecalho.setLayout(new GridLayout(1, 2));
        pnCabecalho.setBackground(new Color(136, 224, 200));
        pnCabecalho.setBorderPainted(false);
        this.setJMenuBar(pnCabecalho);
        pnRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnRodape.setBackground(new Color(136, 224, 200));
        pnUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnUsuario.setBackground(new Color(136, 224, 200));
        pnTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTitulo.setBackground(new Color(136, 224, 200));
        pnCentral = new JPanel(new BorderLayout());
        pnCentral.setBackground(Color.WHITE);
        pnLateral = new JPanel(new BorderLayout(5, 5));
        pnLateral.setBackground(Color.WHITE);
        pnLateralCentral = new JPanel();
        pnLateralCentral.setLayout(new GridLayout(8,1,0,10));
        pnLateralCentral.setBackground(Color.WHITE);
        pnCentral2 = new JPanel(new BorderLayout());
        pnCentral2.setBackground(Color.WHITE);
        pnCentral3 = new JPanel(new GridLayout(1, 1));
        pnCentral3.setBackground(Color.WHITE);
        pg = new JProgressBar(SwingConstants.HORIZONTAL);
        pg.setBorderPainted(false);
        pg.setBackground(Color.WHITE);
        pg.setForeground(new Color(255, 147, 147));
        pg.setVisible(false);
        pg.setMaximum(100);

        lbImgTitulo = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/shop.png"))));
        lbTitulo = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/imgLogo.png"))));
        lbBack = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/background.png"))));
        lbImgUser = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/user.png"))));
        lbUser = new JLabel(nomeUsuario);
        lbHora = new JLabel();
        lbHora.setFont(new Font(lbHora.getName(), Font.BOLD, 13));

        btNovaVenda = new JButton();
        aplicarEstiloBotao(btNovaVenda, "Nova Venda", "add-to-cart.png");
        btProdutos = new JButton();
        aplicarEstiloBotao(btProdutos, "Produtos", "product.png");
        btClientes = new JButton();
        aplicarEstiloBotao(btClientes, "Clientes", "people.png");
        btArmazens = new JButton();
        aplicarEstiloBotao(btArmazens, "Armazens", "warehouse.png");
        btFornecimento = new JButton();
        aplicarEstiloBotao(btFornecimento, "Fornecimentos", "supplier.png");
        btVendas = new JButton();
        aplicarEstiloBotao(btVendas, "Rel.Vendas", "sale-report.png");
        btFornecedores = new JButton();
        aplicarEstiloBotao(btFornecedores, "Fornecedores", "delivery.png");
        btEstatistica = new JButton();
        aplicarEstiloBotao(btEstatistica, "Estatísticas", "statistic.png");
        btUsers = new JButton();
        aplicarEstiloBotao(btUsers, "Usuários", "users.png");

        Timer timer = new Timer(1000, new ClockListener());
        timer.start();

        pnLateralCentral.add(btNovaVenda);
        pnLateralCentral.add(btProdutos);
        pnLateralCentral.add(btFornecimento);
        pnLateralCentral.add(btFornecedores);
        pnLateralCentral.add(btVendas);
        pnLateralCentral.add(btEstatistica);
        pnLateralCentral.add(btArmazens);
        pnLateralCentral.add(btClientes);
        if(nvAcesso.equals("Administrador")){
            pnLateralCentral.setLayout(new GridLayout(9,1,0,10));
            pnLateralCentral.add(btUsers);
        }

       // pnLateral.add(new JLabel(), BorderLayout.NORTH);
        pnLateral.add(pnLateralCentral, BorderLayout.CENTER);
        pnLateral.add(new JLabel(), BorderLayout.SOUTH);
        pnLateral.add(new JLabel(), BorderLayout.EAST);
        pnLateral.add(new JLabel(), BorderLayout.WEST);

        pnTitulo.add(lbImgTitulo);
        pnTitulo.add(lbTitulo);

        pnUsuario.add(lbImgUser);
        pnUsuario.add(lbUser);

        pnCabecalho.add(pnTitulo);
        pnCabecalho.add(pnUsuario);
        pnRodape.add(pg);
        pnRodape.add(lbHora);

        pnCentral3.add(lbBack);
        pnCentral2.add(pnCentral3, BorderLayout.CENTER);
        pnCentral2.add(pnRodape, BorderLayout.SOUTH);

        pnCentral.add(pnLateral, BorderLayout.WEST);
        pnCentral.add(pnCentral2, BorderLayout.CENTER);


        this.add(pnCentral, BorderLayout.CENTER);

        btFornecimento.addMouseListener(this);
        btProdutos.addMouseListener(this);
        btArmazens.addMouseListener(this);
        btClientes.addMouseListener(this);
        btNovaVenda.addMouseListener(this);
        btFornecedores.addMouseListener(this);
        btEstatistica.addMouseListener(this);
        btVendas.addMouseListener(this);
        btUsers.addMouseListener(this);
        lbImgTitulo.addMouseListener(this);

    }

    public void aplicarEstiloBotao(JButton bt, String legenda, String imagem) {
        bt.setText(legenda);
        bt.setIcon(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/" + imagem))));
        bt.setBackground(Color.WHITE);
        bt.setBorderPainted(false);
        bt.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void criarFonte() {

        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("View/Fonts/RobotoCondensed-Light.ttf"))).deriveFont(Font.BOLD, 13);
            UIManager.put("Label.font", new Font(font.getFontName(), Font.PLAIN, 18));
            UIManager.put("Table.font", new Font(font.getFontName(), Font.PLAIN, 15));
            UIManager.put("ComboBox.font", new Font(font.getFontName(), Font.PLAIN, 15));
            UIManager.put("TextField.font", new Font(font.getFontName(), Font.PLAIN, 15));
            UIManager.put("TextArea.font", new Font(font.getFontName(), Font.PLAIN, 15));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lbImgTitulo) {
            pnCentral3.removeAll();
            pnCentral3.add(lbBack);
            pnCentral3.updateUI();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setBackground(new Color(136, 224, 200));
            mudarCor(((JButton) e.getSource()));
        }

    }

    public void mudarCor(JButton bt) {
        if (bt != btEstatistica)
            btEstatistica.setBackground(Color.WHITE);
        if (bt != btFornecimento)
            btFornecimento.setBackground(Color.WHITE);
        if (bt != btNovaVenda)
            btNovaVenda.setBackground(Color.WHITE);
        if (bt != btProdutos)
            btProdutos.setBackground(Color.WHITE);
        if (bt != btFornecedores)
            btFornecedores.setBackground(Color.WHITE);
        if (bt != btVendas)
            btVendas.setBackground(Color.WHITE);
        if (bt != btArmazens)
            btArmazens.setBackground(Color.WHITE);
        if (bt != btClientes)
            btClientes.setBackground(Color.WHITE);
        if (bt != btUsers)
            btUsers.setBackground(Color.WHITE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setBackground(new Color(0, 183, 164));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            Color c = new Color(0, 183, 164);
            if (((JButton) e.getSource()).getBackground().equals(c)) {
                ((JButton) e.getSource()).setBackground(Color.WHITE);
            }
        }
    }

    public void addActionBtProdutos(ActionListener action) {
        btProdutos.addActionListener(action);
    }

    public void addActionBtArmazens(ActionListener action) {
        btArmazens.addActionListener(action);
    }

    public void addActionBtVendas(ActionListener action) {
        btVendas.addActionListener(action);
    }

    public void addActionBtNovaVenda(ActionListener action) {
        btNovaVenda.addActionListener(action);
    }

    public void addActionBtFornecedor(ActionListener action) {
        btFornecedores.addActionListener(action);
    }

    public void addActionBtEstatisticas(ActionListener action) {
        btEstatistica.addActionListener(action);
    }

    public void addActionBtClientes(ActionListener action) {
        btClientes.addActionListener(action);
    }

    public void addActionBtFornecimento(ActionListener action) {
        btFornecimento.addActionListener(action);
    }

    public void addActionBtUsers(ActionListener action) {
        btUsers.addActionListener(action);
    }

    public JProgressBar getPg() {
        return pg;
    }


    public void addTela(JPanel pn) {
        pnCentral3.removeAll();
        pnCentral3.add(pn);
        pnCentral3.updateUI();
    }

    private class ClockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date date = Calendar.getInstance().getTime();
            Calendar c = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy | hh:mm:ss");
            String strDate = dateFormat.format(date);
            lbHora.setText("Data: " + diaSemana(c.get(Calendar.DAY_OF_WEEK)) + " | " + strDate);
        }
    }

    public String diaSemana(int n) {

        switch (n) {
            case 1:
                return "Domingo";
            case 2:
                return "Segunda-Feira";
            case 3:
                return "Terça-Feira";
            case 4:
                return "Quarta-Feira";
            case 5:
                return "Quinta-Feira";
            case 6:
                return "Sexta-Feira";
            case 7:
                return "Sábado";
            default:
                return null;
        }

    }
}
