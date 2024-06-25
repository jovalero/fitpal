package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;

public class HomeClientePremium extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public HomeClientePremium(Cliente cliente) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + cliente.getNombre() + " " + cliente.getApellido() +". ¿Qué desea hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(43, 10, 691, 31);
		contentPane.add(Bienvenidolabel);

		JButton PerfilButton = new JButton("Ver Perfil");
		PerfilButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		PerfilButton.setBounds(64, 80, 99, 29);
		contentPane.add(PerfilButton);
		
		JButton VerProgresoButton = new JButton("Ver Progresos");
		VerProgresoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		VerProgresoButton.setBounds(64, 120, 135, 29);
		contentPane.add(VerProgresoButton);
		
		JButton AnotarProgresoButton = new JButton("Anotar Progreso");
		AnotarProgresoButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AnotarProgresoButton.setBounds(64, 162, 153, 29);
		contentPane.add(AnotarProgresoButton);
		
		JButton CerrarButton = new JButton("Cerrar Sesion");
		CerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CerrarButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CerrarButton.setBounds(362, 206, 131, 29);
		contentPane.add(CerrarButton);
		
		JButton VerRutinabutton = new JButton("Ver Rutina");
		VerRutinabutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		VerRutinabutton.setBounds(64, 206, 111, 29);
		contentPane.add(VerRutinabutton);
		
		JButton CanjearIncentivo = new JButton("Canjear Incetivos");
		CanjearIncentivo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CanjearIncentivo.setBounds(350, 80, 161, 29);
		contentPane.add(CanjearIncentivo);
		
		JButton btnVerDieta = new JButton("Ver Dieta");
		btnVerDieta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerDieta.setBounds(350, 120, 161, 29);
		contentPane.add(btnVerDieta);
		
		JButton btnVerEntrenador = new JButton("Ver Entrenador");
		btnVerEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerEntrenador.setBounds(350, 162, 161, 29);
		contentPane.add(btnVerEntrenador);
		setContentPane(contentPane);
	}
		

}
