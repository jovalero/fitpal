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
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + administrador.getNombre()+" " + administrador.getApellido() + ". ¿Qué desea hacer?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(99, 11, 599, 38);
		contentPane.add(lblNewLabel);
		
		JButton ClienteButton = new JButton("Sección de Clientes");
		ClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TablaClientes cliente= new TablaClientes(administrador);
				dispose();
			}
		});
		ClienteButton.setBounds(52, 81, 201, 43);
		contentPane.add(ClienteButton);
		
		JButton SeccionEntrenadores = new JButton("Sección de Entrenadores");
		SeccionEntrenadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaEntrenadores entrenadores= new TablaEntrenadores(administrador);
				dispose();
			}
		});
		SeccionEntrenadores.setBounds(52, 135, 201, 43);
		contentPane.add(SeccionEntrenadores);
		
		JButton AreasButton = new JButton("Sección de Áreas");
		AreasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreasTabla area= new AreasTabla(administrador);
				dispose();
			}
		});
		AreasButton.setBounds(52, 189, 201, 38);
		contentPane.add(AreasButton);
		
		JButton btnSeccionProgreso = new JButton("Sección de Progreso");
		btnSeccionProgreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaProgreso area= new TablaProgreso(administrador);
				dispose();
			}
		});
		btnSeccionProgreso.setBounds(52, 287, 201, 38);
		contentPane.add(btnSeccionProgreso);
		
		JButton RutinasButton = new JButton("Sección de Rutinas");
		RutinasButton.setBounds(52, 238, 201, 38);
		contentPane.add(RutinasButton);
		
		JButton SeccionComidaButton = new JButton("Sección de Comida");
        SeccionComidaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComidaTabla comidaTabla = new ComidaTabla(administrador);
                dispose();
            }
        });
        SeccionComidaButton.setBounds(300, 81, 201, 43);
        contentPane.add(SeccionComidaButton);
		
        JButton SeccionEjercicioButton = new JButton("Sección de Ejercicio");
        SeccionEjercicioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EjercicioTabla ejercicioTabla = new EjercicioTabla(administrador);
                dispose();
            }
        });
        SeccionEjercicioButton.setBounds(300, 135, 201, 43);
        contentPane.add(SeccionEjercicioButton);
		
        
        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar sesión y volver a la ventana de inicio de sesión
                Inicio inicio = new Inicio();  // personas es la lista de usuarios
                inicio.setVisible(true);
                dispose();  // Cierra la ventana actual
            }
        });
        botonCerrarSesion.setBounds(500, 300, 130, 23);
        contentPane.add(botonCerrarSesion);

	}
}
