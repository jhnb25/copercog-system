package copercog.view;

import DAO.EstoqueService;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.AbstractBorder;

public class acoesdeestoque extends JFrame {

    private JPanel jPanel_1, jPanel_2;
    private JButton jbtnadd, jbtnvoltar, jbtnremover;
    private JTextField txtNome, txtqt;
    private JLabel jLabel_1, jLabel_2, jLabel_4, jLabel_5, jlblCANCELAR, jlberr;
    private JLabel lblMensagem;
    private JSeparator jSeparator_2;
    private double escala;
    private String tipo; // ← campo para cascata

    public void carregarIcone() {
        try {
            java.net.URL urlImagem = getClass().getResource("/copercog/resources/copercogimagem.png");
            if (urlImagem == null) {
                throw new IllegalArgumentException("Imagem não encontrada no caminho especificado.");
            }
            ImageIcon icon = new ImageIcon(urlImagem);
            int alturaDesejada = a(50);
            int larguraOriginal = icon.getIconWidth();
            int alturaOriginal = icon.getIconHeight();
            int larguraCalculada = (larguraOriginal * alturaDesejada) / alturaOriginal;
            java.awt.Image imgRedimensionada = icon.getImage()
                    .getScaledInstance(larguraCalculada, alturaDesejada, java.awt.Image.SCALE_SMOOTH);
            JLabel labelComImagem = new JLabel(new ImageIcon(imgRedimensionada));
            labelComImagem.setBounds(a(20), a(15), larguraCalculada, alturaDesejada);
            jPanel_2.add(labelComImagem);
            jPanel_2.revalidate();
            jPanel_2.repaint();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }

    public acoesdeestoque(String tipo) {
        this.tipo = tipo;
        setTitle("Ações de estoque");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        escala = tela.width / 1366.0;

        definir();
        carregarIcone();
    }

    private int a(int valor) {
        return (int) Math.round(valor * escala);
    }

    public void definir() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        jPanel_1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(
                        0, 0, new Color(0x4953C3),
                        getWidth(), 0, new Color(0x23285D)
                ));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel_1.setBounds(0, 0, larguraTela, alturaTela);
        jPanel_1.setLayout(null);
        add(jPanel_1);

        jPanel_2 = new JPanel();
        jPanel_2.setBounds(a(35), a(60), a(1280), a(700));
        jPanel_2.setBackground(Color.WHITE);
        jPanel_2.setLayout(null);
        jPanel_1.add(jPanel_2);

        jSeparator_2 = new JSeparator();
        jSeparator_2.setBounds(0, a(104), a(1280), a(2));
        jPanel_2.add(jSeparator_2);

        jLabel_4 = new JLabel("Estoque");
        jLabel_4.setBounds(a(100), a(15), a(400), a(30));
        jLabel_4.setFont(new Font("SansSerif", Font.BOLD, a(24)));
        jPanel_2.add(jLabel_4);

        jLabel_5 = new JLabel("Gerenciamento de produtos");
        jLabel_5.setBounds(a(100), a(45), a(400), a(25));
        jLabel_5.setFont(new Font("SansSerif", Font.PLAIN, a(16)));
        jPanel_2.add(jLabel_5);

        jLabel_1 = new JLabel("Nome do produto:");
        jLabel_1.setBounds(a(150), a(220), a(200), a(30));
        jLabel_1.setFont(new Font("SansSerif", Font.BOLD, a(18)));
        jPanel_2.add(jLabel_1);

        txtNome = new JTextField();
        txtNome.setBounds(a(150), a(250), a(500), a(35));
        jPanel_2.add(txtNome);

        jLabel_2 = new JLabel("Quantidade:");
        jLabel_2.setBounds(a(150), a(300), a(200), a(30));
        jLabel_2.setFont(new Font("SansSerif", Font.BOLD, a(18)));
        jPanel_2.add(jLabel_2);

        txtqt = new JTextField();
        txtqt.setBounds(a(150), a(330), a(300), a(35));
        jPanel_2.add(txtqt);

        // ===== BOTÃO VOLTAR =====
        jbtnvoltar = new JButton("🢪 Voltar") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getParent().getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtnvoltar.setBounds(a(20), a(130), a(180), a(35));
        jbtnvoltar.setBackground(Color.decode("#2485CA"));
        jbtnvoltar.setForeground(Color.WHITE);
        jbtnvoltar.setFont(new Font("SansSerif", Font.BOLD, a(16)));
        jbtnvoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnvoltar.setContentAreaFilled(false);
        jbtnvoltar.setFocusPainted(false);
        UI.arredondar(jbtnvoltar, a(12), new Color(0x1A6FA0));
        jbtnvoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnvoltar.setBackground(Color.decode("#1A6FA0"));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnvoltar.setBackground(Color.decode("#2485CA"));
            }
        });
        jPanel_2.add(jbtnvoltar);

        JPanel jpanelACOES = new JPanel();
        jpanelACOES.setLayout(null);
        jpanelACOES.setBounds(a(300), a(130), a(450), a(40));
        jpanelACOES.setBackground(Color.decode("#0045DB"));

        JLabel lblTextoAcoes = new JLabel("Ações");
        lblTextoAcoes.setForeground(Color.white);
        lblTextoAcoes.setFont(new Font("SansSerif", Font.BOLD, a(20)));
        lblTextoAcoes.setBounds(a(175), 0, a(70), a(40));

        JLabel dsa = new JLabel("⇅");
        dsa.setForeground(Color.decode("#3AC49D"));
        dsa.setFont(new Font("SansSerif", Font.BOLD, a(20)));
        dsa.setBounds(a(250), 0, a(30), a(40));

        jpanelACOES.add(lblTextoAcoes);
        jpanelACOES.add(dsa);
        jPanel_2.add(jpanelACOES);

        // ===== BOTÃO ADICIONAR =====
        jbtnadd = new JButton("Adicionar estoque +") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getParent().getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtnadd.setBounds(a(300), a(400), a(650), a(40));
        jbtnadd.setBackground(new Color(63, 81, 181));
        jbtnadd.setForeground(Color.WHITE);
        jbtnadd.setFont(new Font("SansSerif", Font.BOLD, a(18)));
        jbtnadd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnadd.setContentAreaFilled(false);
        jbtnadd.setFocusPainted(false);
        UI.arredondar(jbtnadd, a(12), new Color(0x3040AA));
        jbtnadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnadd.setBackground(new Color(48, 64, 170));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnadd.setBackground(new Color(63, 81, 181));
            }
        });
        jPanel_2.add(jbtnadd);

        // ===== BOTÃO REMOVER =====
        jbtnremover = new JButton("Remover estoque -") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getParent().getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtnremover.setBounds(a(300), a(450), a(650), a(40));
        jbtnremover.setBackground(new Color(255, 193, 7));
        jbtnremover.setForeground(Color.BLACK);
        jbtnremover.setFont(new Font("SansSerif", Font.BOLD, a(18)));
        jbtnremover.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnremover.setContentAreaFilled(false);
        jbtnremover.setFocusPainted(false);
        UI.arredondar(jbtnremover, a(12), new Color(0xC8960A));
        jbtnremover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnremover.setBackground(new Color(200, 150, 10));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnremover.setBackground(new Color(255, 193, 7));
            }
        });
        jPanel_2.add(jbtnremover);

        jlblCANCELAR = new JLabel("Cancelar");
        jlblCANCELAR.setBounds(a(560), a(530), a(100), a(35));
        jlblCANCELAR.setFont(new Font("Kameron", Font.BOLD, a(19)));
        jlblCANCELAR.setForeground(Color.red);
        jlblCANCELAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_2.add(jlblCANCELAR);

        jlblCANCELAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblCANCELARActionPerformed(null);
            }
        });

        lblMensagem = new JLabel("");
        lblMensagem.setBounds(a(500), a(500), a(500), a(35));
        jPanel_2.add(lblMensagem);

        jlberr = new JLabel("Erro produto inexistente!");
        jlberr.setBounds(a(530), a(530), a(300), a(35));
        jlberr.setFont(new Font("Zilla Slab", Font.BOLD, a(20)));
        jlberr.setForeground(Color.red);
        jlberr.setVisible(false);
        jPanel_2.add(jlberr);

        jbtnvoltar.addActionListener(e -> jbtnvoltarActionPerformed(e));
        jbtnadd.addActionListener(e -> jbtnaddActionPerformed(e));
        jbtnremover.addActionListener(e -> jbtnremoverActionPerformed(e));
    }

    private void jbtnvoltarActionPerformed(ActionEvent evt) {
        new Gerenciamentodeprodutos(tipo).setVisible(true); // passa o tipo de volta
        dispose();
    }

    private void jbtnaddActionPerformed(ActionEvent evt) {
        String nome = txtNome.getText().trim();
        String qtdTexto = txtqt.getText().trim();

        EstoqueService service = new EstoqueService();
        boolean sucesso = service.adicionar(nome, qtdTexto);

        if (!sucesso) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao adicionar (produto inexistente ou valor inválido)",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        lblMensagem.setVisible(true);
        lblMensagem.setText("Produto adicionado com sucesso ✓");
        lblMensagem.setForeground(new Color(0, 128, 0));
        txtNome.setText("");
        txtqt.setText("");
    }

    private void jbtnremoverActionPerformed(ActionEvent evt) {
        String nome = txtNome.getText().trim();
        String qtdTexto = txtqt.getText().trim();

        EstoqueService service = new EstoqueService();
        boolean sucesso = service.remover(nome, qtdTexto);

        if (!sucesso) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao remover (produto inexistente ou valor inválido)",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        txtNome.setText("");
        txtqt.setText("");
    }

    private void jlblCANCELARActionPerformed(ActionEvent evt) {
        txtNome.setText("");
        txtqt.setText("");
        jlberr.setVisible(false);
        lblMensagem.setVisible(false);
        jlblCANCELAR.setVisible(true);
    }

    public static class UI {

        public static class BordaArredondada extends AbstractBorder {

            private final int raio;
            private final Color cor;

            public BordaArredondada(int raio, Color cor) {
                this.raio = raio;
                this.cor = cor;
            }

            public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cor);
                g2.drawRoundRect(x, y, w - 1, h - 1, raio, raio);
            }
        }

        public static <T extends JComponent> T arredondar(T comp, int raio, Color cor) {
            comp.setOpaque(false);
            comp.setBorder(new BordaArredondada(raio, cor));
            return comp;
        }

        public static void pintarFundo(Component c, Graphics g, int raio) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), raio, raio);
        }
    }
}