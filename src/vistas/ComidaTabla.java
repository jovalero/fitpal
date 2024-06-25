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
import java.util.List;

public class ComidaTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ComidaControlador controlador;
    private Comida comidaSeleccionada;
    private JTextField textField;

    public ComidaTabla(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

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

        String[] columnNames = {"ID", "Nombre", "Descripción"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        table.setBounds(10, 37, 532, 204);

        JScrollPane comidatabla = new JScrollPane(table);
        comidatabla.setBounds(0, 73, 921, 173);
        contentPane.add(comidatabla);

        JLabel seleccionadoLabel = new JLabel("Seleccionado: ");
        seleccionadoLabel.setBounds(10, 11, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comidaSeleccionada != null) {
                    controlador.deleteComida(comidaSeleccionada.getID_Comida());
                    JOptionPane.showMessageDialog(null, "Comida eliminada");
                    actualizarTabla();
                    comidaSeleccionada = null;
                    seleccionadoLabel.setText("Seleccionado: ");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una comida");
                }
            }
        });
        btnEliminar.setBounds(36, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comidaSeleccionada != null) {
                    EditarComida editar = new EditarComida(comidaSeleccionada,administrador);
                    editar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una comida");
                }
            }
        });
        btnEditar.setBounds(292, 257, 166, 58);
        contentPane.add(btnEditar);

        JButton seccomidas = new JButton("Registrar nuevo");
        seccomidas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarComida registrar = new RegistrarComida(administrador);
                registrar.setVisible(true);
            }
        });
        seccomidas.setBounds(496, 257, 166, 58);
        contentPane.add(seccomidas);

        textField = new JTextField();
        textField.setBounds(97, 36, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel filtro = new JLabel("Buscador:");
        filtro.setBounds(20, 36, 67, 14);
        contentPane.add(filtro);

        JButton filtrar = new JButton("Filtrar");
        filtrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Filtrar(textField.getText());
            }
        });
        filtrar.setBounds(206, 36, 89, 23);
        contentPane.add(filtrar);

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

    private void Filtrar(String criterio) {
        model.setRowCount(0);
        List<Comida> comidas = controlador.getAllComidas();
        String criterioLower = criterio.toLowerCase();

        for (Comida comida : comidas) {
            if (comida.getNombre().toLowerCase().contains(criterioLower)) {
                model.addRow(new Object[]{comida.getID_Comida(), comida.getNombre(), comida.getDescripcion()});
            }
        }
    }
}