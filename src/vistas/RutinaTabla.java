package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
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
    private JTextField filtroField;
    private Rutina rutinaSeleccionada;

    public RutinaTabla(Admin administrador) {
        this.setVisible(true);
        controlador = new RutinaControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        model = new DefaultTableModel(
            new Object[][] {}, // No initial data
            new String[] { "ID_Rutina", "Estado", "Descripción", "Objetivo" }
        );
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 83, 911, 190);
        contentPane.add(scrollPane);

        JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
        Seleccionadolabel.setBounds(10, 11, 911, 14);
        contentPane.add(Seleccionadolabel);

        filtroField = new JTextField();
        filtroField.setBounds(10, 52, 150, 20);
        contentPane.add(filtroField);
        filtroField.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(170, 49, 89, 23);
        contentPane.add(btnFiltrar);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filtro = filtroField.getText();
                filtrarTabla(filtro, administrador.getIdRutina());
            }
        });

        JButton btnAdd = new JButton("Agregar Rutina");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarRutina();
            }
        });
        btnAdd.setBounds(10, 301, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Rutina");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarRutina();
            }
        });
        btnEdit.setBounds(183, 301, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Rutina");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarRutina();
            }
        });
        btnDelete.setBounds(353, 301, 150, 30);
        contentPane.add(btnDelete);

        JButton btnHome = new JButton("Volver a Inicio");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAInicio(administrador);
            }
        });
        btnHome.setBounds(522, 301, 150, 30);
        contentPane.add(btnHome);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String estado = (String) table.getValueAt(selectedRow, 1);
                        String descripcion = (String) table.getValueAt(selectedRow, 2);
                        String objetivo = (String) table.getValueAt(selectedRow, 3);
                        Seleccionadolabel.setText("Seleccionado: ID=" + id + ", Estado=" + estado + ", Descripción=" + descripcion + ", Objetivo=" + objetivo);
                        rutinaSeleccionada = new Rutina(id, estado, descripcion, objetivo);
                    }
                }
            }
        });

        actualizarTabla(administrador.getIdRutina());
    }

    public void actualizarTabla(int rutinaId) {
        model.setRowCount(0);
        Rutina rutina = controlador.getRutinaById(rutinaId);
        if (rutina != null) {
            model.addRow(new Object[] {
                rutina.getID_Rutina(),
                rutina.getEstado(),
                rutina.getDescripcion(),
                rutina.getObjetivo()
            });
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la rutina con el ID especificado.");
        }
    }


    private void filtrarTabla(String filtro, int rutinaId) {
        model.setRowCount(0);
        Rutina rutina = controlador.getRutinaById(rutinaId);
        if (rutina != null) {
            if (rutina.getDescripcion().toLowerCase().contains(filtro.toLowerCase()) || rutina.getEstado().toLowerCase().contains(filtro.toLowerCase())) {
                model.addRow(new Object[] {
                    rutina.getID_Rutina(),
                    rutina.getEstado(),
                    rutina.getDescripcion(),
                    rutina.getObjetivo()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la rutina con el ID especificado.");
        }
    }

    private void agregarRutina() {
        // Implementar lógica para agregar una rutina
    }

    private void editarRutina() {
        if (rutinaSeleccionada != null) {
            // Implementar lógica para editar una rutina
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una rutina para editar.");
        }
    }

    private void borrarRutina() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idRutina = (int) table.getValueAt(selectedRow, 0);
            controlador.deleteRutina(idRutina);
            actualizarTabla(idRutina); // Actualizar tabla después de borrar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una rutina para borrar.");
        }
    }

    private void volverAInicio(Admin administrador) {
        new HomeAdmin(administrador).setVisible(true);
        this.dispose();
    }
}
