package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ComidaControlador;
import modelo.Comida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarComida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField editarcomida;
    private JTextArea descripcionArea;
    private ComidaControlador controlador;
    private Comida comida;
	
	
	public EditarComida(Comida comida) {
		 this.comida = comida;
		 this.setVisible(true);
	        controlador = new ComidaControlador();
	        setTitle("Editar Comida");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(null);
	        
	        JLabel nombreLabel = new JLabel("Nombre:");
	        nombreLabel.setBounds(10, 10, 80, 25);
	        getContentPane().add(nombreLabel);

	        editarcomida = new JTextField(comida.getNombre());
	        editarcomida.setBounds(100, 10, 200, 25);
	        getContentPane().add(editarcomida);

	        JLabel descripcionLabel = new JLabel("Descripción:");
	        descripcionLabel.setBounds(10, 50, 80, 25);
	        getContentPane().add(descripcionLabel);

	        descripcionArea = new JTextArea(comida.getDescripcion());
	        descripcionArea.setBounds(100, 50, 200, 100);
	        getContentPane().add(descripcionArea);

	        JButton editarButton = new JButton("Guardar");
	        editarButton.setBounds(150, 200, 100, 30);
	        getContentPane().add(editarButton);
	        
	        editarButton.addActionListener(new ActionListener() {
	        	 @Override
	             public void actionPerformed(ActionEvent e) {
	                 String nombre = editarcomida.getText();
	                 String descripcion = descripcionArea.getText();

	                 if (nombre.isEmpty() || descripcion.isEmpty()) {
	                     JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
	                 } else {
	                     comida.setNombre(nombre);
	                     comida.setDescripcion(descripcion);
	                     controlador.updateComida(comida);
	                     JOptionPane.showMessageDialog(null, "Comida actualizada exitosamente");
	                     dispose();
	                 }
	             }
	         });
	        }
		}


