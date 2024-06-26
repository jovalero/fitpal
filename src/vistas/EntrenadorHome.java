package vistas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Entrenador;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntrenadorHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public EntrenadorHome(Entrenador entrenador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + entrenador.getNombre() + " " + entrenador.getApellido() +" Que deseas Hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(43, 10, 691, 31);
		contentPane.add(Bienvenidolabel);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton PerfilButton = new JButton("Ver Perfil");
		PerfilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PerfilButton.setBounds(132, 79, 148, 52);
		contentPane.add(PerfilButton);
		
		JButton RutinasButton = new JButton("Gestionar Rutinas");
		RutinasButton.setBounds(446, 79, 148, 52);
		contentPane.add(RutinasButton);
		
		JButton btnGestionarDietas = new JButton("Gestionar Dietas");
		btnGestionarDietas.setBounds(446, 172, 148, 52);
		contentPane.add(btnGestionarDietas);
		
		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setBounds(132, 172, 148, 52);
		contentPane.add(btnGestionarClientes);
	}
}
