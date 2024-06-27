package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.AreasControlador;
import interfaces.VerificacionesRepository;
import modelo.Admin;
import modelo.Areas;

public class EditarAreas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idAreaInput;
    private JTextField nameInput;
    private JTextField idSucursalInput;
    private JTextField locationInput;
    private AreasControlador controlador;
    private Areas area;

    public EditarAreas(Areas area, Admin administrador) {
        this.area = area;
        controlador = new AreasControlador();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 542, 465);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel editarAreaLabel = new JLabel("Editar Área");
        editarAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        editarAreaLabel.setBounds(150, 10, 243, 62);
        contentPane.add(editarAreaLabel);

        JLabel idAreaLabel = new JLabel("ID Área:");
        idAreaLabel.setBounds(150, 100, 66, 14);
        contentPane.add(idAreaLabel);

        idAreaInput = new JTextField(String.valueOf(area.getIdArea()));
        idAreaInput.setBounds(250, 97, 150, 20);
        idAreaInput.setEditable(false);
        contentPane.add(idAreaInput);
        idAreaInput.setColumns(10);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(150, 140, 66, 14);
        contentPane.add(nameLabel);

        nameInput = new JTextField(area.getNombre());
        nameInput.setColumns(10);
        nameInput.setBounds(250, 137, 150, 20);
        contentPane.add(nameInput);

        JLabel idSucursalLabel = new JLabel("ID Sucursal:");
        idSucursalLabel.setBounds(150, 180, 66, 14);
        contentPane.add(idSucursalLabel);

        idSucursalInput = new JTextField(String.valueOf(area.getIdSucursal()));
        idSucursalInput.setColumns(10);
        idSucursalInput.setBounds(250, 177, 150, 20);
        contentPane.add(idSucursalInput);

        JLabel locationLabel = new JLabel("Ubicación:");
        locationLabel.setBounds(150, 220, 66, 14);
        contentPane.add(locationLabel);

        locationInput = new JTextField(area.getUbicacion());
        locationInput.setColumns(10);
        locationInput.setBounds(250, 217, 150, 20);
        contentPane.add(locationInput);

        JButton editarButton = new JButton("Guardar Cambios");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarArea(administrador);
            }
        });
        editarButton.setBounds(107, 300, 150, 23);
        contentPane.add(editarButton);

        JButton volverButton = new JButton("Volver a Áreas");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreasTabla(administrador);
                dispose();
            }
        });
        volverButton.setBounds(274, 300, 150, 23);
        contentPane.add(volverButton);
    }

    private void editarArea(Admin administrador) {
        String name = nameInput.getText();
        String idSucursal = idSucursalInput.getText();
        String location = locationInput.getText();
        boolean flag = true;

        if (!VerificacionesRepository.Sololetras(name)) {
            flag = false;
        }

        if (!VerificacionesRepository.SoloEnteros(idSucursal)) {
            flag = false;
        }

        if (location.isEmpty()) {
            flag = false;
        }

        if (flag) {
            area.setNombre(name);
            area.setIdSucursal(Integer.parseInt(idSucursal));
            area.setUbicacion(location);
            boolean actualizado = controlador.updateArea(area);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Área actualizada exitosamente!");
                new AreasTabla(administrador);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar el área.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin admin = new Admin(null);
                    Areas area = new Areas(); // Inicializa con un área válida para pruebas
                    EditarAreas frame = new EditarAreas(area, admin);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

