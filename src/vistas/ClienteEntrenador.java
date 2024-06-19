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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import controlador.ProgresoControlador;
import modelo.Admin;
import modelo.Cliente;
import modelo.Entrenador;
import modelo.Progreso;

public class ClienteEntrenador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ClienteControlador controlador;
    private ProgresoControlador progreso;
    private Cliente seleccionado;
    private Entrenador entrenador;

    public ClienteEntrenador(Admin administrador, Entrenador entrenador) {
        this.entrenador = entrenador;
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
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

        seleccionado = new Cliente();

        String[] columnNames = {
            "ID Entrenador", "ID Cliente", "ID Dieta", "ID Sucursal", "Peso", "Altura", "Objetivo",
            "Nombre", "Apellido", "Correo Electrónico", "Teléfono", "Fecha Venc Sus", 
            "Contraseña", "Estado Suscripción", "DNI"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla(administrador.getId_sucursal());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 37, 1180, 450);
        contentPane.add(scrollPane);

        JLabel Seleccionadolabel = new JLabel("Seleccionado: ");
        Seleccionadolabel.setBounds(5, 5, 1180, 14);
        contentPane.add(Seleccionadolabel);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TablaEntrenadores(administrador);
                dispose();
            }
        });
        btnAtras.setBounds(805, 498, 283, 52);
        contentPane.add(btnAtras);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(72, 498, 268, 52);
        contentPane.add(btnEditar);
        
        JButton btnAsignarCliente = new JButton("Asignar Cliente Nuevo");
        btnAsignarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AsignarClienteEntrenador(administrador, entrenador);
                dispose();
        	}
        });
        btnAsignarCliente.setBounds(410, 498, 268, 52);
        contentPane.add(btnAsignarCliente);
     

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Integer idEntrenador = (Integer) table.getValueAt(selectedRow, 0);
                        int idCliente = (int) table.getValueAt(selectedRow, 1);
                        int idDieta = (int) table.getValueAt(selectedRow, 2);
                        int idSucursal = (int) table.getValueAt(selectedRow, 3);
                        double peso = (double) table.getValueAt(selectedRow, 4);
                        double altura = (double) table.getValueAt(selectedRow, 5);
                        String objetivo = (String) table.getValueAt(selectedRow, 6);
                        String nombre = (String) table.getValueAt(selectedRow, 7);
                        String apellido = (String) table.getValueAt(selectedRow, 8);
                        String correoElectronico = (String) table.getValueAt(selectedRow, 9);
                        int telefono = (int)(table.getValueAt(selectedRow, 10));
                        LocalDate Fechavenc = (LocalDate) table.getValueAt(selectedRow, 11);
                        String contrasena = (String) table.getValueAt(selectedRow, 12);
                        String estadoSus = (String) table.getValueAt(selectedRow, 13);
                        int dni = (int)(table.getValueAt(selectedRow, 14));
                        
                        Seleccionadolabel.setText("Seleccionado: ID=" + idCliente + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Correo Electrónico=" + correoElectronico);
                        
                        seleccionado.setId_entrenador(idEntrenador);
                        seleccionado.setId_cliente(idCliente);
                        seleccionado.setNombre(nombre);
                        seleccionado.setApellido(apellido);
                        seleccionado.setUsuario(correoElectronico);
                        seleccionado.setTelefono(telefono);
                        seleccionado.setDNI(dni);
                        seleccionado.setId_dieta(idDieta);
                        seleccionado.setId_sucursal(idSucursal);
                        seleccionado.setPeso(peso);
                        seleccionado.setAltura(altura);
                        seleccionado.setObjetivo(objetivo);
                        seleccionado.setFechavenc(Fechavenc);
                        seleccionado.setContrasena(contrasena);
                        seleccionado.setEstado_sus(estadoSus);
                    }
                }
            }
        });
    }

    public void actualizarTabla(int sucursal) { 
        model.setRowCount(0);

        LinkedList<Cliente> clientes = controlador.getAllClientesBySucursal(sucursal);

        for (Cliente cliente : clientes) {
            // Filtrar por clientes del entrenador actual o con suscripción activa
            if (cliente.getId_entrenador() == entrenador.getId_entrenador()) {
                model.addRow(new Object[]{
                    cliente.getId_entrenador(), cliente.getId_cliente(), cliente.getId_dieta(), cliente.getId_sucursal(),
                    cliente.getPeso(), cliente.getAltura(), cliente.getObjetivo(), cliente.getNombre(), 
                    cliente.getApellido(), cliente.getUsuario(), cliente.getTelefono(), 
                    cliente.getFechavenc(), cliente.getContrasena(), cliente.getEstado_sus(), cliente.getDNI()
                });
            }
        }
    }
}


