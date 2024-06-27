package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.AreasControlador;
import modelo.Admin;
import modelo.Areas;

public class AreasTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private AreasControlador controlador;
    private JLabel Seleccionadolabel;

    public AreasTabla(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            controlador = new AreasControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        Areas seleccionado = new Areas();

        String[] columnNames = { "ID_Areas", "Nombre", "ID_Sucursal", "Ubicacion" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 56, 911, 190);
        contentPane.add(scrollPane);

        Seleccionadolabel = new JLabel("Seleccionado:");
        Seleccionadolabel.setBounds(5, 5, 911, 14);
        contentPane.add(Seleccionadolabel);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }
        });
        btnAgregar.setBounds(60, 257, 166, 58);
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }
        });
        btnEditar.setBounds(268, 257, 166, 58);
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }
        });
        btnEliminar.setBounds(475, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton Volverbutton = new JButton("Volver al menú");
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador);
                dispose();
            }
        });
        Volverbutton.setBounds(700, 257, 166, 58);
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
                        int ID_Sucursal = (int) table.getValueAt(selectedRow, 2);
                        String ubicacion = (String) table.getValueAt(selectedRow, 3);

                        Seleccionadolabel.setText("Seleccionado: ID Area=" + id + ", Nombre=" + nombre + ", ID Sucursal=" + ID_Sucursal + ", Ubicacion=" + ubicacion);
                        seleccionado.setIdArea(id);
                        seleccionado.setNombre(nombre);
                        seleccionado.setIdSucursal(ID_Sucursal);
                        seleccionado.setUbicacion(ubicacion);
                    }
                }
            }
        });
    }

    private void agregarSucursal() {
        String id = JOptionPane.showInputDialog("Ingresa ID");
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre");
        String ID_Sucursal = JOptionPane.showInputDialog("Ingresa la sucursal");
        String ubicacion = JOptionPane.showInputDialog("Ingresa la ubicación");

        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error al agregar el área: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Object[]{Integer.parseInt(id), nombre, Integer.parseInt(ID_Sucursal), ubicacion});
        }
    }

    private void editarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = JOptionPane.showInputDialog("Editar ID", model.getValueAt(selectedRow, 0));
            String nombre = JOptionPane.showInputDialog("Editar el nombre", model.getValueAt(selectedRow, 1));
            String ID_Sucursal = JOptionPane.showInputDialog("Ingresa la sucursal", model.getValueAt(selectedRow, 2));
            String ubicacion = JOptionPane.showInputDialog("Editar la ubicación", model.getValueAt(selectedRow, 3));

            if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || ID_Sucursal == null || ID_Sucursal.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error al editar el área: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                model.setValueAt(Integer.parseInt(id), selectedRow, 0);
                model.setValueAt(nombre, selectedRow, 1);
                model.setValueAt(Integer.parseInt(ID_Sucursal), selectedRow, 2);
                model.setValueAt(ubicacion, selectedRow, 3);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un área para editar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void borrarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un área para borrar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actualizarTabla(int sucursal) {
        model.setRowCount(0);
        List<Areas> areas = controlador.getallAreasbySucursal(sucursal);
        for (Areas area : areas) {
            model.addRow(new Object[]{area.getIdArea(), area.getNombre(), area.getIdSucursal(), area.getUbicacion()});
        }
    }
}
