package copercog.view;


import static DAO.EstoqueDAO.select_produtos_em_estoque;
import DAO.EstoqueService;
import copercog.model.Cogumelos;
import java.awt.BasicStroke;
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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class acoesdeestoque extends JFrame {

    private JPanel jPanel_1, jPanel_2;

    private JButton jbtnadd, jbtnvoltar, jbtnremover, jbtncancelar;

    private JTextField  txtqt;
    private JComboBox<Cogumelos> JProduto ;

    private JLabel jLabel_1, jLabel_2, jLabel_4, jLabel_5;

    private JLabel jlberr, lblMensagem,lblMensagemR;

    private JSeparator jSeparator_2;

    private double escala;

    private String tipo;

    public acoesdeestoque(String tipo) {

        this.tipo = tipo;

        setTitle("Ações de estoque");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

        escala = tela.width / 1366.0;

        definirLayout();

        
    }

    private int a(int valor) {

        return (int) Math.round(valor * escala);
    }

    

    private void definirLayout() {

        jPanel_1 = new JPanel(null) {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setPaint(
                        new GradientPaint(
                                0,
                                0,
                                new Color(0x4953C3),
                                getWidth(),
                                0,
                                new Color(0x23285D)
                        )
                );

                g2d.fillRect(
                        0,
                        0,
                        getWidth(),
                        a(100)
                );

                g2d.setPaint(new Color(0xF4F7FA));

                g2d.fillRect(
                        0,
                        a(100),
                        getWidth(),
                        getHeight() - a(100)
                );
            }
        };

        setContentPane(jPanel_1);

        // =========================
        // HEADER
        // =========================

        jLabel_4 = new JLabel("E s t o q u e");

        jLabel_4.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(28)
                )
        );

        jLabel_4.setForeground(Color.WHITE);

        jLabel_4.setBounds(
                a(120),
                a(20),
                a(400),
                a(35)
        );

        jPanel_1.add(jLabel_4);

        jLabel_5 =
                new JLabel(
                        "Gerenciamento de produtos"
                );

        jLabel_5.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        a(14)
                )
        );

        jLabel_5.setForeground(new Color(0xCCCCCC));

        jLabel_5.setBounds(
                a(120),
                a(55),
                a(400),
                a(20)
        );

        jPanel_1.add(jLabel_5);

        // =========================
        // CARD CENTRAL
        // =========================

        jPanel_2 = new JPanel(null);

        jPanel_2.setBackground(Color.WHITE);

        jPanel_2.setBounds(
                a(150),
                a(130),
                a(1066),
                a(550)
        );

        jPanel_2.setBorder(
                new UI.BordaArredondada(
                        a(15),
                        new Color(0xE0E0E0)
                )
        );

        jPanel_1.add(jPanel_2);

        // =========================
        // BOTÃO VOLTAR
        // =========================

        jbtnvoltar = new JButton(" Voltar");

        jbtnvoltar.setBounds(
                a(40),
                a(30),
                a(120),
                a(40)
        );

        jbtnvoltar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(14)
                )
        );

        jbtnvoltar.setBackground(
                new Color(0xF5F5F5)
        );

        jbtnvoltar.setForeground(Color.BLACK);

        jbtnvoltar.setFocusPainted(false);

        jbtnvoltar.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        jbtnvoltar.setBorder(
                new UI.BordaArredondada(
                        a(10),
                        new Color(0xDDDDDD)
                )
        );

        jbtnvoltar.addActionListener(
                e -> jbtnvoltarActionPerformed(e)
        );
        
        
        
           jbtnvoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnvoltar.setBackground(new Color(0xEEEEEE));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnvoltar.setBackground(Color.WHITE);
            }
        });
          
          

        jPanel_2.add(jbtnvoltar);

        // =========================
        // TÍTULO AÇÕES
        // =========================

        JLabel lblAcoes =
                new JLabel(
                        "Ações ⇅",
                        SwingConstants.CENTER
                );

        lblAcoes.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(24)
                )
        );

        lblAcoes.setForeground(
                new Color(0x0A1F44)
        );

        lblAcoes.setBounds(
                a(433),
                a(30),
                a(200),
                a(40)
        );

        jPanel_2.add(lblAcoes);

        jSeparator_2 = new JSeparator();

        jSeparator_2.setBounds(
                a(40),
                a(90),
                a(986),
                a(1)
        );
         jSeparator_2.setForeground(new Color(0xEEEEEE));
       

        jPanel_2.add(jSeparator_2);

        // =========================
        // CAMPO NOME
        // =========================

        jLabel_1 =
                new JLabel(
                        "Nome do produto:"
                );

        jLabel_1.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(16)
                )
        );

        jLabel_1.setBounds(
                a(80),
                a(120),
                a(300),
                a(25)
        );

        jPanel_2.add(jLabel_1);

        
        List<Cogumelos> listaCogumelos = select_produtos_em_estoque();
JProduto = new JComboBox<>(listaCogumelos.toArray(new Cogumelos[0]));
JProduto.setBounds(a(80), a(150), a(906), a(45));
aplicarEstilo(JProduto);
jPanel_2.add(JProduto);
        
      
      

        // =========================
        // CAMPO QUANTIDADE
        // =========================

        jLabel_2 = new JLabel("Quantidade:");

        jLabel_2.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(16)
                )
        );

        jLabel_2.setBounds(
                a(80),
                a(220),
                a(300),
                a(25)
        );

        jPanel_2.add(jLabel_2);

        txtqt =
                new JTextField(
                        " Digite a quantidade"
                );

        txtqt.setBounds(
                a(80),
                a(250),
                a(400),
                a(45)
        );

        txtqt.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        a(14)
                )
        );

        txtqt.setForeground(Color.GRAY);

        txtqt.setBorder(
                BorderFactory.createCompoundBorder(
                        new UI.BordaArredondada(
                                a(8),
                                new Color(0xCCCCCC)
                        ),
                        new EmptyBorder(
                                0,
                                10,
                                0,
                                10
                        )
                )
        );
        
        txtqt.addFocusListener(new java.awt.event.FocusAdapter() {
    @Override
    public void focusGained(java.awt.event.FocusEvent evt) {
   
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtqt.setCaretPosition(0);
            }
        });
    }
});
    
          

        jPanel_2.add(txtqt);

        // =========================
        // BOTÃO ADD
        // =========================
 Border bordaEntalhada = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        jbtnadd =
                new JButton(
                        "Adicionar estoque +"
                );

        jbtnadd.setBounds(
                a(80),
                a(430),
                a(280),
                a(55)
        );

        jbtnadd.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(16)
                )
        );

        jbtnadd.setBackground(Color.decode("#0388E3"));

        jbtnadd.setForeground(Color.WHITE);

   

        jbtnadd.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

     
    
        jbtnadd.setBorder(bordaEntalhada);
        
        
        jbtnadd.addActionListener(
                e -> jbtnaddActionPerformed(e)
        );

       
            //26DD5D
           jbtnadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
     jbtnadd.setBackground(Color.decode("#0388E3"));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
      jbtnadd.setBackground(Color.decode("#0256B8"));
            }
        });
        
        
        
        
        
        
        jPanel_2.add(jbtnadd);

        // =========================
        // BOTÃO REMOVER
        // =========================

        jbtnremover =
                new JButton(
                        "Remover estoque -"
                );

        jbtnremover.setBounds(
                a(380),
                a(430),
                a(280),
                a(55)
        );

        jbtnremover.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(16)
                )
        );

          jbtnremover.setBackground(Color.decode("#8D2626"));

        jbtnremover.setForeground(Color.WHITE);

        jbtnremover.setFocusPainted(false);

        jbtnremover.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

//8D2626
        
     jbtnremover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                            jbtnremover.setBackground(Color.decode("#E64A19"));
          
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
              jbtnremover.setBackground(Color.decode("#8D2626"));
            }
        });
        

        
     
       jbtnremover.setBorder(bordaEntalhada);

        jbtnremover.addActionListener(
                e -> jbtnremoverActionPerformed(e)
        );

        jPanel_2.add(jbtnremover);

        // =========================
        // BOTÃO CANCELAR
        // =========================

        jbtncancelar =
                new JButton("Cancelar");

        jbtncancelar.setBounds(
                a(680),
                a(430),
                a(200),
                a(55)
        );

        jbtncancelar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        a(16)
                )
        );

        jbtncancelar.setBackground(
                new Color(0xF5F5F5)
        );

        jbtncancelar.setForeground(Color.BLACK);

        jbtncancelar.setFocusPainted(false);

        jbtncancelar.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        jbtncancelar.setBorder(
                new UI.BordaArredondada(
                        a(10),
                        new Color(0xDDDDDD)
                )
        );

        jbtncancelar.addActionListener(
                e -> jlblCANCELARActionPerformed(null)
        );

        jPanel_2.add(jbtncancelar);

        // =========================
        // STATUS
        // =========================

        lblMensagem = new JLabel("");

        lblMensagem.setBounds(
                a(80),
                a(380),
                a(500),
                a(30)
        );
        
          lblMensagemR = new JLabel("");

        lblMensagemR.setBounds(
                a(80),
                a(380),
                a(500),
                a(30)
        );
        

        jPanel_2.add(lblMensagem);

        jPanel_2.add(lblMensagemR);
        jlberr =
                new JLabel(
                        "Erro produto inexistente!"
                );

        jlberr.setBounds(
                a(80),
                a(380),
                a(400),
                a(30)
        );

        jlberr.setForeground(Color.RED);

        jlberr.setVisible(false);

        jPanel_2.add(jlberr);
    }

    
   
    
    
    
    private void jbtnvoltarActionPerformed(ActionEvent evt) {

        new Gerenciamentodeprodutos(tipo)
                .setVisible(true);

        dispose();
    }

 private void jbtnaddActionPerformed(ActionEvent evt) {

    Cogumelos cg = (Cogumelos) JProduto.getSelectedItem();

    if (cg == null) {
        JOptionPane.showMessageDialog(
                null,
                "Selecione um produto!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    String qtdTexto = txtqt.getText().trim();

    EstoqueService service = new EstoqueService();

    boolean sucesso = service.adicionar(cg, qtdTexto);

    if (!sucesso) {
        JOptionPane.showMessageDialog(
                null,
                "Erro ao adicionar (valor inválido)",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    lblMensagem.setVisible(true);
    lblMensagem.setText("Produto adicionado com sucesso ✓");
    
    lblMensagem.setForeground(new Color(0, 128, 0));
    javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
    lblMensagem.setVisible(false);
});
timer.setRepeats(false);
timer.start();
    txtqt.setText("");
}

 private void jbtnremoverActionPerformed(ActionEvent evt) {

    Cogumelos cg = (Cogumelos) JProduto.getSelectedItem();

    if (cg == null) {
        JOptionPane.showMessageDialog(
                null,
                "Selecione um produto!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    String qtdTexto = txtqt.getText().trim();

    EstoqueService service = new EstoqueService();

    boolean sucesso = service.remover(cg, qtdTexto);

    if (!sucesso) {
        JOptionPane.showMessageDialog(
                null,
                "Erro ao remover (valor inválido)",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

      lblMensagemR.setVisible(true);
    lblMensagemR.setText("Produto removido");
    
    lblMensagemR.setForeground(Color.RED);
    javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
    lblMensagemR.setVisible(false);
});
timer.setRepeats(false);
timer.start();

    txtqt.setText("");
}

    private void jlblCANCELARActionPerformed(ActionEvent evt) {

      

        txtqt.setText("");

        jlberr.setVisible(false);

        lblMensagem.setVisible(false);
    }

    public static class UI {

        public static class BordaArredondada extends AbstractBorder {

            private final int raio;

            private final Color cor;

            public BordaArredondada(
                    int raio,
                    Color cor
            ) {

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

                Graphics2D g2 =
                        (Graphics2D) g;

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

            Graphics2D g2 =
                    (Graphics2D) g;

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    c.getBackground()
            );

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
      public static void aplicarEstilo(JComboBox<?> combo) {
        combo.setBackground(Color.WHITE);
        combo.setFocusable(false);
        combo.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(new Color(150, 150, 180));
                        g2.setStroke(new BasicStroke(1.2f));
                        int w = getWidth(), h = getHeight();
                        g2.drawLine(w / 2 - 4, h / 2 - 2, w / 2, h / 2 + 2);
                        g2.drawLine(w / 2, h / 2 + 2, w / 2 + 4, h / 2 - 2);
                        g2.dispose();
                    }
                };
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusable(false);
                return button;
            }
        });
    }
    
    
}