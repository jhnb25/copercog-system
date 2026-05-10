package copercog.model;

import copercog.view.CadastrodeClientes;
import copercog.view.GerenciamentoDePedidos;
import copercog.view.Gerenciamentodeprodutos;
import copercog.view.TeladeUsuarios;

import javax.swing.*;
import java.awt.*;

public class Copercog {

    // ===== MAIN: só inicia o login, o menu abre depois =====
    public static void main(String[] args) {
        new TeladeUsuarios(); // login abre primeiro, passa o tipo pro menu
    }

    // ===== CONSTRUTOR: recebe o tipo do usuário logado =====
    public Copercog(String tipo) {

        JFrame menu = new JFrame("Copercog - Menu Principal");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setLayout(new BorderLayout());

        // ===== FUNDO GRADIENTE =====
        JPanel fundo = new JPanel() {
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

        fundo.setLayout(new BorderLayout());
        menu.setContentPane(fundo);

        // ===== TOPO =====
        JPanel topo = new JPanel(new BorderLayout());
        topo.setPreferredSize(new Dimension(100, 70));
        topo.setBackground(new Color(60, 60, 65));

        JLabel titulo = new JLabel("MENU - " + tipo, SwingConstants.CENTER); // mostra o tipo logado
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel menuIcon = new JLabel("≡", SwingConstants.CENTER);
        menuIcon.setPreferredSize(new Dimension(70, 70));
        menuIcon.setForeground(Color.WHITE);
        menuIcon.setFont(new Font("Arial", Font.BOLD, 26));
        menuIcon.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        topo.add(titulo, BorderLayout.CENTER);
        topo.add(menuIcon, BorderLayout.EAST);

        // ===== CENTRO =====
        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(Box.createVerticalGlue());

        // ===== CLIENTES =====
        // Todos os tipos acessam, mas cada tela controla o que exibe
        JPanel clientes = criarPainel("👥", "Clientes", "Cadastrar e consultar");
        clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new CadastrodeClientes(tipo).setVisible(true); // passa o tipo
            }
            public void mouseEntered(java.awt.event.MouseEvent e) { clientes.setBackground(new Color(80,80,85)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { clientes.setBackground(new Color(60,60,65)); }
        });

        // ===== ESTOQUE =====
        // Usuário comum não acessa estoque
        JPanel produtos = criarPainel("📦", "Estoque", "Estoque e ações");
        if (!tipo.equals("Usuário")) { // Usuário não vê estoque
            produtos.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new Gerenciamentodeprodutos(tipo).setVisible(true); // passa o tipo
                }
                public void mouseEntered(java.awt.event.MouseEvent e) { produtos.setBackground(new Color(80,80,85)); }
                public void mouseExited(java.awt.event.MouseEvent e)  { produtos.setBackground(new Color(60,60,65)); }
            });
        } else {
            produtos.setOpaque(true);
            produtos.setBackground(new Color(40,40,45)); // visual de bloqueado
            produtos.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        // ===== PEDIDOS =====
        JPanel pedidos = criarPainel("🧾", "Gerenciamento de Pedidos", "Criar e acompanhar");
        pedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new GerenciamentoDePedidos(tipo).setVisible(true); // passa o tipo
            }
            public void mouseEntered(java.awt.event.MouseEvent e) { pedidos.setBackground(new Color(80,80,85)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { pedidos.setBackground(new Color(60,60,65)); }
        });

        centro.add(clientes);
        centro.add(Box.createVerticalStrut(20));
        centro.add(produtos);
        centro.add(Box.createVerticalStrut(20));
        centro.add(pedidos);
        centro.add(Box.createVerticalGlue());

        fundo.add(topo, BorderLayout.NORTH);
        fundo.add(centro, BorderLayout.CENTER);

        menu.setVisible(true);
    }

    // ===== HELPER: monta painel de menu sem repetir código =====
    private JPanel criarPainel(String icone, String titulo, String subtitulo) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setMaximumSize(new Dimension(450, 70));
        painel.setBackground(new Color(60,60,65));
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painel.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        JLabel ic = new JLabel(icone);
        ic.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        ic.setForeground(Color.WHITE);

        JPanel txt = new JPanel();
        txt.setOpaque(false);
        txt.setLayout(new BoxLayout(txt, BoxLayout.Y_AXIS));

        JLabel t1 = new JLabel(titulo);
        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel t2 = new JLabel(subtitulo);
        t2.setForeground(new Color(180,220,215));
        t2.setFont(new Font("Arial", Font.PLAIN, 13));

        txt.add(t1);
        txt.add(t2);

        painel.add(ic, BorderLayout.WEST);
        painel.add(txt, BorderLayout.CENTER);

        return painel;
    }
}