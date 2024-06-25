package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Cliente;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeClientePremium extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public HomeClientePremium(Cliente cliente) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 673, 393);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        JLabel Bienvenidolabel = new JLabel("Bienvenido " + cliente.getNombre() + " " + cliente.getApellido() + ". ¿Qué desea hacer?");
        Bienvenidolabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Bienvenidolabel.setBounds(99, 11, 599, 38);
        contentPane.add(Bienvenidolabel);

        JButton PerfilButton = new JButton("Ver perfil");
        PerfilButton.setBounds(52, 81, 201, 43);
        contentPane.add(PerfilButton);

        JButton VerProgresoButton = new JButton("Ver progresos");
        VerProgresoButton.setBounds(52, 135, 201, 43);
        contentPane.add(VerProgresoButton);

        JButton AnotarProgresoButton = new JButton("Anotar progreso");
        AnotarProgresoButton.setBounds(52, 189, 201, 43);
        contentPane.add(AnotarProgresoButton);

        JButton VerRutinabutton = new JButton("Ver rutina");
        VerRutinabutton.setBounds(52, 243, 201, 43);
        contentPane.add(VerRutinabutton);

        JButton CanjearIncentivo = new JButton("Canjear incentivos");
        CanjearIncentivo.setBounds(300, 81, 201, 43);
        contentPane.add(CanjearIncentivo);

        JButton btnVerDieta = new JButton("Ver dietas");
        btnVerDieta.setBounds(300, 135, 201, 43);
        contentPane.add(btnVerDieta);

        JButton btnVerEntrenador = new JButton("Ver entrenador");
        btnVerEntrenador.setBounds(300, 189, 201, 43);
        contentPane.add(btnVerEntrenador);

        JButton CerrarButton = new JButton("Cerrar sesión");
        CerrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para CerrarButton
            }
        });
        CerrarButton.setBounds(300, 243, 201, 43);
        contentPane.add(CerrarButton);

        setContentPane(contentPane);
    }
}

