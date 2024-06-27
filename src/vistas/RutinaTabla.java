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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controlador.RutinaControlador;
import modelo.Admin;
import modelo.Rutina;

public class RutinaTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private RutinaControlador controlador;
    private JTextField textFieldFiltro;
    private Rutina rutinaSeleccionada; // Asumiendo que Rutina tiene los métodos getIdRutina(), getEstado(), getDescripcion(), getObjetivo()

    public RutinaTabla(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);

        try {
            controlador = new RutinaControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        rutinaSeleccionada = new Rutina(0, "", "", "",""); // Revisar que los argumentos aquí coincidan con el constructor de Rutina

        String[] columnNames = { "ID Rutina", "Estado", "Descripción", "Objetivo" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 56, 911, 190);
        contentPane.setLayout(null);
        contentPane.add(scrollPane);

        JLabel lblFiltro = new JLabel("Buscador:");
        lblFiltro.setBounds(20, 36, 67, 14);
        contentPane.add(lblFiltro);

        textFieldFiltro = new JTextField();
        textFieldFiltro.setBounds(97, 33, 100, 20);
        contentPane.add(textFieldFiltro);
        textFieldFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(207, 32, 89, 23);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrar(textFieldFiltro.getText());
            }
        });
        contentPane.add(btnFiltrar);

        JLabel seleccionadoLabel = new JLabel("Seleccionado:");
        seleccionadoLabel.setBounds(5, 257, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rutinaSeleccionada != null && rutinaSeleccionada.getIdRutina() != 0) {
                    int idRutina = rutinaSeleccionada.getIdRutina();
                    controlador.deleteRutina(idRutina);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(null, "Rutina eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una rutina");
                }
            }
        });

        btnEliminar.setBounds(475, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rutinaSeleccionada.getIdRutina() != 0) {
                    // Crear una instancia de EditarRutina pasando el controlador, la rutina seleccionada y RutinaTabla actual
                    new EditarRutina(controlador, rutinaSeleccionada, RutinaTabla.this).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una rutina");
                }
            }
        });
        btnEditar.setBounds(268, 257, 166, 58);
        contentPane.add(btnEditar);

        JButton Volverbutton = new JButton("Volver a menú");
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador).setVisible(true);
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
                        int idRutina = (int) table.getValueAt(selectedRow, 0);
                        String estado = (String) table.getValueAt(selectedRow, 1);
                        String descripcion = (String) table.getValueAt(selectedRow, 2);
                        String objetivo = (String) table.getValueAt(selectedRow, 3);
                        seleccionadoLabel.setText("Seleccionado: ID Rutina=" + idRutina + ", Estado=" + estado
                                + ", Descripción=" + descripcion + ", Objetivo=" + objetivo);
                        rutinaSeleccionada.setIdRutina(idRutina);
                        rutinaSeleccionada.setEstado(estado);
                        rutinaSeleccionada.setDescripcion(descripcion);
                        rutinaSeleccionada.setObjetivo(objetivo);
                    }
                }
            }
        });
    }

    // Cambios en el método actualizarTabla() y filtrar() para usar List en lugar de LinkedList

    public void actualizarTabla() {
        model.setRowCount(0);
        List<Rutina> rutinas = controlador.getAllRutinas(); // Cambiar aquí a List<Rutina>
        for (Rutina rutina : rutinas) {
            model.addRow(new Object[] { rutina.getIdRutina(), rutina.getEstado(), rutina.getDescripcion(),
                    rutina.getObjetivo() });
        }
    }

    private void filtrar(String criterio) {
        model.setRowCount(0);
        List<Rutina> rutinas = controlador.getAllRutinas(); // Cambiar aquí a List<Rutina>
        String criterioLower = criterio.toLowerCase();

        for (Rutina rutina : rutinas) {
            if (rutina.getDescripcion().toLowerCase().contains(criterioLower)
                    || rutina.getEstado().toLowerCase().contains(criterioLower)) {
                model.addRow(new Object[] { rutina.getIdRutina(), rutina.getEstado(), rutina.getDescripcion(),
                        rutina.getObjetivo() });
            }
        }
    }
}
