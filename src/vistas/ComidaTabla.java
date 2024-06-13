package vistas;

import controlador.ComidaControlador;
import modelo.Admin;
import modelo.Comida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ComidaTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ComidaControlador controlador;
    private Comida comidaSeleccionada;

    /**
     * Create the frame.
     */
    public ComidaTabla(Admin Administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       
        setContentPane(contentPane);

        try {
            controlador = new ComidaControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }
        
        Comida seleccionado = new Comida(); 
        
        String[] columnNames = {"ID", "Nombre", "Descripción"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        table.setBounds(10, 37, 532, 204);

        JScrollPane comidatabla = new JScrollPane(table);
        comidatabla.setBounds(0, 37, 911, 190);
        contentPane.add(comidatabla);

        JLabel seleccionadoLabel = new JLabel("Seleccionado: ");
        seleccionadoLabel.setBounds(5, 5, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comidaSeleccionada != null) {
                    controlador.deleteComida(comidaSeleccionada.getID_Comida());
                    JOptionPane.showMessageDialog(null, "Comida eliminada");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una comida");
                }
            }
        });
        btnEliminar.setBounds(55, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comidaSeleccionada != null) {
                    EditarComida editar = new EditarComida(comidaSeleccionada, ComidaTabla.this);
                    editar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una comida");
                }
            }
        });
        btnEditar.setBounds(308, 257, 166, 58);
        contentPane.add(btnEditar);

        JButton seccomidas = new JButton("Registrar nuevo");
        seccomidas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarComida registrar = new RegistrarComida(ComidaTabla.this);
                registrar.setVisible(true);
            }
        });
        seccomidas.setBounds(571, 257, 166, 58);
        contentPane.add(seccomidas);

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
                        String descripcion = (String) table.getValueAt(selectedRow, 2);
                        seleccionadoLabel.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Descripción=" + descripcion);
                        comidaSeleccionada = new Comida(nombre, descripcion, id);
                    }
                }
            }
        });
    }

    public void actualizarTabla() {
        model.setRowCount(0);
        LinkedList<Comida> comidas = controlador.getAllComidas();
        for (Comida comida : comidas) {
            model.addRow(new Object[]{comida.getID_Comida(), comida.getNombre(), comida.getDescripcion()});
        }
    }
}