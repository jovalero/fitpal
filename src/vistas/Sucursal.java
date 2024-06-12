package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DatabaseConnection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sucursal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Sucursal frame = new Sucursal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Sucursal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 628, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 592, 300);
        contentPane.add(scrollPane);

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID_Sucursal", "Direccion", "Telefono"
            }
        );
        table = new JTable(model);
        scrollPane.setViewportView(table);

        loadSucursales();

        JButton btnAdd = new JButton("Agregar Sucursal");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarSucursal();
            }
        });
        btnAdd.setBounds(10, 322, 150, 30);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("Editar Sucursal");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarSucursal();
            }
        });
        btnEdit.setBounds(170, 322, 150, 30);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Borrar Sucursal");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarSucursal();
            }
        });
        btnDelete.setBounds(330, 322, 150, 30);
        contentPane.add(btnDelete);
    }

    private void loadSucursales() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM sucursal")) {

            while (rs.next()) {
                int id = rs.getInt("ID_Sucursal");
                String direccion = rs.getString("Direccion");
                String telefono = rs.getString("Telefono");
                model.addRow(new Object[]{id, direccion, telefono});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las sucursales", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarSucursal() {
        String direccion = JOptionPane.showInputDialog("Ingresa Dirección");
        String telefono = JOptionPane.showInputDialog("Ingresa Teléfono");

        if (direccion == null || direccion.trim().isEmpty() || telefono == null || telefono.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error al agregar la sucursal: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sucursal (Direccion, Telefono) VALUES (?, ?)")) {

                pstmt.setString(1, direccion);
                pstmt.setString(2, telefono);
                pstmt.executeUpdate();
                model.addRow(new Object[]{getLastInsertedId(conn), direccion, telefono});

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al agregar la sucursal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getLastInsertedId(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la última inserción.");
            }
        }
    }

    private void editarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String direccion = JOptionPane.showInputDialog("Editar Dirección", model.getValueAt(selectedRow, 1));
            String telefono = JOptionPane.showInputDialog("Editar Teléfono", model.getValueAt(selectedRow, 2));

            if (direccion == null || direccion.trim().isEmpty() || telefono == null || telefono.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error al editar la sucursal: Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement("UPDATE sucursal SET Direccion = ?, Telefono = ? WHERE ID_Sucursal = ?")) {

                    pstmt.setString(1, direccion);
                    pstmt.setString(2, telefono);
                    pstmt.setInt(3, id);
                    pstmt.executeUpdate();
                    model.setValueAt(direccion, selectedRow, 1);
                    model.setValueAt(telefono, selectedRow, 2);

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al editar la sucursal", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una sucursal para editar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void borrarSucursal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM sucursal WHERE ID_Sucursal = ?")) {

                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                model.removeRow(selectedRow);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al borrar la sucursal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una sucursal para borrar", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
