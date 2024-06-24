package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.EjercicioControlador;
import modelo.Admin;
import modelo.Ejercicio;

public class EjercicioTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private EjercicioControlador controlador;
    private Ejercicio ejercicioSeleccionado;
    private Admin administrador;
    private JLabel seleccionadoLabel;

    // Constructor de la clase EjercicioTabla
    public EjercicioTabla(Admin administrador) {
        this.administrador = administrador;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            controlador = new EjercicioControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }
        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        // Definición de las columnas de la tabla
        String[] columnNames = {"ID", "Nombre", "Maquina", "Musculo", "Descripcion", "Video", "ID_Area"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla(); // Cargar los datos iniciales en la tabla
        table.setBounds(10, 37, 532, 204);

        JScrollPane ejercicioTabla = new JScrollPane(table);
        ejercicioTabla.setBounds(0, 37, 911, 190);
        contentPane.add(ejercicioTabla);

        // Etiqueta para mostrar el ejercicio seleccionado
        seleccionadoLabel = new JLabel("Seleccionado: ");
        seleccionadoLabel.setBounds(5, 5, 911, 14);
        contentPane.add(seleccionadoLabel);

        // Botón para eliminar un ejercicio
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ejercicioSeleccionado != null) {
                    controlador.deleteEjercicio(ejercicioSeleccionado.getID_Ejercicio());
                    JOptionPane.showMessageDialog(null, "Ejercicio eliminado");
                    actualizarTabla(); // Actualizar la tabla después de eliminar
                    ejercicioSeleccionado = null;
                    seleccionadoLabel.setText("Seleccionado: ");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un ejercicio");
                }
            }
        });
        btnEliminar.setBounds(55, 257, 187, 58);
        contentPane.add(btnEliminar);

        // Botón para editar un ejercicio
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ejercicioSeleccionado != null) {
                    EditarEjercicio editar = new EditarEjercicio(ejercicioSeleccionado, administrador);
                    editar.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un ejercicio");
                }
            }
        });
        btnEditar.setBounds(308, 257, 166, 58);
        contentPane.add(btnEditar);

        // Botón para registrar un nuevo ejercicio
        JButton secEjercicios = new JButton("Registrar nuevo");
        secEjercicios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarEjercicio registrar = new RegistrarEjercicio(EjercicioTabla.this);
                registrar.setVisible(true);
                dispose();
            }
        });
        secEjercicios.setBounds(575, 257, 166, 58);
        contentPane.add(secEjercicios);

        // Manejador de selección de filas en la tabla
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
                        String maquina = (String) table.getValueAt(selectedRow, 2);
                        String musculo = (String) table.getValueAt(selectedRow, 3);
                        String descripcion = (String) table.getValueAt(selectedRow, 4);
                        String video = (String) table.getValueAt(selectedRow, 5);
                        int idArea = (int) table.getValueAt(selectedRow, 6);
                        seleccionadoLabel.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Máquina=" + maquina + ", Músculo=" + musculo + ", Descripción=" + descripcion);
                        ejercicioSeleccionado = new Ejercicio(id, nombre, maquina, musculo, descripcion, video, idArea);
                    }
                }
            }
        });
        setVisible(true);
    }

    // Método para actualizar la tabla con los datos de la base de datos
    public void actualizarTabla() {
        model.setRowCount(0); // Limpiar la tabla existente
        LinkedList<Ejercicio> ejercicios = controlador.getAllEjercicio(); // Obtener todos los ejercicios
        for (Ejercicio ejercicio : ejercicios) {
            model.addRow(new Object[]{ejercicio.getID_Ejercicio(), ejercicio.getNombre(), ejercicio.getMaquina(), ejercicio.getMusculo(), ejercicio.getDescripcion(), ejercicio.getVideo(), ejercicio.getID_Area()});
        }
    }
}
