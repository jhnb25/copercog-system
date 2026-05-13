package copercog.view;

import DAO.CogumelosDAO;
import DAO.EstoqueDAO;
import static DAO.EstoqueDAO.select_nome;
import static DAO.EstoqueDAO.selectestoque;
import copercog.model.Estoque;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ControleEstoque extends JFrame {

    private JPanel jPanel_1, jPanel_2, jPanel_3;
    private JLabel jLabelTitulo, jLabelas;
    private JTextField txtPesquisar;
    private JTable tabela;
    private JScrollPane scroll;
    private JButton btnMenu, btnExcluir;
    private DefaultTableModel model;
    private double escala;
    List<Estoque> linhas;

    public ControleEstoque(String tipo) {
        setTitle("Controle de Estoque");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        escala = tela.width / 1366.0;
        recarregarDados();
        definir(tipo);
        setVisible(true);
    }

    private int a(int valor) {
        return (int) Math.round(valor * escala);
    }

    private void recarregarDados() {
        List<Estoque> resultado = selectestoque();
        linhas = (resultado != null) ? resultado : new ArrayList<>();
        if (model != null) {
            popularTabela();
        }
    }

    public void definir(String tipo) {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        jPanel_1 = new JPanel();
        jPanel_1.setBounds(0, 0, larguraTela, alturaTela);
        jPanel_1.setBackground(new Color(245, 245, 245));
        jPanel_1.setLayout(null);
        add(jPanel_1);

        jPanel_2 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x4953C3), getWidth(), 0, new Color(0x23285D)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel_2.setBounds(0, 0, larguraTela, a(90));
        jPanel_2.setLayout(null);
        jPanel_2.setBackground(new Color(0x23285D));

        jLabelTitulo = new JLabel(" Controle de estoque ");
        jLabelTitulo.setBounds(a(100), a(18), a(400), a(30));
        jLabelTitulo.setFont(new Font("Roboto", Font.ITALIC, a(22)));
        jLabelTitulo.setForeground(Color.decode("#D7E32D"));
        jPanel_2.add(jLabelTitulo);

        jLabelas = new JLabel("⭣");
        jLabelas.setBounds(a(90), a(18), a(30), a(30));
        jLabelas.setFont(new Font("Roboto", Font.PLAIN, a(26)));
        jLabelas.setForeground(Color.white);
        jPanel_2.add(jLabelas);

        // ===== BOTÃO MENU =====
        btnMenu = new JButton("Menu inicial") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getParent().getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        btnMenu.setBounds(larguraTela - a(186), a(20), a(150), a(30));
        btnMenu.setFont(new Font("Montserrat", Font.PLAIN, a(16)));
        btnMenu.setBackground(Color.decode("#3755EA"));
        btnMenu.setForeground(Color.white);
        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setOpaque(false);
        UI.arredondar(btnMenu, a(12), new Color(0x1A6FA0));
        btnMenu.addActionListener(e -> {
            new Gerenciamentodeprodutos(tipo).setVisible(true); // passa o tipo
            dispose();
        });
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnMenu.setBackground(Color.WHITE);
                btnMenu.setForeground(Color.decode("#3755EA"));
                btnMenu.setBorder(new UI.BordaArredondada(a(12), Color.decode("#3755EA")));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnMenu.setBackground(Color.decode("#3755EA"));
                btnMenu.setForeground(Color.WHITE);
                btnMenu.setBorder(new UI.BordaArredondada(a(12), new Color(0x1A6FA0)));
            }
        });
        jPanel_2.add(btnMenu);

        
        Border bordaEntalhada = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        // ===== BOTÃO EXCLUIR =====
        btnExcluir = new JButton("Excluir item") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, a(12));
                super.paintComponent(g);
            }
        };
        btnExcluir.setBounds(larguraTela - a(486), a(60), a(150), a(30));
        btnExcluir.setFont(new Font("Montserrat", Font.PLAIN, a(16)));
        btnExcluir.setBackground(Color.decode("#A60B0B"));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setOpaque(false);
        btnExcluir.setContentAreaFilled(false);

         btnExcluir.setBorder(bordaEntalhada);
        
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
  
        btnExcluir.addActionListener(e -> btnExcluirActionPerformed(e));
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnExcluir.setBackground(Color.decode("#7A0808"));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnExcluir.setBackground(Color.decode("#A60B0B"));
            }
        });

        // Operador e Usuário não podem excluir do estoque
        if (!tipo.equals("Administrador")) {
            btnExcluir.setVisible(false);
        }

        jPanel_2.add(btnExcluir);

        JPanel capsula = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), a(30), a(30));
            }
        };
        capsula.setLayout(null);
        capsula.setOpaque(false);
        capsula.setBackground(new Color(255, 255, 255, 40));
        capsula.setBounds(a(80), a(12), a(360), a(42));
        jPanel_2.add(capsula);

        // ===== BOTÃO PESQUISAR =====
        JButton btnPesquisar = new JButton("Buscar") {
            @Override
            protected void paintComponent(Graphics g) {
                UI.pintarFundo(this, g, 25);
                super.paintComponent(g);
            }
        };
        btnPesquisar.setBounds(a(745), a(62), a(60), a(25));
        btnPesquisar.setBackground(new Color(0x3755EA));
        btnPesquisar.setForeground(Color.WHITE);
        btnPesquisar.setFont(new Font("Segoe UI", Font.BOLD, a(11)));
        btnPesquisar.setOpaque(false);
        btnPesquisar.setContentAreaFilled(false);
        btnPesquisar.setFocusPainted(false);
        btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        UI.arredondar(btnPesquisar, 25, new Color(0x3755EA));
        btnPesquisar.addActionListener(e -> btnPesquisarActionPerformed(e));
        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnPesquisar.setBackground(new Color(0x2A40C0));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnPesquisar.setBackground(new Color(0x3755EA));
            }
        });
        jPanel_2.add(btnPesquisar);

        txtPesquisar = new JTextField("Nome..") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getParent().getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                UI.pintarFundo(this, g, 7);
                super.paintComponent(g);
            }
        };
        txtPesquisar.setForeground(Color.black);
        txtPesquisar.setBounds(a(490), a(62), a(250), a(25));
        txtPesquisar.setToolTipText("Digite o nome do produto para buscar");
        UI.arredondar(txtPesquisar, 7, Color.GRAY);
        jPanel_2.add(txtPesquisar);

        jPanel_1.add(jPanel_2);

        jPanel_3 = new JPanel();
        jPanel_3.setBounds(0, a(70), larguraTela, alturaTela - a(70));
        jPanel_3.setBackground(new Color(245, 245, 245));
        jPanel_3.setLayout(null);

        String[] colunas = {"nome", "tipo", "peso atual", "(R$)preço por kg"};
        model = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(model);
        tabela.setRowHeight(a(30));
        tabela.setShowGrid(true);
        tabela.setGridColor(new Color(200, 200, 200));
        tabela.setShowHorizontalLines(true);
        tabela.setShowVerticalLines(true);
        tabela.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = tabela.getTableHeader();
        header.setPreferredSize(new Dimension(a(100), a(35)));
        header.setFont(new Font("Montserrat", Font.BOLD, a(14)));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(63, 81, 181));

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        scroll = new JScrollPane(tabela);
        scroll.setBounds(a(20), a(50), larguraTela - a(40), alturaTela - a(150));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel_3.add(scroll);
        jPanel_1.add(jPanel_3);

        popularTabela();
    }

    public void popularTabela() {
        model.setRowCount(0);
        for (Estoque e : linhas) {
            model.addRow(new Object[]{
                e.getProduto().getNome(),
                e.getProduto().getTipo(),
                e.getPesoAtual(),
                e.getProduto().getPrecoBase()
            });
        }
    }

    //além disso tem que excluir cogumelos
    private void btnExcluirActionPerformed(ActionEvent evt) {
        int linhaView = tabela.getSelectedRow();
        if (linhaView == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto!");
            return;
        }
        int linhaModel = tabela.convertRowIndexToModel(linhaView);
        Estoque e = linhas.get(linhaModel);
        EstoqueDAO dao = new EstoqueDAO();
        CogumelosDAO dao2= new CogumelosDAO();
      //daqui pra baixop precisa do try catch pra pegar aquele runtime
          dao.excluirEstoque(e);              
    dao2.delete_cogumelos(e.getProduto());
        recarregarDados();
    }

    private void btnPesquisarActionPerformed(ActionEvent evt) {
        String parametro = txtPesquisar.getText();
        String define = select_nome(parametro);
        if (define != null) {
            for (int i = 0; i < tabela.getRowCount(); i++) {
                String valorCelula = tabela.getValueAt(i, 0).toString();
                if (valorCelula.equalsIgnoreCase(define)) {
                    tabela.setRowSelectionInterval(i, i);
                    Rectangle rect = tabela.getCellRect(i, 0, true);
                    Dimension areaVisivel = tabela.getParent().getSize();
                    rect.y = rect.y - (areaVisivel.height / 2);
                    rect.height = areaVisivel.height;
                    tabela.scrollRectToVisible(rect);
                    tabela.requestFocus();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque", "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static class UI {
        public static class BordaArredondada extends AbstractBorder {
            private final int raio;
            private final Color cor;

            public BordaArredondada(int raio, Color cor) {
                this.raio = raio;
                this.cor = cor;
            }

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