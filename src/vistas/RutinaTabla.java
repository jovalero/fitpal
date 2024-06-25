package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.RutinaControlador;
import modelo.Admin;
import modelo.Areas;
import modelo.Rutina;

public class RutinaTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private RutinaControlador controlador;

	
	public RutinaTabla(Admin Administrador) {
		this.setVisible(true);
		controlador = new RutinaControlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		model = new DefaultTableModel(
        		new String[] {
                        "ID_Rutina", "Estado", "Descripción", "Objetivo"
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

        JButton btnAdd = new JButton("Agregar Área");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }

			
        });
        btnAdd.setBounds(56, 345, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Área");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }

        });
        btnEdit.setBounds(227, 345, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Área");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }

			
        });
        btnDelete.setBounds(406, 345, 150, 30);
        contentPane.add(btnDelete);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
	}
	
	
	
public void actualizarTabla(int sucursal) {
		
		model.setRowCount(0);
		
		List<Rutina> rutinas=controlador.getallRutinabySucursal(sucursal);
		
		for (Rutina rutina : rutinas) {
			model.addRow(new Object[] {rutina.getIdRutina(),rutina.getEstado(),rutina.getDescripcion(),rutina.getObjetivo()});
		}
		
	}



	
	private void agregarSucursal() {
		// TODO Auto-generated method stub
		
	}

	private void editarSucursal() {
		// TODO Auto-generated method stub
		
	}
	private void borrarSucursal() {
		// TODO Auto-generated method stub
		
	}
}
