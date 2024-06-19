package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import javax.swing.JLabel;
import java.awt.Font;

public class HomeCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeCliente(Cliente cliente) {
		if (cliente.getEstado_sus("Nuevo")) {
			
		}
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + cliente.getNombre() + " " + cliente.getApellido() +" Que deseas Hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(51, 11, 770, 64);
		contentPane.add(Bienvenidolabel);
	}

}
