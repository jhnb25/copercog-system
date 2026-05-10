package copercog.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class TelaConfirmacao extends JFrame {

    private JPanel jPanel_1;
    private JLabel jlbltitulo, jlblmensagem, jlblsubtexto, jlblcheck;
    private JButton jbtnOK;

    public TelaConfirmacao() {
        setSize(500, 350);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);

        jPanel_1 = new JPanel();
        jPanel_1.setBounds(0, 0, 500, 350);
        jPanel_1.setBackground(Color.WHITE);
        jPanel_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        jPanel_1.setLayout(null);
        add(jPanel_1);

        jlbltitulo = new JLabel("Confirmação");
        jlbltitulo.setBounds(185, 20, 200, 30);
        jlbltitulo.setFont(new Font("SansSerif", Font.PLAIN, 22));
        jPanel_1.add(jlbltitulo);

        jlblmensagem = new JLabel("Produto cadastrado com sucesso");
        jlblmensagem.setBounds(100, 100, 300, 30);
        jlblmensagem.setFont(new Font("SansSerif", Font.PLAIN, 18));
        jPanel_1.add(jlblmensagem);

        jlblcheck = new JLabel("✓");
        jlblcheck.setBounds(400, 100, 30, 30);
       jlblcheck.setFont(new Font("SansSerif", Font.BOLD, 26));
        jlblcheck.setForeground(new Color(0x32CD32));
        jPanel_1.add(jlblcheck);

        jlblsubtexto = new JLabel("Você será redirecionado a tela inicial");
        jlblsubtexto.setBounds(110, 150, 300, 25);
        jlblsubtexto.setFont(new Font("SansSerif", Font.PLAIN, 16));
        jPanel_1.add(jlblsubtexto);

        jbtnOK = new JButton("OK");
        jbtnOK.setBounds(190, 220, 120, 40);
        jbtnOK.setBackground(new Color(0x2ECC71));
        jbtnOK.setForeground(Color.WHITE);
        jbtnOK.setFont(new Font("SansSerif", Font.BOLD, 16));
        jbtnOK.setFocusPainted(false);
        jbtnOK.setBorder(null);
         jbtnOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanel_1.add(jbtnOK);

        jbtnOK.addActionListener(e -> dispose());
        
        
        
        
    }
    
    
    
    
    
    
}