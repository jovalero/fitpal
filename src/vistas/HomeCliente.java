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

public class HomeCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeCliente(Cliente cliente) {
		if (cliente.getEstado_sus().equalsIgnoreCase("Nuevo")) {
			new FormularioClienteNuevo(cliente);
			dispose();
		}
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + cliente.getNombre() + " " + cliente.getApellido() +". ¿Qué desea hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(51, 11, 770, 64);
		contentPane.add(Bienvenidolabel);
		
		JButton PerfilButton = new JButton("Ver Perfil");
		PerfilButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		PerfilButton.setBounds(84, 86, 147, 53);
		contentPane.add(PerfilButton);
		
		JButton CambiarRutinaButton = new JButton("Cambiar Rutina");
		CambiarRutinaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CambiarRutinaButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CambiarRutinaButton.setBounds(84, 161, 147, 53);
		contentPane.add(CambiarRutinaButton);
		
		JButton VerProgresoButton = new JButton("Ver Progresos");
		VerProgresoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		VerProgresoButton.setBounds(84, 232, 147, 53);
		contentPane.add(VerProgresoButton);
		
		JButton AnotarProgresoButton = new JButton("Anotar Progreso");
		AnotarProgresoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AnotarProgresoButton.setBounds(427, 86, 179, 53);
		contentPane.add(AnotarProgresoButton);
		
		JButton CerrarButton = new JButton("Cerrar Sesion");
		CerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CerrarButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CerrarButton.setBounds(427, 232, 179, 53);
		contentPane.add(CerrarButton);
		
		JButton VerRutinabutton = new JButton("Ver Rutina");
		VerRutinabutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		VerRutinabutton.setBounds(427, 161, 147, 53);
		contentPane.add(VerRutinabutton);
	}
}
