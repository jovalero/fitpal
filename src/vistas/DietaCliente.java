package vistas;

import controlador.DietaControlador;
import modelo.Cliente;
import modelo.Dieta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class DietaCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private DietaControlador dietaControlador;
    private Dieta dietaSeleccionada;
    private JTextField textField;

    public DietaCliente(Cliente cliente) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            dietaControlador = new DietaControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (dietaControlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        String[] columnNames = {"ID", "Nombre", "Descripción"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        table.setBounds(10, 37, 532, 204);

        JScrollPane dietaTabla = new JScrollPane(table);
        dietaTabla.setBounds(0, 73, 921, 173);
        contentPane.add(dietaTabla);

        JLabel seleccionadoLabel = new JLabel("Seleccionado: ");
        seleccionadoLabel.setBounds(10, 11, 911, 14);
        contentPane.add(seleccionadoLabel);

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
        
        JButton volverButton = new JButton("Volver a menú");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeCliente(cliente);
                dispose();
            }
        });
        volverButton.setBounds(700, 257, 166, 58);
        contentPane.add(volverButton);
        
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
                        dietaSeleccionada = new Dieta(id, nombre, descripcion);
                    }
                }
            }
        });
    }

    public void actualizarTabla() {
        model.setRowCount(0);
        LinkedList<Dieta> dietas = dietaControlador.getAllDietas();
        for (Dieta dieta : dietas) {
            model.addRow(new Object[]{dieta.getIdDieta(), dieta.getNombreDieta(), dieta.getDescripcionDieta()});
        }
    }

    private void Filtrar(String criterio) {
        model.setRowCount(0);
        List<Dieta> dietas = dietaControlador.getAllDietas();
        String criterioLower = criterio.toLowerCase();

        for (Dieta dieta : dietas) {
            if (dieta.getNombreDieta().toLowerCase().contains(criterioLower) || dieta.getDescripcionDieta().toLowerCase().contains(criterioLower)) {
                model.addRow(new Object[]{dieta.getIdDieta(), dieta.getNombreDieta(), dieta.getDescripcionDieta()});
            }
        }
    }
}
