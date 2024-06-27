package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import modelo.Cliente;

public class Misincentivos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel Puntos;

    public Misincentivos(Cliente cliente) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btncanjear = new JButton("Comprar Puntos");
        btncanjear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarTabladeCanje MostrarTabladeCanje=new MostrarTabladeCanje();
            	dispose();
            }
        });
        btncanjear.setBounds(42, 210, 120, 23);
        contentPane.add(btncanjear);

        JButton btnatras = new JButton("Atras");
        btnatras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	HomeClientePremium HomeClientePremium=new HomeClientePremium(cliente);
        		dispose();
            }
        });
        btnatras.setBounds(278, 210, 120, 23);
        contentPane.add(btnatras);

        JLabel TusPuntos = new JLabel("Tus Puntos:");
        TusPuntos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TusPuntos.setBounds(169, 11, 127, 47);
        contentPane.add(TusPuntos);

        Puntos = new JLabel("");
        Puntos.setFont(new Font("Tahoma", Font.PLAIN, 21));
        Puntos.setBounds(128, 69, 170, 69);
        contentPane.add(Puntos);

        // Establece el texto del JLabel Puntos con los puntos del cliente
        Puntos.setText(String.valueOf(cliente.getPuntos()));
    }
}

