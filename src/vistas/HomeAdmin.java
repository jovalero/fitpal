package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Admin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeAdmin(Admin administrador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + administrador.getNombre()+" " + administrador.getApellido() + " Que desea hacer: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 599, 38);
		contentPane.add(lblNewLabel);
		
		JButton ClienteButton = new JButton("Seccion Clientes");
		ClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TablaClientes cliente= new TablaClientes(administrador);
				dispose();
			}
		});
		ClienteButton.setBounds(52, 60, 201, 43);
		contentPane.add(ClienteButton);
		
		JButton SeccionEntrenadores = new JButton("Seccion Entrenadores");
		SeccionEntrenadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SeccionEntrenadores.setBounds(52, 127, 201, 43);
		contentPane.add(SeccionEntrenadores);
		
		JButton AreasButton = new JButton("Seccion Areas");
		AreasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreasTabla area= new AreasTabla(administrador);
				dispose();
			}
		});
		AreasButton.setBounds(52, 218, 201, 38);
		contentPane.add(AreasButton);
		
		JButton RutinasButton = new JButton("Seccion Rutinas");
		RutinasButton.setBounds(52, 272, 201, 38);
		contentPane.add(RutinasButton);
	}
}
