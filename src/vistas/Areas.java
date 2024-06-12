package vistas;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Areas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Areas frame = new Areas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Areas() {
		this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 612, 399); // Adjust height for buttons
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 592, 300); // Adjust for buttons
        contentPane.add(scrollPane);
        

        model = new DefaultTableModel(
            new Object[][] {
                {"1", "Cardio", "Sucursal 1", "Planta Baja"},
                {"2", "Pesas", "Sucursal 1", "Primer Piso"},
                {"3", "Crossfit", "Sucursal 1", "Primer Piso"},
                {"4", "Funcional", "Sucursal 1", "Primer Piso"},
                {"5", "Pileta", "Sucursal 1", "Planta Baja"},

              
            },
            new String[] {
                "ID_Areas", "Nombre", "ID_Sucursal", "Ubicacion"
            }
        );
        table = new JTable(model);
        scrollPane.setViewportView(table);

        JButton btnAdd = new JButton("Agregar Sucursal");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }
        });
        btnAdd.setBounds(10, 322, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Sucursal");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }
        });
        btnEdit.setBounds(170, 322, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Sucursal");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }
        });
        btnDelete.setBounds(330, 322, 150, 30);
        contentPane.add(btnDelete);
    }

    private void agregarSucursal() {
        String id = JOptionPane.showInputDialog("Ingresa ID");
        String nombre = JOptionPane.showInputDialog("Ingresa Nombre");
        String ID_Sucursal= JOptionPane.showInputDialog("Ingresa la Sucursal"); 
        String ubicacion = JOptionPane.showInputDialog("Ingresa Ubicacion");
        
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() ||  ubicacion == null || ubicacion.trim().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Error al agregar el Area: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Object[]{id, nombre,ID_Sucursal ,ubicacion});
        }
    }

    private void editarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = JOptionPane.showInputDialog("Editar ID", model.getValueAt(selectedRow, 0));
            String nombre = JOptionPane.showInputDialog("Editar Nombre", model.getValueAt(selectedRow, 1));
            String ID_Sucursal= JOptionPane.showInputDialog("Ingresa la Sucursal", model.getValueAt(selectedRow,2 )); 
            String ubicacion = JOptionPane.showInputDialog("Editar Ubicacion", model.getValueAt(selectedRow, 3));
            
            if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error al editar sucursal: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
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
}