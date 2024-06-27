package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
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
import controlador.ProgresoControlador;
import modelo.Admin;
import modelo.Progreso;

public class TablaProgreso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ProgresoControlador controlador;
    private JTextField textFieldFiltro;
    private Progreso seleccionado;

    public TablaProgreso(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            controlador = new ProgresoControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        seleccionado = new Progreso(0, 0, null, "", 0.0);

        String[] columnNames = { "ID Progreso", "ID Cliente", "Fecha", "Imagen", "Peso" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 73, 921, 173);
        contentPane.add(scrollPane);
        
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
                filtrar(textFieldFiltro.getText());
            }
        });
        contentPane.add(btnFiltrar);

        JLabel seleccionadoLabel = new JLabel("SECCIÓN DE PROGRESO");
        seleccionadoLabel.setBounds(10, 11, 911, 14);
        contentPane.add(seleccionadoLabel);

        // Añadir los botones de manera simétrica
        int buttonWidth = 166;
        int buttonHeight = 58;
        int spacing = (945 - (buttonWidth * 4)) / 5; // Calcula el espacio entre botones y bordes

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(spacing, 257, buttonWidth, buttonHeight);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarProgreso agregarProgreso = new AgregarProgreso(controlador, TablaProgreso.this);
                agregarProgreso.setVisible(true);
            }
        });
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(spacing * 2 + buttonWidth, 257, buttonWidth, buttonHeight);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdProgreso() != 0) {
                    new EditarProgreso(controlador, seleccionado, TablaProgreso.this).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un progreso");
                }
            }
        });
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(spacing * 3 + buttonWidth * 2, 257, buttonWidth, buttonHeight);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null && seleccionado.getIdProgreso() != 0) {
                    int idProgreso = seleccionado.getIdProgreso();
                    controlador.deleteProgreso(idProgreso);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(null, "Progreso eliminado");
                    seleccionado = new Progreso(0, 0, null, "", 0.0);
                    seleccionadoLabel.setText("Seleccionado:");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un progreso");
                }
            }
        });
        contentPane.add(btnEliminar);

        JButton Volverbutton = new JButton("Volver al menú");
        Volverbutton.setBounds(spacing * 4 + buttonWidth * 3, 257, buttonWidth, buttonHeight);
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador);
                dispose();
            }
        });
        contentPane.add(Volverbutton);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int idProgreso = (int) table.getValueAt(selectedRow, 0);
                        int idCliente = (int) table.getValueAt(selectedRow, 1);
                        String fecha = (String) table.getValueAt(selectedRow, 2);
                        String imagen = (String) table.getValueAt(selectedRow, 3);
                        double peso = (double) table.getValueAt(selectedRow, 4);
                        seleccionadoLabel.setText("Seleccionado: ID Progreso=" + idProgreso + ", ID Cliente=" + idCliente + ", Fecha=" + fecha + ", Imagen=" + imagen + ", Peso=" + peso);
                        seleccionado.setIdProgreso(idProgreso);
                        seleccionado.setIdCliente(idCliente);
                        seleccionado.setFecha(LocalDate.parse(fecha));
                        seleccionado.setImagen(imagen);
                        seleccionado.setPeso(peso);
                    }
                }
            }
        });
    }

    public void actualizarTabla() {
        model.setRowCount(0); 
        LinkedList<Progreso> progresos = controlador.getAllProgresos();
        for (Progreso progreso : progresos) {
            model.addRow(new Object[] { 
                progreso.getIdProgreso(), 
                progreso.getIdCliente(), 
                progreso.getFecha().toString(), 
                progreso.getImagen(), 
                progreso.getPeso() 
            });
        }
    }
    
    private void filtrar(String criterio) {
        model.setRowCount(0);
        LinkedList<Progreso> progresos = controlador.getAllProgresos();
        String criterioLower = criterio.toLowerCase();

        for (Progreso progreso : progresos) {
            if (progreso.getFecha().toString().toLowerCase().contains(criterioLower) ||
                progreso.getImagen().toLowerCase().contains(criterioLower) ||
                String.valueOf(progreso.getIdProgreso()).contains(criterioLower) ||
                String.valueOf(progreso.getIdCliente()).contains(criterioLower) ||
                String.valueOf(progreso.getPeso()).contains(criterioLower)) {
                model.addRow(new Object[] { 
                    progreso.getIdProgreso(), 
                    progreso.getIdCliente(), 
                    progreso.getFecha().toString(), 
                    progreso.getImagen(), 
                    progreso.getPeso() 
                });
            }
        }
    }
}
