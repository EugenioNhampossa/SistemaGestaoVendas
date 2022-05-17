package View;

import Controller.ControllerLogin;
import Model.User;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

public class TelaLogin extends JFrame {
    private final JLabel lbNome;
    private final JLabel lbSenha;
    private final JLabel lbImagem;
    private final JLabel lbTitulo;
    private final JLabel lbImg;
    private final JTextField txtNome;
    private final JPasswordField txtSenha;
    private final JButton btEntrar;
    private final JButton btCancelar;
    private final JPanel pnCentral;
    private final JPanel pnInsercao;
    private final JPanel pnBt;
    private final JPanel pnImagem;
    private final JPanel pnCabecalho;
    private final JPanel pnNome;
    private final JPanel pnSenha;

    public TelaLogin() {
        super("Login");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(TelaLogin.class.getResource("/View/Icons/shop.png"))).getImage());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(2, 10));

        criarFonte();

        pnInsercao = new JPanel(new GridLayout(3, 2, 0, 7));
        pnInsercao.setBackground(Color.WHITE);
        pnCentral = new JPanel(new BorderLayout(5, 10));
        pnCentral.setBackground(Color.WHITE);
        pnImagem = new JPanel(new GridLayout(1, 1));

        pnImagem.setBackground(Color.WHITE);
        pnBt = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        pnBt.setBackground(Color.WHITE);
        pnCabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnCabecalho.setBackground(new Color(136, 224, 200));
        pnNome = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnNome.setBackground(Color.WHITE);
        pnSenha = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnSenha.setBackground(Color.WHITE);

        lbImagem = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/login.png"))));
        lbNome = new JLabel("Nome:");
        lbNome.setHorizontalAlignment(SwingConstants.CENTER);
        lbSenha = new JLabel("Senha:");
        lbSenha.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/src/View/Icons/shop.png"));
        lbTitulo = new JLabel(new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/imgLogo2.png"))));

        txtNome = new JTextField(17);
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));
        txtSenha = new JPasswordField(17);
        txtSenha.setBorder(BorderFactory.createLineBorder(new Color(25, 103, 95)));

        btEntrar = new JButton("Entrar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/logIn.png"))));
        btEntrar.setHorizontalTextPosition(SwingConstants.LEFT);
        btEntrar.setForeground(Color.WHITE);
        btEntrar.setBackground(new Color(74, 211, 149));
        btCancelar = new JButton("Cancelar",new ImageIcon(Objects.requireNonNull(TelaPrincipal.class.getResource("/View/Icons/cancel.png"))));
        btCancelar.setHorizontalTextPosition(SwingConstants.LEFT);
        btCancelar.setForeground(Color.WHITE);
        btCancelar.setBackground(new Color(224, 79, 95));

        pnBt.add(btCancelar);
        pnBt.add(btEntrar);

        pnNome.add(lbNome);
        pnNome.add(txtNome);
        pnSenha.add(lbSenha);
        pnSenha.add(txtSenha);
        pnInsercao.add(pnNome);
        pnInsercao.add(pnSenha);

        pnCabecalho.add(lbImg);
        pnCabecalho.add(lbTitulo);

        pnImagem.add(lbImagem);
        pnCentral.add(pnImagem, BorderLayout.NORTH);
        pnCentral.add(new JLabel(), BorderLayout.EAST);
        pnCentral.add(pnInsercao, BorderLayout.CENTER);
        pnCentral.add(pnBt, BorderLayout.SOUTH);
        pnCentral.add(new JLabel(), BorderLayout.WEST);

        this.add(pnCabecalho, BorderLayout.NORTH);
        this.add(pnCentral, BorderLayout.CENTER);

        this.setVisible(true);

    }

    public void criarFonte() {

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("View/Fonts/RobotoCondensed-Light.ttf"))).deriveFont(Font.BOLD, 13);
            UIManager.put("Label.font", new Font(font.getFontName(), Font.PLAIN, 18));
            UIManager.put("TextField.font", new Font(font.getFontName(), Font.PLAIN, 15));
            UIManager.put("PasswordField.font", new Font(font.getFontName(), Font.PLAIN, 15));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getSenha() {
        return txtSenha.getText().trim();
    }

    public String getNome() {
        return txtNome.getText().trim();
    }

    public void addAccaoBtEntrar(ActionListener action){
        btEntrar.addActionListener(action);
    }

    public void addAccaoBtCancelar(ActionListener action){
        btCancelar.addActionListener(action);
    }
    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmationMessage(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirmação", JOptionPane.YES_NO_OPTION);
    }

    public static void main(String[] args) {
        TelaLogin tl = new TelaLogin();
        User user = new User();
        Vector<Object> users = user.getDao().ler();
        ControllerLogin uc = new ControllerLogin(tl, users);

    }
}
