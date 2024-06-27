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
    private JTextField textFieldFiltro;

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

        JLabel seleccionadoLabel = new JLabel("SECCIÓN DE COMIDA");
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

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comidaSeleccionada != null) {
                    EditarComida editar = new EditarComida(comidaSeleccionada, administrador);
                    editar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una comida");
                }
            }
        });

        JButton seccomidas = new JButton("Registrar nuevo");
        seccomidas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarComida registrar = new RegistrarComida(administrador);
                registrar.setVisible(true);
            }
        });

        JButton Volverbutton = new JButton("Volver al menú");
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador);
                dispose();
            }
        });

        // Añadir los botones de manera simétrica
        int buttonWidth = 166;
        int buttonHeight = 58;
        int spacing = (945 - (buttonWidth * 4)) / 5; // Calcula el espacio entre botones y bordes

        btnEliminar.setBounds(spacing, 257, buttonWidth, buttonHeight);
        btnEditar.setBounds(spacing * 2 + buttonWidth, 257, buttonWidth, buttonHeight);
        seccomidas.setBounds(spacing * 3 + buttonWidth * 2, 257, buttonWidth, buttonHeight);
        Volverbutton.setBounds(spacing * 4 + buttonWidth * 3, 257, buttonWidth, buttonHeight);

        contentPane.add(btnEliminar);
        contentPane.add(btnEditar);
        contentPane.add(seccomidas);
        contentPane.add(Volverbutton);

        JLabel lblFiltro = new JLabel("Buscador:");
        lblFiltro.setBounds(20, 31, 67, 20);
        contentPane.add(lblFiltro);

        textFieldFiltro = new JTextField();
        textFieldFiltro.setBounds(85, 31, 126, 20);
        contentPane.add(textFieldFiltro);
        textFieldFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(227, 29, 81, 25);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Filtrar(textFieldFiltro.getText());
            }
        });
        contentPane.add(btnFiltrar);

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

