package copercog.view;

import DAO.usuariosDAO;
import static DAO.usuariosDAO.getMD5;
import copercog.model.Copercog;
import copercog.model.usuarios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TeladeUsuarios extends JFrame {
    private JPanel painelPrincipal;
    private JTextField txtLogin, txtSenha;
    private JButton btnLogin;

    public TeladeUsuarios() {
        setUndecorated(true);
        setSize(400, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        Definir();
        setVisible(true);
    }

    private void Definir() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBounds(0, 0, 400, 360);
        this.add(painelPrincipal);

        JPanel barra = new JPanel(null);
        barra.setBackground(new Color(25, 25, 25));
        barra.setBounds(0, 0, 400, 30);

        JLabel tit = new JLabel("Login");
        tit.setForeground(Color.WHITE);
        tit.setBounds(10, 0, 100, 30);

        JButton minimizar = new JButton("🗕");
        minimizar.setBounds(325, 0, 50, 30);
        minimizar.setBackground(new Color(25, 25, 25));
        minimizar.setForeground(Color.WHITE);
        minimizar.setBorderPainted(false);
        minimizar.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton fechar = new JButton("X");
        fechar.setBounds(365, 0, 45, 30);
        fechar.setBackground(new Color(25, 25, 25));
        fechar.setForeground(Color.WHITE);
        fechar.setBorderPainted(false);
        fechar.addActionListener(e -> System.exit(0)); // fecha tudo se sair do login

        barra.add(tit);
        barra.add(minimizar);
        barra.add(fechar);
        painelPrincipal.add(barra);

        JLabel lUser = new JLabel("Usuário");
        lUser.setBounds(160, 50, 100, 30);

        txtLogin = new JTextField("admin_master");
        txtLogin.setBounds(50, 80, 300, 30);

        JLabel lPass = new JLabel("Senha");
        lPass.setBounds(160, 120, 100, 30);

        txtSenha = new JPasswordField("senha123"); // JPasswordField oculta a senha
        txtSenha.setBounds(50, 150, 300, 30);

        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(125, 200, 150, 35);
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        painelPrincipal.add(lUser);
        painelPrincipal.add(txtLogin);
        painelPrincipal.add(lPass);
        painelPrincipal.add(txtSenha);
        painelPrincipal.add(btnLogin);
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

        String login = txtLogin.getText().trim();
        String senhaPura = txtSenha.getText().trim();

        if (login.isEmpty() || senhaPura.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Usuário e senha vazios", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
String senhaCriptografada = getMD5(senhaPura);
        // monta o objeto e valida no banco
        usuarios usuarioTemp = new usuarios(login, senhaCriptografada);
        usuarios usuarioLogado = usuariosDAO.validacao(usuarioTemp);

    
        
        
        
        
        
        if (usuarioLogado == null) return; // mensagem já exibida no DAO

        String tipo = usuarioLogado.getTipo();

        switch (tipo) {
            case "Administrador":
                JOptionPane.showMessageDialog(null,
                    "Usuário master\nSuas ações são: cadastrar, excluir e exibir lista");
                break;
            case "Operador":
                JOptionPane.showMessageDialog(null,
                    "Operador\nSuas ações são: cadastrar e exibir lista");
                break;
            case "Usuário":
                JOptionPane.showMessageDialog(null,
                    "Usuário comum\nSua ação é: exibir lista");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuário desconhecido.");
                return; // não abre o menu se o tipo for inválido
        }

        dispose(); // fecha o login
        new Copercog(tipo); // abre o menu passando o tipo
    }
}