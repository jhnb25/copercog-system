package copercog.view;

import static DAO.ClientesDAO.select_cliente;
import static DAO.CogumelosDAO.retorna_todos_cogumelos;
import DAO.EstoqueDAO;

import DAO.PedidosDAO;
import copercog.model.Clientes;
import copercog.model.Cogumelos;
import copercog.model.Pedidos;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Cadastrodepedido extends JFrame {

    private JPanel painelPrincipal;

    private JLabel lblCliente;
    private JComboBox<Clientes> JCliente;

    private JLabel lblProduto;
    private JComboBox<Cogumelos> JProduto;

    private JLabel lblPeso;
    private JTextField txtPeso;

    private JButton btnCadastrar;
    private JLabel JLabelv;

    private GerenciamentoDePedidos telaPrincipal;
    private String tipo; // ← campo para cascata

    public Cadastrodepedido(GerenciamentoDePedidos telaPrincipal, String tipo) {
        this.telaPrincipal = telaPrincipal;
        this.tipo = tipo;

        setTitle("Cadastro de Pedido");
        setSize(480, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        definir();
    }

    public void definir() {
        int larguraTela = getWidth();
        int alturaTela = getHeight();

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(245, 245, 245));
        painelPrincipal.setBounds(0, 0, larguraTela, alturaTela);
        add(painelPrincipal);

        int cardW = 370;
        int cardH = 360;
        int cardX = (larguraTela - cardW) / 2;
        int cardY = (alturaTela - cardH) / 2;

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(cardX, cardY, cardW, cardH);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        painelPrincipal.add(card);

        int campoW = 310;
        int campoH = 35;
        int labelH = 18;
        int leftX = 30;
        int espCampos = 18;

        // --- Cliente ---
        int y = 25;
        lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(leftX, y, campoW, labelH);
        lblCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCliente.setForeground(new Color(80, 80, 80));
        card.add(lblCliente);

        List<Clientes> listaClientes = select_cliente();
        JCliente = new JComboBox<>(listaClientes.toArray(new Clientes[0]));
        JCliente.setBounds(leftX, y + labelH + 3, campoW, campoH);
        aplicarEstilo(JCliente);
        card.add(JCliente);

        // --- Produto ---
        y = y + labelH + 3 + campoH + espCampos;
        lblProduto = new JLabel("Produto");
        lblProduto.setBounds(leftX, y, campoW, labelH);
        lblProduto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblProduto.setForeground(new Color(80, 80, 80));
        card.add(lblProduto);

        List<Cogumelos> listaCogumelos = retorna_todos_cogumelos();
        JProduto = new JComboBox<>(listaCogumelos.toArray(new Cogumelos[0]));
        JProduto.setBounds(leftX, y + labelH + 3, campoW, campoH);
        aplicarEstilo(JProduto);
        card.add(JProduto);

        // --- Peso ---
        y = y + labelH + 3 + campoH + espCampos;
        lblPeso = new JLabel("Peso");
        lblPeso.setBounds(leftX, y, campoW, labelH);
        lblPeso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPeso.setForeground(new Color(80, 80, 80));
        card.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(leftX, y + labelH + 3, campoW, campoH);
        txtPeso.setBackground(Color.WHITE);
        txtPeso.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        txtPeso.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(txtPeso);

        // --- Botão + Voltar ---
        int btnY = y + labelH + 3 + campoH + 28;

        btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setBounds(leftX, btnY, 140, 36);
        btnCadastrar.setBackground(new Color(123, 97, 255));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        JLabelv = new JLabel("Voltar");
        JLabelv.setBounds(leftX + 140 + 16, btnY + 8, 80, 20);
        JLabelv.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JLabelv.setForeground(Color.BLACK);
        JLabelv.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabelv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
            }
        });
        card.add(JLabelv);
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

private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
    String erros = validarCampos();
    if (!erros.isEmpty()) {
        JOptionPane.showMessageDialog(null, erros, "Erros encontrados", JOptionPane.WARNING_MESSAGE);
        return;
    }
    try {
        Pedidos pedido = montarPedido();
        new PedidosDAO().insert_pedidos(pedido);
        telaPrincipal.atualizarTabela();
        JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Estoque insuficiente", JOptionPane.WARNING_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

  
private Pedidos montarPedido() {
    Clientes c = (Clientes) JCliente.getSelectedItem();
    Cogumelos cg = (Cogumelos) JProduto.getSelectedItem();

    double peso = Double.parseDouble(txtPeso.getText().trim());

    if (!new EstoqueDAO().diminuirQuantidade(cg.getNome(), peso))
        throw new IllegalArgumentException("Quantidade superior ao estoque atual!");

    return new Pedidos(c, cg.getNome(), peso, cg);
}

private String validarCampos() {
    StringBuilder erros = new StringBuilder();

    if (JCliente.getSelectedItem() == null)
        erros.append("Cliente obrigatório\n");

    if (JProduto.getSelectedItem() == null)
        erros.append("Produto obrigatório\n");

    String texto = txtPeso.getText().trim();
    if (texto.isEmpty())
        erros.append("Peso obrigatório\n");
    else if (!texto.matches("\\d+([.,]\\d+)?"))//um ou mais digitos d+   
        erros.append("Peso inválido, digite um número válido\n");
    else if (Double.parseDouble(texto) <= 0)
        erros.append("Peso deve ser maior que zero\n");

    return erros.toString();
}
}