package vistas;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import modelo.Admin;
import modelo.Cliente;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class TablaClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ClienteControlador controlador;




	/**
	 * Create the frame.
	 */
	public TablaClientes(Admin administrador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		   try {
		        controlador = new ClienteControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String [] columnames= {"ID","Nombre","Apellido","DNI","Mail"};
		model= new DefaultTableModel(columnames,0);
		table = new JTable(model);
		table.setBounds(10, 37, 532, 204);
		contentPane.add(table);
		
		JLabel Seleccionadolabel = new JLabel("New label");
		Seleccionadolabel.setBounds(10, 23, 532, 14);
		contentPane.add(Seleccionadolabel);
	}
	public void actualizarTabla() {
		
		model.setRowCount(0);
		
		LinkedList<Cliente> clientes=controlador.getAllClientes();
		
		for (Cliente cliente : clientes) {
			model.addRow(new Object[] {cliente.getId_cliente(),cliente.getNombre(),cliente.getApellido(),cliente.getDNI(),cliente.getUsuario()});
		}
		
	}
}
