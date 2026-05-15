package copercog.view;

import DAO.CogumelosDAO;
import copercog.model.Cogumelos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.AbstractBorder;

public class cadastrodeproduto extends JFrame {

    private JPanel jPanel_Fundo, jPanel_Header, jPanel_Card, jPanel_Preview;
    private JLabel jlblPreco, jlblTipo, jlblNome, jlblTitulo, jlblVoltar, jlblCancelar;
    private JRadioButton jrbinconserva, jrbinnatura;
    private ButtonGroup grupo;
    private JButton jbtnConcluir;
    private JTextField txtNome, txtPreco;

    private double escala;
    private String tipo;

    public cadastrodeproduto(String tipo) {

        this.tipo = tipo;

        setTitle("Cadastro de Produto");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        escala = tela.width / 1366.0;

        configurarTela();
    }

    private int a(int valor) {
        return (int) Math.round(valor * escala);
    }

    private void configurarTela() {

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

        int larguraTela = tela.width;
        int alturaTela = tela.height;

        jPanel_Fundo = new JPanel();
        jPanel_Fundo.setLayout(null);
        jPanel_Fundo.setBackground(new Color(0xF2F2F2));
        add(jPanel_Fundo);

        // HEADER
        jPanel_Header = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2d = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0,
                        0,
                        new Color(0x23285D),
                        getWidth(),
                        0,
                        new Color(0x009ADB)
                );

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        jPanel_Header.setLayout(null);
        jPanel_Header.setBounds(0, 0, larguraTela, a(85));

        jPanel_Fundo.add(jPanel_Header);

        jlblTitulo = new JLabel("Cadastro de produtos", SwingConstants.CENTER);
        jlblTitulo.setBounds(0, 0, larguraTela, a(85));
        jlblTitulo.setFont(new Font("Poppins", Font.BOLD, a(32)));
        jlblTitulo.setForeground(Color.WHITE);

        jPanel_Header.add(jlblTitulo);

        // CARD
        jPanel_Card = new JPanel();
        jPanel_Card.setLayout(null);
        jPanel_Card.setBackground(Color.WHITE);

        int cardL = a(820);
        int cardA = a(470);

        jPanel_Card.setBounds(
                (larguraTela - cardL) / 2,
                a(120),
                cardL,
                cardA
        );

        UI.arredondar(jPanel_Card, a(20), new Color(0xD8D8D8));

        jPanel_Fundo.add(jPanel_Card);

        // NOME
        jlblNome = new JLabel("Nome:");
        jlblNome.setBounds(a(30), a(30), a(200), a(25));
        jlblNome.setFont(new Font("Segoe UI", Font.BOLD, a(16)));

        jPanel_Card.add(jlblNome);

        txtNome = new JTextField(" Digite o nome do produto...");
        txtNome.setBounds(a(30), a(60), a(380), a(42));
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, a(14)));

        UI.arredondar(txtNome, a(12), new Color(0xBEBEBE));

        jPanel_Card.add(txtNome);

        // PREÇO
        jlblPreco = new JLabel("Preço:");
        jlblPreco.setBounds(a(30), a(120), a(200), a(25));
        jlblPreco.setFont(new Font("Segoe UI", Font.BOLD, a(16)));

        jPanel_Card.add(jlblPreco);

        txtPreco = new JTextField(" Digite o preço...");
        txtPreco.setBounds(a(30), a(150), a(380), a(42));
        txtPreco.setFont(new Font("Segoe UI", Font.PLAIN, a(14)));

        UI.arredondar(txtPreco, a(12), new Color(0xBEBEBE));

        jPanel_Card.add(txtPreco);

        // TIPO
        jlblTipo = new JLabel("Tipo:");
        jlblTipo.setBounds(a(30), a(210), a(200), a(25));
        jlblTipo.setFont(new Font("Segoe UI", Font.BOLD, a(16)));

        jPanel_Card.add(jlblTipo);

        grupo = new ButtonGroup();

        jrbinnatura = new JRadioButton(" In natura");
       jrbinnatura.setBounds(a(30), a(245), a(170), a(38));
        jrbinnatura.setFont(new Font("Segoe UI", Font.PLAIN, a(14)));
        jrbinnatura.setBackground(new Color(0xE8F0FE));
        jrbinnatura.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jrbinnatura.setOpaque(true);

        jrbinconserva = new JRadioButton(" In conserva");
 jrbinconserva.setBounds(a(210), a(245), a(190), a(38));
        jrbinconserva.setFont(new Font("Segoe UI", Font.PLAIN, a(14)));
        jrbinconserva.setBackground(Color.WHITE);
        jrbinconserva.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jrbinconserva.setOpaque(true);

        grupo.add(jrbinnatura);
        grupo.add(jrbinconserva);

        jPanel_Card.add(jrbinnatura);
        jPanel_Card.add(jrbinconserva);

        // PREVIEW
        jPanel_Preview = new JPanel();
        jPanel_Preview.setLayout(null);
        jPanel_Preview.setBackground(Color.WHITE);

        jPanel_Preview.setBounds(
                a(470),
                a(30),
                a(310),
                a(360)
        );

        UI.arredondar(jPanel_Preview, a(15), new Color(0xDDDDDD));

        jPanel_Card.add(jPanel_Preview);

        JLabel lblPrevTitulo = new JLabel(
                "Pré-visualização do Produto",
                SwingConstants.CENTER
        );

        lblPrevTitulo.setBounds(0, a(10), a(310), a(25));
        lblPrevTitulo.setFont(new Font("Segoe UI", Font.BOLD, a(15)));

        jPanel_Preview.add(lblPrevTitulo);

        JPanel imgPlaceholder = new JPanel();
        imgPlaceholder.setBounds(a(30), a(50), a(250), a(220));
        imgPlaceholder.setBackground(new Color(0xE3E3E3));

        UI.arredondar(imgPlaceholder, a(15), new Color(0xD0D0D0));

        jPanel_Preview.add(imgPlaceholder);

        JLabel lblIcone = new JLabel("", SwingConstants.CENTER);
        lblIcone.setBounds(0, a(100), a(250), a(80));
        lblIcone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, a(52)));

        imgPlaceholder.add(lblIcone);

        // BOTÃO
        jbtnConcluir = new JButton("CONCLUIR") {

            @Override
            protected void paintComponent(Graphics g) {

                UI.pintarFundo(this, g, a(12));

                super.paintComponent(g);
            }
        };

        jbtnConcluir.setBounds(a(180), a(395), a(180), a(45));

        jbtnConcluir.setBackground(new Color(0x107D5B));
        jbtnConcluir.setForeground(Color.WHITE);

        jbtnConcluir.setFont(new Font("Segoe UI", Font.BOLD, a(16)));

        jbtnConcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jbtnConcluir.setContentAreaFilled(false);
        jbtnConcluir.setFocusPainted(false);
        jbtnConcluir.setBorderPainted(false);

        jbtnConcluir.addActionListener(e -> jbtncadastrarActionPerformed(e));

        jbtnConcluir.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {

                jbtnConcluir.setBackground(Color.WHITE);
                jbtnConcluir.setForeground(new Color(0x107D5B));

                jbtnConcluir.setBorder(
                        new UI.BordaArredondada(
                                a(12),
                                new Color(0x107D5B)
                        )
                );
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {

                jbtnConcluir.setBackground(new Color(0x107D5B));
                jbtnConcluir.setForeground(Color.WHITE);

                jbtnConcluir.setBorder(
                        new UI.BordaArredondada(
                                a(12),
                                Color.BLACK
                        )
                );
            }
        });

        jPanel_Card.add(jbtnConcluir);

        // CANCELAR
        jlblCancelar = new JLabel("Cancelar", SwingConstants.CENTER);

        jlblCancelar.setBounds(a(380), a(395), a(120), a(45));

        jlblCancelar.setForeground(new Color(0x8D2626));

        jlblCancelar.setFont(new Font("Segoe UI", Font.PLAIN, a(20)));

        jlblCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jlblCancelar.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                jlblCANCELARActionPerformed(null);
            }
        });

        jPanel_Card.add(jlblCancelar);

        // VOLTAR
        jlblVoltar = new JLabel("Voltar ⮞ ", SwingConstants.CENTER);

  jlblVoltar.setBounds(
        (larguraTela - a(220)) / 2,
        a(620),
        a(220),
        a(45)
);

        jlblVoltar.setForeground(new Color(0x107D5B));

      jlblVoltar.setFont(new Font("Dialog", Font.BOLD, a(18)));

        jlblVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jlblVoltar.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                jlblirActionPerformed(null);
            }
        });

        jPanel_Fundo.add(jlblVoltar);
    }

    
private void jbtncadastrarActionPerformed(ActionEvent evt) {

    // 1. Coleta e limpeza dos dados de entrada
    String nome = txtNome.getText().trim();
    String precotxt = txtPreco.getText().trim();


    // 2. Validação de preenchimento obrigatório
    if (nome.isEmpty() || precotxt.isEmpty()) {

        JOptionPane.showMessageDialog(this, "Preencha todos os campos.");

        return;
    }


    // 3. Validação de seleção dos botões de opção (RadioButtons)
    if (!jrbinnatura.isSelected() && !jrbinconserva.isSelected()) {

        JOptionPane.showMessageDialog(this, "Selecione o tipo do cogumelo.");

        return;
    }


    // 4. Validação de formato (Nome deve conter apenas letras)
    if (!nome.matches("^[\\p{L} ]+$")) {

        JOptionPane.showMessageDialog(this, "Nome só pode conter letras.");

        return;
    }


    try {

      
        double preco = Double.parseDouble(precotxt.replace(",", "."));

        if (preco <= 0) {

            JOptionPane.showMessageDialog(this, "Preço deve ser maior que zero.");

            return;
        }


      
        String tipoProduto = jrbinnatura.isSelected() ? "in natura" : "conserva";


        // 7. Processo de salvamento via DAO
        Cogumelos cogumelo = new Cogumelos(nome, tipoProduto, preco);

        CogumelosDAO dao = new CogumelosDAO();

        dao.insert_cogumelos(cogumelo);



        new TelaConfirmacao().setVisible(true);

        dispose();


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this, "Preço inválido! Use números e vírgula.");


    } catch (Exception e) {

       
        // O erro é identificado buscando o texto "Duplicate entry" na exceção
        String erroInfo = e.toString();

        if (erroInfo.contains("Duplicate entry")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto já cadastrado no sistema.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}

    public void limparcampos() {

        txtNome.setText("");
        txtPreco.setText("");

        grupo.clearSelection();
    }

    private void jlblirActionPerformed(ActionEvent evt) {

        new Gerenciamentodeprodutos(tipo).setVisible(true);

        dispose();
    }

    private void jlblCANCELARActionPerformed(ActionEvent evt) {

        limparcampos();
    }

    // =========================
    // UI
    // =========================
    public static class UI {

        public static class BordaArredondada extends AbstractBorder {

            private final int raio;
            private final Color cor;

            public BordaArredondada(int raio, Color cor) {

                this.raio = raio;
                this.cor = cor;
            }

            @Override
            public void paintBorder(
                    Component c,
                    Graphics g,
                    int x,
                    int y,
                    int w,
                    int h
            ) {

                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(cor);

                g2.drawRoundRect(
                        x,
                        y,
                        w - 1,
                        h - 1,
                        raio,
                        raio
                );
            }
        }

        public static <T extends JComponent> T arredondar(
                T comp,
                int raio,
                Color cor
        ) {

            comp.setOpaque(false);

            comp.setBorder(
                    new BordaArredondada(
                            raio,
                            cor
                    )
            );

            return comp;
        }

        public static void pintarFundo(
                Component c,
                Graphics g,
                int raio
        ) {

            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(c.getBackground());

            g2.fillRoundRect(
                    0,
                    0,
                    c.getWidth(),
                    c.getHeight(),
                    raio,
                    raio
            );
        }
    }
}