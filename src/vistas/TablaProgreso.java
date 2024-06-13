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

    public TablaProgreso(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(null);

        setContentPane(contentPane);

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

        Progreso seleccionado = new Progreso(0, 0, null, "", 0.0);

        String[] columnNames = { "ID Progreso", "ID Cliente", "Fecha", "Imagen", "Peso" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 37, 911, 190);
        contentPane.setLayout(null);
        contentPane.add(scrollPane);

        JLabel seleccionadoLabel = new JLabel("Seleccionado:");
        seleccionadoLabel.setBounds(5, 5, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null && seleccionado.getIdProgreso() != 0) {
                    int idProgreso = seleccionado.getIdProgreso();
                    System.out.println("ID Progreso a eliminar: " + idProgreso);
                    controlador.deleteProgreso(idProgreso);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(null, "Progreso eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un progreso");
                }
            }
        });

        btnEliminar.setBounds(626, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdProgreso() != 0) {
                    new EditarProgreso(controlador, seleccionado, TablaProgreso.this).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un progreso");
                }
            }
        });
        btnEditar.setBounds(366, 257, 166, 58);
        contentPane.add(btnEditar);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 AgregarProgreso pantalla =  new AgregarProgreso(controlador, TablaProgreso.this);
            	 dispose();
            }
        });
        btnAgregar.setBounds(91, 257, 166, 58);
        contentPane.add(btnAgregar);

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
        model.setRowCount(0); //
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

}


