package vistas;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.AreasControlador;
import modelo.Admin;
import modelo.Areas;
import modelo.Cliente;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AreasTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private AreasControlador controlador;
   
    
    public AreasTabla(Admin Administrador) {
		this.setVisible(true);
		controlador = new AreasControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 436); // Adjust height for buttons
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Areas seleccionado = new Areas();

        

        model = new DefaultTableModel(
        		new String[] {
                        "ID_Areas", "Nombre", "ID_Sucursal", "Ubicacion"
                    },0     
        );

        table = new JTable(model);
        actualizarTabla(Administrador.getId_sucursal());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(32, 23, 587, 311); // Adjust for buttons
        contentPane.add(scrollPane);

		JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
		Seleccionadolabel.setBounds(5, 5, 911, 14);
		contentPane.add(Seleccionadolabel);

        JButton btnAdd = new JButton("Agregar");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }
        });
        btnAdd.setBounds(56, 345, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }
        });
        btnEdit.setBounds(227, 345, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }
        });
        btnDelete.setBounds(406, 345, 150, 30);
        contentPane.add(btnDelete);
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
                        int ID_Sucursal = (int ) table.getValueAt(selectedRow, 2);
                        String Ubicacion = (String) table.getValueAt(selectedRow, 3);
                       
                        Seleccionadolabel.setText("Seleccionado: ID Area=" + id + ", Nombre=" + nombre + ", ID Sucursal=" + ID_Sucursal + ", Ubicacion=" + Ubicacion);
                        seleccionado.setIdArea(id);
                        seleccionado.setNombre(nombre);
                        seleccionado.setIdSucursal(ID_Sucursal);
                        seleccionado.setUbicacion(Ubicacion);
                    }
                }
            }
        });

    }

    private void agregarSucursal() {
        String id = JOptionPane.showInputDialog("Ingresa ID");
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre");
        String ID_Sucursal= JOptionPane.showInputDialog("Ingresa la sucursal"); 
        String ubicacion = JOptionPane.showInputDialog("Ingresa la ubicación");
        
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() ||  ubicacion == null || ubicacion.trim().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Error al agregar el área: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Object[]{id, nombre,ID_Sucursal ,ubicacion});
        }
    }

    private void editarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = JOptionPane.showInputDialog("Editar ID", model.getValueAt(selectedRow, 0));
            String nombre = JOptionPane.showInputDialog("Editar el nombre", model.getValueAt(selectedRow, 1));
            String ID_Sucursal= JOptionPane.showInputDialog("Ingresa la sucursal", model.getValueAt(selectedRow,2 )); 
            String ubicacion = JOptionPane.showInputDialog("Editar la ubicación", model.getValueAt(selectedRow, 3));
            
            if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error al editar la sucursal: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                model.setValueAt(id, selectedRow, 0);
                model.setValueAt(nombre, selectedRow, 1);
                model.setValueAt(ID_Sucursal, selectedRow, 2);
                model.setValueAt(ubicacion, selectedRow, 3);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una sucursal para editar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void borrarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una sucursal para borrar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
public void actualizarTabla(int sucursal) {
		
		model.setRowCount(0);
		
		List<Areas> areas=controlador.getallAreasbySucursal(sucursal);
		
		for (Areas area : areas) {
			model.addRow(new Object[] {area.getIdArea(),area.getNombre(),area.getIdSucursal(),area.getUbicacion()});
		}
		
	}
}