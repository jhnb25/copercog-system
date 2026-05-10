package copercog.view;
import DAO.ClientesDAO;

import copercog.model.Clientes;
import copercog.model.ENDERECO_DIGITAL;
import copercog.model.Endereco_Fisico;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;

public class CadastrodeClientes extends JFrame {

    private JPanel jPanel_1, jPanel_2, jPanel_3, jPanel_5, jPanel_6, jPanel_7, jPanel_8,jPanel1Layout,jPanel1;
    private JLabel jLabel_1, jlbldadospessoais, jlblnome, jlblsexo, jlbldatadenascimento, jlblcpf;
    private JLabel jlblenderecofisico, jlblpais, jlblestado, jlblcidade, jlblbairro, jlblrua, jlblcep;
    private JLabel jlblcontato, jlblemailprincipal, jlblemailopcional, jlbtelefone, jlblcadastro;
    private JSeparator jSeparator_1, jSeparator_2, jSeparator_3, jSeparator_4, jSeparator_5, jSeparator_6;
    private JRadioButton jrbMasculino, jrbFeminino, jrbOutro;
    private ButtonGroup gruposexo;
    private JButton jbtncriar, jbtnlimpar;
    private JTextField txtNome, txtDataNascimento, txtCPF;
    private JTextField txtEmailPrincipal, txtEmailOpcional, txtTelefone;
    private JTextField txtPais, txtEstado, txtCidade, txtBairro, txtRua, txtCEP;
    private JLabel labelEmailIcone;
    private JSeparator jSeparator_12, jSeparator_13;
    private String tipo; // ← campo para cascata
    private double escala;

    public CadastrodeClientes(String tipo) {
        this.tipo = tipo;
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        escala = tela.width / 1366.0;

        definir();

        jbtncriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtncriarActionPerformed(evt);
            }
        });

        jbtnlimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnlimparActionPerformed(evt);
            }
        });

        // Usuário só visualiza — sem cadastro
        if (tipo.equals("Usuário")) {
            jbtncriar.setVisible(false);
            jbtnlimpar.setVisible(false);
        }
    }

    private int a(int valor) {
        return (int) Math.round(valor * escala);
    }

    public void carregarIcone() {
        try {
            java.net.URL urlImagem = getClass().getResource("/copercog/resources/pngtree-location-icon-png-image_6672610.png");
            if (urlImagem == null) {
                throw new IllegalArgumentException("Imagem não encontrada no caminho especificado.");
            }
            ImageIcon icon = new ImageIcon(urlImagem);
            java.awt.Image imgRedimensionada = icon.getImage().getScaledInstance(a(35), a(35), java.awt.Image.SCALE_SMOOTH);
            JLabel labelComImagem = new JLabel(new ImageIcon(imgRedimensionada));
            labelComImagem.setBounds(a(15), a(50), a(40), a(40));
            jPanel_5.add(labelComImagem);
            jPanel_5.revalidate();
            jPanel_5.repaint();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro específico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar imagem: " + e.getMessage());
        }
    }

    public void definir() {

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        // ===== PAINEL PRINCIPAL
        jPanel_1 = new JPanel();
        jPanel_1.setBounds(0, 0, larguraTela, alturaTela);
        jPanel_1.setBackground(new Color(245, 245, 245));
        jPanel_1.setLayout(null);
        add(jPanel_1);

        jPanel_2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x4953C3), getWidth(), 0, new Color(0x23285D)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 80));
                g2d.setFont(new Font("Roboto", Font.BOLD, a(26)));
                g2d.drawString("Cadastro de cliente", a(489) + 2, a(17) + a(26) + 2);
                g2d.setColor(Color.WHITE);
                g2d.drawString("Cadastro de cliente", a(489), a(17) + a(26));
            }
        };
        jPanel_2.setBounds(0, 0, larguraTela, a(70));
        jPanel_2.setLayout(null);

        jLabel_1 = new JLabel("Cadastro de cliente");
        jLabel_1.setBounds(a(489), a(17), a(400), a(35));
        jLabel_1.setFont(new Font("Roboto", Font.BOLD, a(26)));
        jLabel_1.setForeground(Color.WHITE);
        jPanel_2.add(jLabel_1);
        jPanel_1.add(jPanel_2);

        // ===== DADOS PESSOAIS (Painel 3)
        jPanel_3 = new JPanel();
        jPanel_3.setBounds(a(20), a(80), a(1312), a(210));
        jPanel_3.setBackground(Color.WHITE);
        jPanel_3.setLayout(null);

        JPanel pnlHeaderDP = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x4953C3), getWidth(), 0, new Color(0x23285D)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        pnlHeaderDP.setBounds(0, 0, a(1312), a(40));
        pnlHeaderDP.setLayout(null);
        jlbldadospessoais = new JLabel("Dados pessoais");
        jlbldadospessoais.setBounds(a(20), a(10), a(220), a(22));
        jlbldadospessoais.setFont(new Font("Source Serif Pro", Font.BOLD, a(20)));
        jlbldadospessoais.setForeground(Color.WHITE);
        pnlHeaderDP.add(jlbldadospessoais);
        jPanel_3.add(pnlHeaderDP);

        jlblnome = new JLabel("Nome:");
        jlblnome.setBounds(a(200), a(50), a(80), a(20));
        jlblnome.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblnome.setForeground(new Color(0x004A8D));
        jPanel_3.add(jlblnome);

        txtNome = new JTextField();
        txtNome.setBounds(a(260), a(48), a(500), a(25));
        txtNome.setText("Nome completo");
        jPanel_3.add(txtNome);

        jSeparator_1 = new JSeparator();
        jSeparator_1.setBounds(a(200), a(75), a(900), a(2));
        jPanel_3.add(jSeparator_1);

        jlblsexo = new JLabel("Sexo | >", SwingConstants.CENTER);
        jlblsexo.setForeground(new Color(70, 130, 180));
        jlblsexo.setBackground(Color.WHITE);
        jlblsexo.setOpaque(true);
        jlblsexo.setFont(new Font("Source Serif Pro", Font.BOLD, a(15)));
        jlblsexo.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), a(2)));
        jlblsexo.setBounds(a(160), a(80), a(110), a(30));
        jPanel_3.add(jlblsexo);

        gruposexo = new ButtonGroup();
        jrbMasculino = new JRadioButton("masculino");
        jrbMasculino.setBounds(a(280), a(85), a(110), a(20));
        jrbMasculino.setBackground(Color.WHITE);
        jrbMasculino.setActionCommand("M");
        gruposexo.add(jrbMasculino);
        jPanel_3.add(jrbMasculino);

        jrbFeminino = new JRadioButton("feminino");
        jrbFeminino.setBounds(a(390), a(85), a(100), a(20));
        jrbFeminino.setBackground(Color.WHITE);
        jrbFeminino.setActionCommand("F");
        gruposexo.add(jrbFeminino);
        jPanel_3.add(jrbFeminino);

        jrbOutro = new JRadioButton("outro");
        jrbOutro.setBounds(a(495), a(85), a(80), a(20));
        jrbOutro.setBackground(Color.WHITE);
        jrbOutro.setActionCommand("OUTRO");
        gruposexo.add(jrbOutro);
        jPanel_3.add(jrbOutro);

        jSeparator_2 = new JSeparator();
        jSeparator_2.setBounds(a(200), a(112), a(900), a(2));
        jPanel_3.add(jSeparator_2);

        jlbldatadenascimento = new JLabel("Data de nascimento:");
        jlbldatadenascimento.setBounds(a(80), a(120), a(200), a(20));
        jlbldatadenascimento.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlbldatadenascimento.setForeground(new Color(0x004A8D));
        jPanel_3.add(jlbldatadenascimento);

        txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(a(260), a(118), a(500), a(25));
        txtDataNascimento.setText("dd/MM/yyyy");
        jPanel_3.add(txtDataNascimento);

        jSeparator_3 = new JSeparator();
        jSeparator_3.setBounds(a(20), a(145), a(1200), a(2));
        jPanel_3.add(jSeparator_3);

        jlblcpf = new JLabel("CPF:");
        jlblcpf.setBounds(a(217), a(155), a(80), a(20));
        jlblcpf.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblcpf.setForeground(new Color(0x004A8D));
        jPanel_3.add(jlblcpf);

        txtCPF = new JTextField();
        txtCPF.setBounds(a(260), a(153), a(500), a(25));
        txtCPF.setText("11 digitos sem pontos");
        jPanel_3.add(txtCPF);

        jSeparator_4 = new JSeparator();
        jSeparator_4.setBounds(a(20), a(180), a(1270), a(2));
        jPanel_3.add(jSeparator_4);

        jPanel_1.add(jPanel_3);

        // ===== CONTATO (Painel 7)
        jPanel_7 = new JPanel();
        jPanel_7.setBounds(a(20), a(300), a(1312), a(150));
        jPanel_7.setBackground(Color.WHITE);
        jPanel_7.setLayout(null);

        jPanel_8 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x4953C3), getWidth(), 0, new Color(0x23285D)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel_8.setBounds(0, 0, a(1312), a(40));
        jPanel_8.setLayout(null);
        jlblcontato = new JLabel("Contato");
        jlblcontato.setBounds(a(22), a(10), a(200), a(22));
        jlblcontato.setFont(new Font("Source Serif Pro", Font.BOLD, a(20)));
        jlblcontato.setForeground(Color.WHITE);
        jPanel_8.add(jlblcontato);
        jPanel_7.add(jPanel_8);

        labelEmailIcone = new JLabel("📧");
        labelEmailIcone.setBounds(a(10), a(62), a(50), a(65));
        labelEmailIcone.setFont(new Font("Serif", Font.PLAIN, a(40)));
        labelEmailIcone.setForeground(Color.blue);
        jPanel_7.add(labelEmailIcone);

        jlblemailprincipal = new JLabel("E-mail principal:");
        jlblemailprincipal.setBounds(a(110), a(50), a(180), a(20));
        jlblemailprincipal.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblemailprincipal.setForeground(new Color(0x004A8D));
        jPanel_7.add(jlblemailprincipal);

        txtEmailPrincipal = new JTextField();
        txtEmailPrincipal.setBounds(a(260), a(48), a(500), a(25));
        txtEmailPrincipal.setText("usuario@email.com");
        jPanel_7.add(txtEmailPrincipal);

        jSeparator_5 = new JSeparator();
        jSeparator_5.setBounds(a(120), a(75), a(1200), a(2));
        jPanel_7.add(jSeparator_5);

        jlblemailopcional = new JLabel("E-mail (opcional):");
        jlblemailopcional.setBounds(a(100), a(82), a(190), a(20));
        jlblemailopcional.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblemailopcional.setForeground(new Color(0x004A8D));
        jPanel_7.add(jlblemailopcional);

        txtEmailOpcional = new JTextField();
        txtEmailOpcional.setBounds(a(260), a(80), a(500), a(25));
        txtEmailOpcional.setText("opcional");
        jPanel_7.add(txtEmailOpcional);

        jSeparator_6 = new JSeparator();
        jSeparator_6.setBounds(a(120), a(107), a(1200), a(2));
        jPanel_7.add(jSeparator_6);

        jlbtelefone = new JLabel("Telefone:");
        jlbtelefone.setBounds(a(170), a(115), a(120), a(20));
        jlbtelefone.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlbtelefone.setForeground(new Color(0x004A8D));
        jPanel_7.add(jlbtelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(a(260), a(113), a(500), a(25));
        txtTelefone.setText("DDD + numero");
        jPanel_7.add(txtTelefone);

        jPanel_1.add(jPanel_7);

        // ===== ENDEREÇO (Painel 5)
        jPanel_5 = new JPanel();
        jPanel_5.setBounds(a(20), a(460), a(1312), a(130));
        jPanel_5.setBackground(Color.WHITE);
        jPanel_5.setLayout(null);

        jPanel_6 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x4953C3), getWidth(), 0, new Color(0x23285D)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel_6.setBounds(0, 0, a(1312), a(40));
        jPanel_6.setLayout(null);
        jlblenderecofisico = new JLabel("Endereço Físico");
        jlblenderecofisico.setBounds(a(22), a(10), a(220), a(22));
        jlblenderecofisico.setFont(new Font("Source Serif Pro", Font.BOLD, a(20)));
        jlblenderecofisico.setForeground(Color.WHITE);
        jPanel_6.add(jlblenderecofisico);
        jPanel_5.add(jPanel_6);

        jlblpais = new JLabel("Pais:");
        jlblpais.setBounds(a(205), a(50), a(80), a(20));
        jlblpais.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblpais.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblpais);

        txtPais = new JTextField();
        txtPais.setBounds(a(250), a(48), a(200), a(25));
        txtPais.setText("Pais");
        jPanel_5.add(txtPais);

        jlblestado = new JLabel("Estado:");
        jlblestado.setBounds(a(500), a(50), a(100), a(20));
        jlblestado.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblestado.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblestado);

        txtEstado = new JTextField();
        txtEstado.setBounds(a(570), a(48), a(200), a(25));
        txtEstado.setText("Estado");
        jPanel_5.add(txtEstado);

        jlblcep = new JLabel("CEP:");
        jlblcep.setBounds(a(900), a(50), a(48), a(24));
        jlblcep.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblcep.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblcep);

        txtCEP = new JTextField();
        txtCEP.setBounds(a(944), a(48), a(200), a(25));
        txtCEP.setText("8 digitos");
        jPanel_5.add(txtCEP);

        jSeparator_12 = new JSeparator();
        jSeparator_12.setBounds(a(100), a(78), a(1200), a(2));
        jPanel_5.add(jSeparator_12);

        jlblcidade = new JLabel("Cidade:");
        jlblcidade.setBounds(a(180), a(90), a(100), a(20));
        jlblcidade.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblcidade.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblcidade);

        txtCidade = new JTextField();
        txtCidade.setBounds(a(250), a(88), a(200), a(25));
        txtCidade.setText("Cidade");
        jPanel_5.add(txtCidade);

        jlblbairro = new JLabel("Bairro:");
        jlblbairro.setBounds(a(510), a(90), a(100), a(20));
        jlblbairro.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblbairro.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblbairro);

        txtBairro = new JTextField();
        txtBairro.setBounds(a(570), a(88), a(200), a(25));
        txtBairro.setText("Bairro");
        jPanel_5.add(txtBairro);

        jlblrua = new JLabel("Rua:");
        jlblrua.setBounds(a(902), a(90), a(48), a(24));
        jlblrua.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));
        jlblrua.setForeground(new Color(0x004A8D));
        jPanel_5.add(jlblrua);

        txtRua = new JTextField();
        txtRua.setBounds(a(944), a(88), a(200), a(25));
        txtRua.setText("Rua");
        jPanel_5.add(txtRua);

        jSeparator_13 = new JSeparator();
        jSeparator_13.setBounds(a(100), a(115), a(1200), a(2));
        jPanel_5.add(jSeparator_13);

        jPanel_1.add(jPanel_5);

        // ===== BOTÕES
        jbtncriar = new JButton("Criar");
        jbtncriar.setBounds(a(580), a(600), a(120), a(35));
        jbtncriar.setBackground(new Color(63, 81, 181));
        jbtncriar.setForeground(Color.WHITE);
        jbtncriar.setFont(new Font("Source Serif Pro", Font.BOLD, a(16)));
        jbtncriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtncriar.setBorder(BorderFactory.createLineBorder(new Color(0x4953C3), a(2)));
        jPanel_1.add(jbtncriar);

        jbtnlimpar = new JButton("Limpar");
        jbtnlimpar.setBounds(a(580), a(645), a(120), a(35));
        jbtnlimpar.setFont(new Font("Kufam", Font.BOLD, a(16)));
        jbtnlimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbtnlimpar.setBorder(new LineBorder(Color.BLACK, 1, true));
        jPanel_1.add(jbtnlimpar);

        carregarIcone();
    }

   private void jbtncriarActionPerformed(java.awt.event.ActionEvent evt) {
    String erros = validarCampos();
    if (!erros.isEmpty()) {
        JOptionPane.showMessageDialog(null, erros, "Erros encontrados", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        String nome = txtNome.getText().trim();
        String cpf = txtCPF.getText().trim();
        String dataStr = txtDataNascimento.getText().trim();
        String email = txtEmailPrincipal.getText().trim();
        String emailop = txtEmailOpcional.getText().trim();
        if (emailop.isEmpty()) {
            emailop = null;
        }
        String telefone = txtTelefone.getText().trim();
        String pais = txtPais.getText().trim();
        String uf = txtEstado.getText().trim();
        String cidade = txtCidade.getText().trim();
        String bairro = txtBairro.getText().trim();
        String rua = txtRua.getText().trim();
        String cep = txtCEP.getText().trim();

        if (gruposexo.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Selecione o sexo.");
            return;
        }

        String sexo = gruposexo.getSelection().getActionCommand();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtnasc = LocalDate.parse(dataStr, fmt);

        Clientes cliente = new Clientes(nome, cpf, sexo, dtnasc);
        Endereco_Fisico ef = new Endereco_Fisico(pais, uf, cidade, bairro, rua, cep, cliente);
        ENDERECO_DIGITAL ed = new ENDERECO_DIGITAL(email, emailop, telefone, cliente);

        cliente.setEnderecoFisico(ef);
        cliente.setEnderecoDigital(ed);

        new ClientesDAO().salvarClienteCompleto(cliente);

        if (jlblcadastro != null) {
            jPanel_1.remove(jlblcadastro);
        }

        jlblcadastro = new JLabel("Usuário cadastrado com sucesso ✓");
        jlblcadastro.setBounds(a(530), a(600), a(365), a(35));
        jlblcadastro.setForeground(new Color(0, 128, 0));
        jlblcadastro.setFont(new Font("Source Serif Pro", Font.BOLD, a(18)));

        jPanel_1.add(jlblcadastro);
        jbtncriar.setVisible(false);
        jPanel_1.revalidate();
        jPanel_1.repaint();

        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
            jPanel_1.remove(jlblcadastro);
            jlblcadastro = null;
            jbtncriar.setVisible(true);
            jPanel_1.revalidate();
            jPanel_1.repaint();
        });

        timer.setRepeats(false);
        timer.start();

    } catch (Exception e) {
        Throwable c = e.getCause();
        if (c instanceof org.hibernate.exception.ConstraintViolationException ex) {
            String name = ex.getConstraintName();
            if (name != null && name.contains(".")) {
                name = name.substring(name.lastIndexOf('.') + 1);
            }
            if ("uq_EMAIL".equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Email principal já cadastrado.");
            } else if ("uq_EMAIL_OPCIONAL".equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Email opcional já cadastrado.");
            } else if ("uq_TELEFONE".equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Telefone já cadastrado.");
            } else {
                JOptionPane.showMessageDialog(null, "Dado duplicado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        jbtncriar.setVisible(true);
    }
}

    private void jbtnlimparActionPerformed(java.awt.event.ActionEvent evt) {
        txtNome.setText("");
        txtDataNascimento.setText("");
        txtCPF.setText("");
        txtEmailPrincipal.setText("");
        txtEmailOpcional.setText("");
        txtTelefone.setText("");
        txtPais.setText("");
        txtEstado.setText("");
        txtCidade.setText("");
        txtBairro.setText("");
        txtRua.setText("");
        txtCEP.setText("");
        gruposexo.clearSelection();

        if (jlblcadastro != null) {
            jPanel_1.remove(jlblcadastro);
            jlblcadastro = null;
        }

        jbtncriar.setVisible(true);
        jPanel_1.revalidate();
        jPanel_1.repaint();
    }

private String validarCampos() {
    StringBuilder erros = new StringBuilder();

    String nome = txtNome.getText().trim();
    String data = txtDataNascimento.getText().trim();
    String cpf = txtCPF.getText().trim();
    String email = txtEmailPrincipal.getText().trim();
    String emailOpcional = txtEmailOpcional.getText().trim();
    String telefone = txtTelefone.getText().trim();
    String pais = txtPais.getText().trim();
    String estado = txtEstado.getText().trim();
    String cidade = txtCidade.getText().trim();
    String bairro = txtBairro.getText().trim();
    String rua = txtRua.getText().trim();
    String cep = txtCEP.getText().trim();

    if (nome.isEmpty()) {
        erros.append("• Nome obrigatório.\n");
    } else if (!nome.matches("^[a-zA-ZÀ-ÿ\\s]{2,}$")) {
        erros.append("• Nome inválido.\n");
    }

    if (data.isEmpty()) {
        erros.append("• Data obrigatória.\n");
    } else if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
        erros.append("• Data inválida.\n");
    }

    if (!jrbMasculino.isSelected() && !jrbFeminino.isSelected() && !jrbOutro.isSelected()) {
        erros.append("• Selecione o sexo.\n");
    }

    if (cpf.isEmpty()) {
        erros.append("• CPF obrigatório.\n");
    } else if (!cpf.matches("\\d{11}")) {
        erros.append("• CPF deve ter 11 dígitos.\n");
    }

    if (email.isEmpty()) {
        erros.append("• Email obrigatório.\n");
    } else if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
        erros.append("• Email inválido.\n");
    }

    if (!emailOpcional.isEmpty()) {
        if (!emailOpcional.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            erros.append("• Email opcional está com formato inválido.\n");
        }
    }

    if (telefone.isEmpty()) {
        erros.append("• Telefone obrigatório.\n");
    } else if (!telefone.matches("\\d{10,13}")) {
        erros.append("• Telefone inválido.\n");
    }

    if (pais.isEmpty()) erros.append("• País obrigatório.\n");
    if (estado.isEmpty()) erros.append("• Estado obrigatório.\n");
    if (cidade.isEmpty()) erros.append("• Cidade obrigatória.\n");
    if (bairro.isEmpty()) erros.append("• Bairro obrigatório.\n");
    if (rua.isEmpty()) erros.append("• Rua obrigatória.\n");

    if (cep.isEmpty()) {
        erros.append("• CEP obrigatório.\n");
    } else if (!cep.matches("\\d{8}")) {
        erros.append("• CEP inválido deve conter 8 dígitos.\n");
    }

    return erros.toString();
}
}