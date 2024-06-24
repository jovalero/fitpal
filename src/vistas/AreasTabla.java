package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.AreasControlador;
import modelo.Admin;
import modelo.Areas;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AreasTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private AreasControlador controlador;
    private Areas seleccionado;
    private JTextField txtFiltro;

    public AreasTabla(Admin administrador) {
        this.setVisible(true);
        controlador = new AreasControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 436); // Adjust height for buttons
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        seleccionado = new Areas();

        model = new DefaultTableModel(
            new String[] {
                "ID_Areas", "Nombre", "ID_Sucursal", "Ubicacion"
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

        JButton btnAdd = new JButton("Agregar Área");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegistrarAreas(administrador);
                dispose();
            }
        });
        btnAdd.setBounds(32, 345, 150, 30); // Adjusted position
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Área");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdArea() != 0) {
                    new EditarAreas(seleccionado, administrador);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un área");
                }
            }
        });
        btnEdit.setBounds(192, 345, 150, 30); // Adjusted position
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Área");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getIdArea() != 0) {
                    controlador.deleteArea(seleccionado.getIdArea());
                    JOptionPane.showMessageDialog(null, "Área eliminada");
                    actualizarTabla(administrador.getId_sucursal());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un área");
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
                        String nombre = (String) table.getValueAt(selectedRow, 1);
                        int idSucursal = (int) table.getValueAt(selectedRow, 2);
                        String ubicacion = (String) table.getValueAt(selectedRow, 3);

                        seleccionadolabel.setText("Seleccionado: ID Área=" + id + ", Nombre=" + nombre + ", ID Sucursal=" + idSucursal + ", Ubicación=" + ubicacion);
                        seleccionado.setIdArea(id);
                        seleccionado.setNombre(nombre);
                        seleccionado.setIdSucursal(idSucursal);
                        seleccionado.setUbicacion(ubicacion);
                    }
                }
            }
        });
    }

    public void actualizarTabla(int sucursal) {
        model.setRowCount(0);
        List<Areas> areas = controlador.getallAreasbySucursal(sucursal);

        for (Areas area : areas) {
            model.addRow(new Object[] {area.getIdArea(), area.getNombre(), area.getIdSucursal(), area.getUbicacion()});
        }
    }

    private void Filtrar(String criterio) {
        model.setRowCount(0);
        List<Areas> areas = controlador.getAllAreas();
        for (Areas area : areas) {
            if (area.getNombre().contains(criterio) || area.getUbicacion().contains(criterio)) {
                model.addRow(new Object[] {area.getIdArea(), area.getNombre(), area.getIdSucursal(), area.getUbicacion()});
            }
        }
    }
}
