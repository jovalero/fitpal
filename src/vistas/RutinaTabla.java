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

    public RutinaTabla(Admin administrador) {
        this.setVisible(true);
        controlador = new RutinaControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 668, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        model = new DefaultTableModel(
            new String[] {
                "ID_Rutina", "Estado", "Descripción", "Objetivo"
            }, 0
        );
        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(32, 53, 587, 311); // Adjust for buttons
        contentPane.add(scrollPane);

        JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
        Seleccionadolabel.setBounds(5, 5, 911, 14);
        contentPane.add(Seleccionadolabel);

        filtroField = new JTextField();
        filtroField.setBounds(32, 23, 150, 20);
        contentPane.add(filtroField);
        filtroField.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(192, 22, 89, 23);
        contentPane.add(btnFiltrar);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filtro = filtroField.getText();
                filtrarTabla(filtro, administrador.getId_sucursal());
            }
        });

        JButton btnAdd = new JButton("Agregar Rutina");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }
        });
        btnAdd.setBounds(56, 375, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Rutina");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }
        });
        btnEdit.setBounds(227, 375, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Rutina");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }
        });
        btnDelete.setBounds(406, 375, 150, 30);
        contentPane.add(btnDelete);

        JButton btnHome = new JButton("Volver a Inicio");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAInicio(administrador);
            }
        });
        btnHome.setBounds(566, 375, 150, 30);
        contentPane.add(btnHome);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void actualizarTabla(int sucursal) {
        model.setRowCount(0);
        List<Rutina> rutinas = controlador.getallRutinabySucursal(sucursal);
        for (Rutina rutina : rutinas) {
            model.addRow(new Object[] {
                rutina.getIdRutina(),
                rutina.getEstado(),
                rutina.getDescripcion(),
                rutina.getObjetivo()
            });
        }
    }

    private void filtrarTabla(String filtro, int sucursalId) {
        model.setRowCount(0); // Limpiar la tabla antes de actualizar
        List<Rutina> rutinas = controlador.getallRutinabySucursal(sucursalId);
        for (Rutina rutina : rutinas) {
            if (rutina.getDescripcion().contains(filtro)) {
                model.addRow(new Object[] {
                    rutina.getIdRutina(),
                    rutina.getEstado(),
                    rutina.getDescripcion(),
                    rutina.getObjetivo()
                });
            }
        }
    }

    private void agregarSucursal() {
        // TODO Auto-generated method stub
    }

    private void editarSucursal() {
        // TODO Auto-generated method stub
    }

    private void borrarSucursal() {
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
