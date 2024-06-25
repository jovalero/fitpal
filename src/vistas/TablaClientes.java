package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import controlador.ProgresoControlador;
import modelo.Admin;
import modelo.Cliente;
import modelo.Progreso;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
	private ProgresoControlador progreso;
	private Cliente clienteaeditar;
	 private JTextField textBuscar;




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
		        progreso=new ProgresoControlador();
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
    				LinkedList<Progreso> progresoscliente= progreso.getAllProgresos();
					for (Progreso progresoc : progresoscliente) {
						if (progresoc.getIdCliente()==seleccionado.getId_cliente()) {
							progreso.deleteProgreso(progresoc.getIdProgreso());
						}
					}
        			controlador.deleteCliente(seleccionado.getId_cliente());;
        			JOptionPane.showMessageDialog(null, "Elimnado");
        			 actualizarTabla(administrador.getId_sucursal());
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario");
				}
        	
        	}
        });
        btnEliminar.setBounds(31, 257, 187, 58);
        contentPane.add(btnEliminar);
        
        JButton Editar = new JButton("Editar");
        Editar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (seleccionado.getId_cliente()!=0) {
        			LinkedList<Cliente> clientes=controlador.getAllClientesBySucursal(administrador.getId_sucursal());
        			for (Cliente cliente : clientes) {
						if (seleccionado.getId_cliente()==cliente.getId_cliente()) {
							clienteaeditar=cliente;
						}
					}
        			EditarCliente editar = new EditarCliente(clienteaeditar,administrador);
        			dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario");
				}
        		
        	}
        });
        Editar.setBounds(256, 257, 166, 58);
        contentPane.add(Editar);
        
        JButton Registrarbutton = new JButton("Registrar nuevo");
        Registrarbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegistrarCliente registrar=new RegistrarCliente(administrador);
        		dispose();
        	}
        });
        Registrarbutton.setBounds(465, 257, 166, 58);
        contentPane.add(Registrarbutton);
        
        JButton Volverbutton = new JButton("Volver a menú");
        Volverbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new HomeAdmin(administrador);
        		dispose();
        	}
        });
        Volverbutton.setBounds(667, 257, 166, 58);
        contentPane.add(Volverbutton);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String nombre = (String) table.getValueAt(selectedRow, 1);
                        String apellido = (String) table.getValueAt(selectedRow, 2);
                        int DNI = (int) table.getValueAt(selectedRow, 3);
                        String mail = (String) table.getValueAt(selectedRow, 4);
                        Seleccionadolabel.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Mail=" + mail + ", Apellido=" + apellido+ ", DNI=" + DNI );
                        seleccionado.setUsuario(mail);
                        seleccionado.setNombre(nombre);
                        seleccionado.setId_cliente(id);
                        seleccionado.setApellido(apellido);
                        seleccionado.setDNI(DNI);
                    }
                }
            }
        });
        JLabel lblBuscar = new JLabel("Buscador:");
        lblBuscar.setBounds(10, 237, 58, 14);
        contentPane.add(lblBuscar);

        textBuscar = new JTextField();
        textBuscar.setBounds(78, 234, 204, 20);
        contentPane.add(textBuscar);
        textBuscar.setColumns(10);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarClientes(textBuscar.getText(), administrador.getId_sucursal());
            }
        });
        btnBuscar.setBounds(303, 233, 89, 23);
        contentPane.add(btnBuscar);
	}
	public void actualizarTabla(int sucursal) {
		
		model.setRowCount(0);
		
		LinkedList<Cliente> clientes=controlador.getAllClientesBySucursal(sucursal);
		
		
		for (Cliente cliente : clientes) {
			model.addRow(new Object[] {cliente.getId_cliente(),cliente.getNombre(),cliente.getApellido(),cliente.getDNI(),cliente.getUsuario()});
		}
		
	}
	 private void buscarClientes(String termino, int sucursal) {
	        model.setRowCount(0);
	        LinkedList<Cliente> clientes = controlador.getAllClientesBySucursal(sucursal);

	        for (Cliente cliente : clientes) {
	            if ((cliente.getNombre().toLowerCase().contains(termino.toLowerCase()) ||
	                 cliente.getApellido().toLowerCase().contains(termino.toLowerCase())) && String.valueOf(cliente.getDNI()).contains(termino.toLowerCase())) 
	                {
	    			model.addRow(new Object[] {cliente.getId_cliente(),cliente.getNombre(),cliente.getApellido(),cliente.getDNI(),cliente.getUsuario()});
	            }
	        }
	    }
}
