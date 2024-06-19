package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ComidaControlador;
import modelo.Admin;
import modelo.Comida;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarComida extends JFrame {

	private static final long serialVersionUID = 1L;
	 private JTextField registrarcomida;
	 private JTextArea descripcionArea;
	 private ComidaControlador controlador;

	public RegistrarComida(ComidaTabla comidaTabla , Admin Administrador) {
		 controlador = new ComidaControlador();
	        setTitle("Registrar Nueva Comida");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(null);
	        
	        JLabel nombreLabel = new JLabel("Nombre:");
	        nombreLabel.setBounds(10, 10, 80, 25);
	        getContentPane().add(nombreLabel);

	        registrarcomida = new JTextField();
	        registrarcomida.setBounds(100, 10, 200, 25);
	        getContentPane().add(registrarcomida);

	        JLabel descripcionLabel = new JLabel("Descripción:");
	        descripcionLabel.setBounds(10, 50, 80, 25);
	        getContentPane().add(descripcionLabel);

	        descripcionArea = new JTextArea();
	        descripcionArea.setBounds(100, 50, 200, 100);
	        getContentPane().add(descripcionArea);

	        JButton registrarButton = new JButton("Registrar");
	        registrarButton.setBounds(150, 200, 100, 30);
	        getContentPane().add(registrarButton);
	        
	        registrarButton.addActionListener(new ActionListener() {
	        	  @Override
	              public void actionPerformed(ActionEvent e) {
	                  String nombre = registrarcomida.getText();
	                  String descripcion = descripcionArea.getText();

	                  if (nombre.isEmpty() || descripcion.isEmpty()) {
	                      JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
	                  } else {
	                      Comida nuevaComida = new Comida(nombre, descripcion, 0);
	                      controlador.addComida(nuevaComida);
	                      JOptionPane.showMessageDialog(null, "Comida registrada exitosamente");
	                      dispose();
	                      ComidaTabla tabla = new ComidaTabla(Administrador);
	                  }
	        	  }
	        	});
	        }
		}
