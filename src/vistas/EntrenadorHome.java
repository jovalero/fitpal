package vistas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Entrenador;

public class EntrenadorHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public EntrenadorHome(Entrenador entrenador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + entrenador.getNombre() + " " + entrenador.getApellido() +" Que deseas Hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(43, 10, 691, 31);
		contentPane.add(Bienvenidolabel);

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
