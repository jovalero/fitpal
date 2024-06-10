package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
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
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;



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
		setBounds(100, 100, 945, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
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
		Cliente seleccionado= new Cliente();
		
		String [] columnames= {"ID","Nombre","Apellido","DNI","Mail"};
		model= new DefaultTableModel(columnames,0);
		table = new JTable(model);
		actualizarTabla(administrador.getId_sucursal());
		table.setBounds(10, 37, 532, 204);
		contentPane.setLayout(null);
		
		
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 37, 911, 190);
        contentPane.add(scrollPane);

		
		JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
		Seleccionadolabel.setBounds(5, 5, 911, 14);
		contentPane.add(Seleccionadolabel);
		
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (seleccionado.getId_cliente()!=0) {
					
        			controlador.deleteCliente(seleccionado.getId_cliente());;
        			JOptionPane.showMessageDialog(null, "Elimnado");
        			 actualizarTabla(administrador.getId_sucursal());
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario");
				}
        	
        	}
        });
        btnEliminar.setBounds(55, 257, 187, 58);
        contentPane.add(btnEliminar);
        
        JButton Editar = new JButton("Editar");
        Editar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (seleccionado.getId_cliente()!=0) {
					
        			EditarCliente editar = new EditarCliente(seleccionado);
        			dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario");
				}
        		
        	}
        });
        Editar.setBounds(334, 257, 166, 58);
        contentPane.add(Editar);
	}
	public void actualizarTabla(int sucursal) {
		
		model.setRowCount(0);
		
		LinkedList<Cliente> clientes=controlador.getAllClientesBySucursal(sucursal);
		
		for (Cliente cliente : clientes) {
			model.addRow(new Object[] {cliente.getId_cliente(),cliente.getNombre(),cliente.getApellido(),cliente.getDNI(),cliente.getUsuario()});
		}
		
	}
}
