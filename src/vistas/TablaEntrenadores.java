package vistas;

import java.awt.EventQueue;
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
import controlador.EntrenadorControlador;
import modelo.Admin;
import modelo.Entrenador;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TablaEntrenadores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private EntrenadorControlador controlador;

    public TablaEntrenadores(Admin administrador) {
        this.setVisible(true);
        controlador = new EntrenadorControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        try {
            controlador = new EntrenadorControlador(); // Controlador para manejar la lógica de los entrenadores
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        Entrenador seleccionado = new Entrenador();

        String[] columnames = {"ID_Entrenador", "Telefono", "Nombre", "Apellido", "Contrasenia", "ID_Sucursal", "Num_Entrenados", "DNI", "email"};
        model = new DefaultTableModel(columnames, 0);
        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());
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
                if (seleccionado.getId_entrenador() != 0) {
              
                    controlador.deleteEntrenador(seleccionado.getId_entrenador());
                    JOptionPane.showMessageDialog(null, "Eliminado");
                    actualizarTabla(administrador.getId_sucursal());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un entrenador");
                }
            }
        });
        btnEliminar.setBounds(49, 257, 150, 58);
        contentPane.add(btnEliminar);

        JButton Editar = new JButton("Editar");
        Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_entrenador() != 0) {
                	EditarEntrenador editar = new EditarEntrenador(administrador,seleccionado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un entrenador");
                }
            }
        });
        Editar.setBounds(209, 257, 150, 58);
        contentPane.add(Editar);

        JButton Registrarbutton = new JButton("Registrar nuevo");
        Registrarbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                RegistrarEntrenador registrar = new RegistrarEntrenador(administrador);
                dispose();
            }
        });
        Registrarbutton.setBounds(369, 257, 150, 58);
        contentPane.add(Registrarbutton);
        
        JButton btnClientes = new JButton("Clientes ");
        btnClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ClienteEntrenador clientes = new ClienteEntrenador(administrador,seleccionado);
        		dispose();
 
        	}
        });
        btnClientes.setBounds(529, 257, 150, 58);
        contentPane.add(btnClientes);
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                new HomeAdmin (administrador);
                dispose();
        	}
        });
        btnAtras.setBounds(689, 257, 150, 58);
        contentPane.add(btnAtras);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int idEntrenador = (int) table.getValueAt(selectedRow, 0);
                        int telefono = (int) table.getValueAt(selectedRow, 1);
                        String nombre = (String) table.getValueAt(selectedRow, 2);
                        String apellido = (String) table.getValueAt(selectedRow, 3);
                        String contrasena = (String) table.getValueAt(selectedRow, 4);
                        int idSucursal = (int) table.getValueAt(selectedRow, 5);
                        int numEntrenados = (int) table.getValueAt(selectedRow, 6);
                        int dni = (int) table.getValueAt(selectedRow, 7);
                        String email = (String) table.getValueAt(selectedRow, 8);
                        Seleccionadolabel.setText("Seleccionado: ID_Entrenador=" + idEntrenador + ", Nombre=" + nombre + ", Email=" + email + ", Apellido=" + apellido + ", DNI=" + dni);
                        seleccionado.setId_entrenador(idEntrenador);
                        seleccionado.setTelefono(telefono);
                        seleccionado.setNombre(nombre);
                        seleccionado.setApellido(apellido);
                        seleccionado.setContrasena(contrasena);
                        seleccionado.setId_sucursal(idSucursal);
                        seleccionado.setNumentrenados(numEntrenados);
                        seleccionado.setDNI(dni);
                        seleccionado.setEmail(email);
                    }
                }
            }
        });
    }

    public void actualizarTabla(int idSucursal) {
        model.setRowCount(0);
        LinkedList<Entrenador> entrenadores = controlador.getAllEntrenadoresBySucursal(idSucursal);
        for (Entrenador entrenador : entrenadores) {
            model.addRow(new Object[]{entrenador.getId_entrenador(), entrenador.getTelefono(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getContrasena(), entrenador.getId_sucursal(), entrenador.getNumentrenados(), entrenador.getDNI(), entrenador.getEmail()});
        }
    }
}

