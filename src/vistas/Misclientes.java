package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controlador.ClienteControlador;
import modelo.Cliente;
import modelo.Entrenador;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Misclientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ClienteControlador controlador;
    private Cliente seleccionado;
    private JTextField textBuscar;

    /**
     * Create the frame.
     */
    public Misclientes(Entrenador entrenador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        try {
            controlador = new ClienteControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        seleccionado = new Cliente();

        String[] columnNames = {"ID", "Nombre", "Apellido", "DNI", "Mail"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        table.setBounds(10, 37, 532, 204);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 37, 911, 190);
        contentPane.add(scrollPane);

        JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
        Seleccionadolabel.setBounds(5, 5, 911, 14);
        contentPane.add(Seleccionadolabel);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_cliente() != 0) {
                    controlador.deleteCliente(seleccionado.getId_cliente());
                    JOptionPane.showMessageDialog(null, "Eliminado");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario");
                }
            }
        });
        btnEliminar.setBounds(31, 257, 187, 58);
        contentPane.add(btnEliminar);

        JButton Volverbutton = new JButton("Volver a menú");
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin();
                dispose();
            }
        });
        Volverbutton.setBounds(667, 257, 166, 58);
        contentPane.add(Volverbutton);

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
                        Seleccionadolabel.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Mail=" + mail + ", Apellido=" + apellido + ", DNI=" + DNI);
                        seleccionado.setUsuario(mail);
                        seleccionado.setNombre(nombre);
                        seleccionado.setId_cliente(id);
                        seleccionado.setApellido(apellido);
                        seleccionado.setDNI(DNI);
                    }
                }
            }
        });

        JLabel lblBuscar = new JLabel("Buscador:");
        lblBuscar.setBounds(10, 237, 58, 14);
        contentPane.add(lblBuscar);

        textBuscar = new JTextField();
        textBuscar.setBounds(78, 234, 204, 20);
        contentPane.add(textBuscar);
        textBuscar.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarClientes(textBuscar.getText());
            }
        });
        btnBuscar.setBounds(303, 233, 89, 23);
        contentPane.add(btnBuscar);
    }

    public void actualizarTabla() {
        model.setRowCount(0);

        LinkedList<Cliente> clientes = controlador.getAllClientes();

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getId_cliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDNI(), cliente.getUsuario()});
        }
    }

    private void buscarClientes(String termino) {
        model.setRowCount(0);
        LinkedList<Cliente> clientes = controlador.getAllClientes();

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().toLowerCase().contains(termino.toLowerCase()) ||
                cliente.getApellido().toLowerCase().contains(termino.toLowerCase()) ||
                String.valueOf(cliente.getDNI()).contains(termino.toLowerCase())) {
                model.addRow(new Object[]{cliente.getId_cliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDNI(), cliente.getUsuario()});
            }
        }
    }
}

