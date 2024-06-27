package vistas;

import controlador.ClienteControlador;
import controlador.ProgresoControlador;
import modelo.Admin;
import modelo.Cliente;
import modelo.Progreso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class TablaClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ClienteControlador controlador;
    private ProgresoControlador progreso;
    private Cliente clienteSeleccionado;
    private JTextField textFieldFiltro;

    public TablaClientes(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            controlador = new ClienteControlador();
            progreso = new ProgresoControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        String[] columnNames = {"ID", "Nombre", "Apellido", "DNI", "Mail"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());
        table.setBounds(10, 37, 532, 204);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 73, 921, 173);
        contentPane.add(scrollPane);

        JLabel seleccionadoLabel = new JLabel("SECCIÓN DE CLIENTES");
        seleccionadoLabel.setBounds(10, 11, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clienteSeleccionado != null) {
                    LinkedList<Progreso> progresoscliente = progreso.getAllProgresos();
                    for (Progreso progresoc : progresoscliente) {
                        if (progresoc.getIdCliente() == clienteSeleccionado.getId_cliente()) {
                            progreso.deleteProgreso(progresoc.getIdProgreso());
                        }
                    }
                    controlador.deleteCliente(clienteSeleccionado.getId_cliente());
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                    actualizarTabla(administrador.getId_sucursal());
                    clienteSeleccionado = null;
                    seleccionadoLabel.setText("Seleccionado: ");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente");
                }
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clienteSeleccionado != null) {
                    EditarCliente editar = new EditarCliente(clienteSeleccionado, administrador);
                    editar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente");
                }
            }
        });

        JButton btnRegistrar = new JButton("Registrar nuevo");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarCliente registrar = new RegistrarCliente(administrador);
                registrar.setVisible(true);
            }
        });

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.addActionListener(new ActionListener() {
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
        btnRegistrar.setBounds(spacing * 3 + buttonWidth * 2, 257, buttonWidth, buttonHeight);
        btnVolver.setBounds(spacing * 4 + buttonWidth * 3, 257, buttonWidth, buttonHeight);

        contentPane.add(btnEliminar);
        contentPane.add(btnEditar);
        contentPane.add(btnRegistrar);
        contentPane.add(btnVolver);

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
                Filtrar(textFieldFiltro.getText(), administrador.getId_sucursal());
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
                        String apellido = (String) table.getValueAt(selectedRow, 2);
                        int DNI = (int) table.getValueAt(selectedRow, 3);
                        String mail = (String) table.getValueAt(selectedRow, 4);
                        seleccionadoLabel.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Apellido=" + apellido + ", DNI=" + DNI + ", Mail=" + mail);
                        clienteSeleccionado = new Cliente();
                    }
                }
            }
        });
    }

    public void actualizarTabla(int sucursal) {
        model.setRowCount(0);
        LinkedList<Cliente> clientes = controlador.getAllClientesBySucursal(sucursal);
        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getId_cliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDNI(), cliente.getUsuario()});
        }
    }

    private void Filtrar(String criterio, int sucursal) {
        model.setRowCount(0);
        List<Cliente> clientes = controlador.getAllClientesBySucursal(sucursal);
        String criterioLower = criterio.toLowerCase();

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().toLowerCase().contains(criterioLower)
                    || cliente.getApellido().toLowerCase().contains(criterioLower)
                    || String.valueOf(cliente.getDNI()).contains(criterioLower)
                    || cliente.getUsuario().toLowerCase().contains(criterioLower)) {
                model.addRow(new Object[]{cliente.getId_cliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDNI(), cliente.getUsuario()});
            }
        }
    }
}
