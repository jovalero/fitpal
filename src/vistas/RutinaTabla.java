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
    private Rutina seleccionado;
    private JTextField txtFiltro;

    public RutinaTabla(Admin administrador) {
        this.setVisible(true);
        controlador = new RutinaControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 668, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        seleccionado = new Rutina();

        model = new DefaultTableModel(
            new String[] {
                "ID_Rutina", "Estado", "Descripcion", "Objetivo"
            }, 0
        );

        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(32, 53, 587, 281); // Adjust for filter field and buttons
        contentPane.add(scrollPane);

        txtFiltro = new JTextField();
        txtFiltro.setBounds(32, 23, 200, 20);
        contentPane.add(txtFiltro);
        txtFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Filtrar(txtFiltro.getText());
            }
        });
        btnFiltrar.setBounds(242, 22, 100, 23);
        contentPane.add(btnFiltrar);

        JLabel seleccionadolabel = new JLabel("Seleccionado: ");
        seleccionadolabel.setBounds(5, 5, 911, 14);
        contentPane.add(seleccionadolabel);

        JButton btnAdd = new JButton("Registrar Rutina");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Registrar Rutina presionado");
                new RegistrarRutina(administrador);
                dispose();
            }
        });
        btnAdd.setBounds(32, 345, 150, 30); // Adjusted position
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Rutina");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdRutina() != 0) {
                    new EditarRutina(seleccionado, administrador);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una rutina");
                }
            }
        });
        btnEdit.setBounds(192, 345, 150, 30); // Adjusted position
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Rutina");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdRutina() != 0) {
                    controlador.deleteRutina(seleccionado.getIdRutina());
                    JOptionPane.showMessageDialog(null, "Rutina eliminada");
                    actualizarTabla(administrador.getId_sucursal());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una rutina");
                }
            }
        });
        btnDelete.setBounds(352, 345, 150, 30); // Adjusted position
        contentPane.add(btnDelete);

        JButton btnHome = new JButton("Volver a Home");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador);
                dispose();
            }
        });
        btnHome.setBounds(512, 345, 150, 30); // Adjusted position
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

                        seleccionadolabel.setText("Seleccionado: ID Rutina=" + id + ", Estado=" + estado + ", Descripción=" + descripcion + ", Objetivo=" + objetivo);
                        seleccionado.setIdRutina(id);
                        seleccionado.setEstado(estado);
                        seleccionado.setDescripcion(descripcion);
                        seleccionado.setObjetivo(objetivo);
                    }
                }
            }
        });
    }

    public void actualizarTabla(int sucursal) {
        model.setRowCount(0);
        List<Rutina> rutinas = controlador.getallRutinabySucursal(sucursal);

        for (Rutina rutina : rutinas) {
            model.addRow(new Object[] { rutina.getIdRutina(), rutina.getEstado(), rutina.getDescripcion(), rutina.getObjetivo() });
        }
    }

    private void Filtrar(String criterio) {
        model.setRowCount(0);
        List<Rutina> rutinas = controlador.getAllRutinas();
        for (Rutina rutina : rutinas) {
            if (rutina.getDescripcion().contains(criterio) || rutina.getEstado().contains(criterio) || rutina.getObjetivo().contains(criterio)) {
                model.addRow(new Object[] { rutina.getIdRutina(), rutina.getEstado(), rutina.getDescripcion(), rutina.getObjetivo() });
            }
        }
    }
}

