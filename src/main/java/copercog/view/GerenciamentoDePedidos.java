package copercog.view;

import DAO.EstoqueDAO;
import DAO.PedidosDAO;
import static DAO.PedidosDAO.selectpedidos;
import copercog.model.Pedidos;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GerenciamentoDePedidos extends JFrame {

    private JPanel painelPrincipal;
    private JPanel cardPrincipal;
    private JLabel titulo;
    private JCheckBox check1, check2;
    private JButton btnNovo, btnExcluir, btnPesquisar;
    private JTable tabela;
    private DefaultTableModel model;
    private JLabel valor1, valor2, valor3, valor4;
    private double escala;
    private int larguraTela, alturaTela;
    List<Pedidos> linhas;
    private JTextField txtpesquisar;
    private Set<Integer> linhasConfirmadas = new java.util.HashSet<>();
    private String tipo; // ← campo para cascata

    public GerenciamentoDePedidos(String tipo) {
        this.tipo = tipo;
        setTitle("Gestão de Pedidos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        larguraTela = tela.width;
        alturaTela = tela.height;
        escala = larguraTela / 1366.0;

        linhas = selectpedidos();
        definir();

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                popularTabela();
            }
        });
    }

    private int a(int valor) {
        return (int) Math.round(valor * escala);
    }

    public void atualizarTabela() {
        linhas = selectpedidos();
        popularTabela();
    }

    public void definir() {

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(Color.decode("#3C3C46"));
        painelPrincipal.setBounds(0, 0, larguraTela, alturaTela);
        painelPrincipal.setLayout(null);
        add(painelPrincipal);

        cardPrincipal = new JPanel();
        cardPrincipal.setLayout(null);
        cardPrincipal.setBackground(Color.decode("#D9D9D9"));
        cardPrincipal.setBounds(a(40), a(30), a(1286), a(720));
        cardPrincipal.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), a(2), true));
        painelPrincipal.add(cardPrincipal);

        titulo = new JLabel("GESTÃO DE PEDIDOS");
        titulo.setBounds(a(40), a(5), a(400), a(30));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, a(20)));
        titulo.setForeground(Color.WHITE);
        painelPrincipal.add(titulo);

        // --- CARDS ---
        JPanel card1 = new JPanel(null);
        card1.setBackground(Color.WHITE);
        card1.setBounds(a(20), a(50), a(220), a(70));
        card1.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        cardPrincipal.add(card1);
        JLabel label1 = new JLabel("📦 Total de pedidos");
        label1.setBounds(a(15), a(10), a(200), a(20));
        label1.setFont(new Font("Segoe UI Emoji", Font.BOLD, a(13)));
        card1.add(label1);
        valor1 = new JLabel("—");
        valor1.setBounds(a(15), a(35), a(200), a(20));
        card1.add(valor1);

        JPanel card2 = new JPanel(null);
        card2.setBackground(Color.WHITE);
        card2.setBounds(a(260), a(50), a(220), a(70));
        card2.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        cardPrincipal.add(card2);
        JLabel label2 = new JLabel("🕒 Pendentes");
        label2.setBounds(a(15), a(10), a(200), a(20));
        label2.setFont(new Font("Segoe UI Emoji", Font.BOLD, a(13)));
        card2.add(label2);
        valor2 = new JLabel("—");
        valor2.setBounds(a(15), a(35), a(200), a(20));
        card2.add(valor2);

        JPanel card3 = new JPanel(null);
        card3.setBackground(Color.WHITE);
        card3.setBounds(a(500), a(50), a(220), a(70));
        card3.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        cardPrincipal.add(card3);
        JLabel label3 = new JLabel("✅ Concluídos");
        label3.setBounds(a(15), a(10), a(200), a(20));
        label3.setFont(new Font("Segoe UI Emoji", Font.BOLD, a(13)));
        card3.add(label3);
        valor3 = new JLabel("—");
        valor3.setBounds(a(15), a(35), a(200), a(20));
        card3.add(valor3);

        JPanel card4 = new JPanel(null);
        card4.setBackground(Color.WHITE);
        card4.setBounds(a(740), a(50), a(220), a(70));
        card4.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        cardPrincipal.add(card4);
        JLabel label4 = new JLabel("💰 Receita total");
        label4.setBounds(a(15), a(10), a(200), a(20));
        label4.setFont(new Font("Segoe UI Emoji", Font.BOLD, a(13)));
        card4.add(label4);
        valor4 = new JLabel("—");
        valor4.setBounds(a(15), a(35), a(200), a(20));
        card4.add(valor4);

        // --- BOTÃO ORDENAR ---
        JButton btnFiltros = new JButton("Ordenar  ▼") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, 8);
                super.paintComponent(g);
            }
        };
        btnFiltros.setBounds(a(550), a(135), a(120), a(30));
        btnFiltros.setBackground(Color.decode("#4A4A5A"));
        btnFiltros.setForeground(Color.WHITE);
        btnFiltros.setFont(new Font("Crimson Text", Font.PLAIN, a(13)));
        btnFiltros.setOpaque(false);
        btnFiltros.setContentAreaFilled(false);
        btnFiltros.setFocusPainted(false);
        btnFiltros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UI.arredondar(btnFiltros, 8, Color.decode("#4A4A5A"));
        cardPrincipal.add(btnFiltros);

        JPanel painelFiltros = new JPanel();
        painelFiltros.setLayout(new BoxLayout(painelFiltros, BoxLayout.Y_AXIS));
        painelFiltros.setBackground(Color.WHITE);
        painelFiltros.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        painelFiltros.setBounds(a(550), a(168), a(120), a(90));
        painelFiltros.setVisible(false);
        cardPrincipal.add(painelFiltros);

        ButtonGroup grupo = new ButtonGroup();
        check1 = new JCheckBox("Maior preço");
        check2 = new JCheckBox("Menor preço");
        grupo.add(check1);
        grupo.add(check2);

        check1.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                btnPesquisarActionPerformed(null);
            }
        });
        check2.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                btnPesquisarActionPerformed(null);
            }
        });

        painelFiltros.add(check1);
        painelFiltros.add(check2);

        btnFiltros.addActionListener(e -> {
            painelFiltros.setVisible(!painelFiltros.isVisible());
            btnFiltros.setText(painelFiltros.isVisible() ? "Ordenar  ▲" : "Ordenar  ▼");
        });

        // --- CAMPO DE PESQUISA ---
        txtpesquisar = new JTextField(15) {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, 7);
                super.paintComponent(g);
            }
        };
        UI.arredondar(txtpesquisar, 7, Color.BLACK);
        txtpesquisar.setBounds(a(550), a(170), a(220), a(35));
        txtpesquisar.setBackground(Color.WHITE);
        txtpesquisar.setForeground(Color.BLACK);
        txtpesquisar.setFont(new Font("Zilla Slab", Font.BOLD, a(16)));
        txtpesquisar.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        txtpesquisar.setToolTipText("Digite o nome do cliente");
        cardPrincipal.add(txtpesquisar);

        // --- BOTÃO PESQUISAR ---
        btnPesquisar = new JButton("\uD83D\uDD0D");
        btnPesquisar.setBounds(a(775), a(170), a(60), a(35));
        btnPesquisar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, a(18)));
        btnPesquisar.setForeground(Color.decode("#3C3C46"));
        btnPesquisar.setBackground(Color.decode("#D9D9D9"));
        btnPesquisar.setBorderPainted(false);
        btnPesquisar.setContentAreaFilled(true);
        btnPesquisar.setOpaque(true);
        btnPesquisar.setFocusPainted(false);
        btnPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPesquisar.addActionListener(evt -> btnPesquisarActionPerformed(evt));
        cardPrincipal.add(btnPesquisar);

        // --- BOTÃO NOVO ---
        btnNovo = new JButton("+ Novo pedido") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, 7);
                super.paintComponent(g);
            }
        };
        btnNovo.setBounds(a(10), a(165), a(140), a(30));
        btnNovo.setBackground(new Color(52, 152, 219));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.setOpaque(false);
        btnNovo.setContentAreaFilled(false);
        btnNovo.setFocusPainted(false);
        btnNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UI.arredondar(btnNovo, 7, new Color(52, 152, 219));
        btnNovo.addActionListener(e -> new Cadastrodepedido(this, tipo).setVisible(true)); // passa o tipo
        btnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnNovo.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnNovo.setBackground(new Color(52, 152, 219));
            }
        });

        // --- BOTÃO EXCLUIR ---
        btnExcluir = new JButton("Excluir pedido") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, 7);
                super.paintComponent(g);
            }
        };
        btnExcluir.setBounds(a(170), a(165), a(140), a(30));
        btnExcluir.setBackground(Color.decode("#A60B0B"));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setOpaque(false);
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setFocusPainted(false);
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnExcluir.setBackground(Color.decode("#7A0808"));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnExcluir.setBackground(Color.decode("#A60B0B"));
            }
        });
        UI.arredondar(btnExcluir, 7, Color.decode("#A60B0B"));

        cardPrincipal.add(btnNovo);
        cardPrincipal.add(btnExcluir);

        // Usuário só visualiza, Operador não pode excluir
        if (tipo.equals("Usuário")) {
            btnNovo.setVisible(false);
            btnExcluir.setVisible(false);
        } else if (tipo.equals("Operador")) {
            btnExcluir.setVisible(false);
        }

        // --- TABELA ---
        String[] colunas = {"Item ▼", "Peso (kg) ▼", "Data ▼", "Total (R$) ▼", "Status ▼", "Cliente ▼"};
        model = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(model);
        JTableHeader header = tabela.getTableHeader();
        header.setPreferredSize(new Dimension(a(100), a(35)));
        header.setFont(new Font("Segoe UI", Font.BOLD, a(14)));
        header.setForeground(Color.WHITE);
        header.setBackground(Color.decode("#3C3C46"));

        tabela.setRowHeight(a(30));
        tabela.setShowGrid(true);
        tabela.setGridColor(new Color(200, 200, 200));
        tabela.setShowHorizontalLines(true);
        tabela.setShowVerticalLines(true);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    acaoFinalizarPedido();
                }
            }
        });

        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);

                if (column == 4) {
                    String status = value != null ? value.toString() : "";
                    if (status.equals("CONFIRMADO")) {
                        setBackground(new Color(0x27AE60));
                        setForeground(Color.WHITE);
                        setText("CONFIRMADO");
                    } else {
                        setBackground(new Color(0xF5D97A));
                        setForeground(new Color(0x5C4A00));
                    }
                    setFont(new Font("Roboto", Font.BOLD, a(12)));
                } else {
                    setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                    setFont(table.getFont());
                }
                return this;
            }
        };

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderizador);
        }

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(a(10), a(215), a(1240), a(430));
        cardPrincipal.add(scroll);

        popularTabela();
    }

    public void popularTabela() {
        model.setRowCount(0);
        double somaReceita = 0;
        List<String> listadestatus = new ArrayList<>();
        try {
            linhas = selectpedidos();
            for (Pedidos p : linhas) {
                Object[] linha = {
                    p.getNomeProduto(),
                    p.getPeso(),
                    p.getDataPedidoFormatada(),
                    p.getPrecoTotal(),
                    p.getStatus(),
                    (p.getCliente() != null ? p.getCliente().getNome() : "—")
                };
                if (p.getStatus().equals("PENDENTE")) {
                    listadestatus.add(p.getStatus());
                }
                model.addRow(linha);
                somaReceita += p.getPrecoTotal();
            }
            valor1.setText(String.valueOf(model.getRowCount()));
            valor2.setText(String.valueOf(listadestatus.size()));
            valor4.setText(String.format("%.2f", somaReceita));
        } catch (Exception e) {
            System.err.println("Erro ao popular a tabela: " + e.getMessage());
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int linhaView = tabela.getSelectedRow();
            if (linhaView == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um pedido!");
                return;
            }
            int linhaModel = tabela.convertRowIndexToModel(linhaView);
            Pedidos p = linhas.get(linhaModel);
            new PedidosDAO().delete_pedidos(p);
            linhasConfirmadas.remove(linhaModel);
            linhas = selectpedidos();
            popularTabela();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String parametro = txtpesquisar.getText().trim();
            String ordem = null;
            if (check1.isSelected()) ordem = "DESC";
            if (check2.isSelected()) ordem = "ASC";

            List<Pedidos> resultado = PedidosDAO.selectpedidosOrdenado(ordem);
            model.setRowCount(0);

            for (Pedidos p : resultado) {
                String nomeCliente = (p.getCliente() != null ? p.getCliente().getNome() : "—");
                if (!parametro.isEmpty() && !nomeCliente.equalsIgnoreCase(parametro)) continue;
                model.addRow(new Object[]{
                    p.getNomeProduto(), p.getPeso(),
                    p.getDataPedidoFormatada(), p.getPrecoTotal(),
                    p.getStatus(), nomeCliente
                });
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado");
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar pedidos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar pedidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Pedidos getPedidoSelecionad2() {
        int linhaView = tabela.getSelectedRow();
        if (linhaView == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pedido na tabela.");
            return null;
        }
        int linhaModel = tabela.convertRowIndexToModel(linhaView);
        return linhas.get(linhaModel);
    }

    private void acaoFinalizarPedido() {
        Pedidos p = getPedidoSelecionad2();
        if (p == null) return;

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Finalizar pedido de: " + p.getNomeProduto() + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            linhasConfirmadas.add(tabela.convertRowIndexToModel(tabela.getSelectedRow()));
            tabela.repaint();
            p.getId();
            PedidosDAO peddao1 = new PedidosDAO();
            peddao1.updateStatus(p.getId(), "CONFIRMADO");

            EstoqueDAO DAOs = new EstoqueDAO();
            DAOs.diminuirQuantidade(
                p.getNomeProduto(),
                p.getPeso()
            );
        }
    }

    public static class UI {
        public static class BordaArredondada extends AbstractBorder {
            private final int raio;
            private final Color cor;
            public BordaArredondada(int raio, Color cor) { this.raio = raio; this.cor = cor; }
            @Override
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