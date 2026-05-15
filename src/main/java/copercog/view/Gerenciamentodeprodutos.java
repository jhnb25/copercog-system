package copercog.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.AbstractBorder;

public class Gerenciamentodeprodutos extends JFrame {

    private JPanel jPanel_1, jPanel_2;
    private JLabel jlblgerenciamento, jlblgerenc;
    private JSeparator jSeparator_1;
    private JButton jbtncadastrar, jbtnver, jbtnacoes;
    private double escala;
    private String tipo; // ← campo para cascata

    public Gerenciamentodeprodutos(String tipo) {
        this.tipo = tipo;
        setTitle("Gerenciamento de produtos");
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
            labelComImagem.setBounds(a(20), a(10), larguraCalculada, alturaDesejada);
            jPanel_2.add(labelComImagem);
            jPanel_2.revalidate();
            jPanel_2.repaint();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }

    public void definir() {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        // ===== PAINEL PRINCIPAL (GRADIENTE) =====
        jPanel_1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0x009ADB),
                        getWidth(), 0, new Color(0x23285D)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel_1.setBounds(0, 0, larguraTela, alturaTela);
        jPanel_1.setLayout(null);
        add(jPanel_1);

        // ===== PAINEL CONTEÚDO =====D9D9D9
        jPanel_2 = new JPanel();
        jPanel_2.setBounds(a(43), a(40), a(1280), a(800));
        jPanel_2.setBackground(Color.white);
        jPanel_2.setLayout(null);
        jPanel_1.add(jPanel_2);

        
          
        
        // ===== LABELS =====
        jlblgerenciamento = new JLabel(" Estoque");
        jlblgerenciamento.setBounds(a(240), a(20), a(350), a(25));
        jlblgerenciamento.setFont(new Font("Libre Baskerville", Font.BOLD, a(22)));
        jlblgerenciamento.setForeground(Color.black);
        jPanel_2.add(jlblgerenciamento);

        jlblgerenc = new JLabel("Controle e organização do estoque em um só lugar.");
        jlblgerenc.setBounds(a(420), a(170), a(650), a(25));
        jlblgerenc.setFont(new Font("Libre Baskerville", Font.ITALIC, a(16)));
        jlblgerenc.setForeground(Color.black);
        jPanel_2.add(jlblgerenc);

        // ===== SEPARADOR =====
        jSeparator_1 = new JSeparator();
        jSeparator_1.setBounds(0, a(60), a(1280), a(2));
        jSeparator_1.setForeground(new Color(0xEEEEEE));
        jPanel_2.add(jSeparator_1);

        
        

     

        
        
        
        // ===== BOTÃO VER PRODUTOS =====
        jbtnver = new JButton("📋 Ver produtos") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtnver.setBounds(a(20), a(80), a(180), a(35));
        jbtnver.setBackground(Color.white);
        jbtnver.setForeground(Color.black);
        jbtnver.setFont(new Font("Zilla Slab", Font.BOLD, a(16)));
        jbtnver.setToolTipText("Clique para visualizar a lista completa de produtos em estoque.");
        jbtnver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnver.setOpaque(false);
        jbtnver.setContentAreaFilled(false);
        jbtnver.setFocusPainted(false);
        jbtnver.setBorder(new UI.BordaArredondada(a(12), new Color(0xCCCCCC)));
        jbtnver.addActionListener(e -> jbtnverActionPerformed(e));
        jbtnver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnver.setBackground(new Color(0xEEEEEE));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnver.setBackground(Color.WHITE);
            }
        });
        jPanel_2.add(jbtnver);

        // ===== BOTÃO CADASTRAR =====
        jbtncadastrar = new JButton("Cadastrar produtos") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtncadastrar.setBounds(a(210), a(80), a(220), a(35));
        jbtncadastrar.setBackground(new Color(0x4781FF));
        jbtncadastrar.setForeground(Color.WHITE);
        jbtncadastrar.setFont(new Font("Zilla Slab", Font.BOLD, a(16)));
        jbtncadastrar.setToolTipText("Abre o formulário para adicionar novos itens ao sistema.");
        jbtncadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtncadastrar.setOpaque(false);
        jbtncadastrar.setContentAreaFilled(false);
        jbtncadastrar.setFocusPainted(false);
        jbtncadastrar.setBorder(new UI.BordaArredondada(a(12), new Color(0x4953C3)));
        jbtncadastrar.addActionListener(e -> jbtncadastrarActionPerformed(e));
        jbtncadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtncadastrar.setBackground(Color.WHITE);
                jbtncadastrar.setForeground(new Color(0x4781FF));
                jbtncadastrar.setBorder(new UI.BordaArredondada(a(12), new Color(0x4781FF)));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtncadastrar.setBackground(new Color(0x4781FF));
                jbtncadastrar.setForeground(Color.WHITE);
                jbtncadastrar.setBorder(new UI.BordaArredondada(a(12), new Color(0x4953C3)));
            }
        });
        jPanel_2.add(jbtncadastrar);

        // ===== BOTÃO AÇÕES =====
        jbtnacoes = new JButton("Ações do estoque ⇌") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        jbtnacoes.setBounds(a(440), a(80), a(200), a(35));
        jbtnacoes.setBackground(Color.WHITE);
        jbtnacoes.setForeground(Color.black);
        jbtnacoes.setFont(new Font("Zilla Slab", Font.BOLD, a(16)));
        jbtnacoes.setToolTipText("Realize movimentações como entradas ou saídas de produto.");
        jbtnacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnacoes.setOpaque(false);
        jbtnacoes.setContentAreaFilled(false);
        jbtnacoes.setFocusPainted(false);
        jbtnacoes.setBorder(new UI.BordaArredondada(a(12), new Color(0xCCCCCC)));
        jbtnacoes.addActionListener(e -> jbtnacoesActionPerformed(e));
        jbtnacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jbtnacoes.setBackground(new Color(0xEEEEEE));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                jbtnacoes.setBackground(Color.WHITE);
            }
        });
        jPanel_2.add(jbtnacoes);

        // Usuário só pode ver produtos
        if (tipo.equals("Usuário")) {
            jbtncadastrar.setVisible(false);
            jbtnacoes.setVisible(false);
        }

        // ===== IMAGEM =====
        try {
            URL url = getClass().getResource("/copercog/resources/imagemdeestoque_upscayl_4x_upscayl-standard-4x.png");
            if (url == null) {
                throw new IllegalArgumentException("Imagem não encontrada no caminho especificado.");
            }
            ImageIcon icon = new ImageIcon(url);
            Image imgRedimensionada = icon.getImage().getScaledInstance(a(900), a(600), Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(imgRedimensionada));
            label.setBounds(a(190), a(125), a(900), a(600));
            jPanel_2.add(label);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void jbtncadastrarActionPerformed(ActionEvent evt) {
        new cadastrodeproduto(tipo).setVisible(true);
        dispose();
    }

    private void jbtnacoesActionPerformed(ActionEvent evt) {
        new acoesdeestoque(tipo).setVisible(true);
        dispose();
    }

    private void jbtnverActionPerformed(ActionEvent evt) {
        new ControleEstoque(tipo).setVisible(true);
        dispose();
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