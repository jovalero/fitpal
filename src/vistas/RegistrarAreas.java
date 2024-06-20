package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interfaces.VerificacionesRepository;
import modelo.Admin;

public class RegistrarAreas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idAreaInput;
    private JTextField nameInput;
    private JTextField idSucursalInput;
    private JTextField locationInput;

    public RegistrarAreas(Admin administrador) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 542, 465);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel registrarAreaLabel = new JLabel("Registrar Área");
        registrarAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        registrarAreaLabel.setBounds(150, 10, 243, 62);
        contentPane.add(registrarAreaLabel);

        JLabel idAreaLabel = new JLabel("ID Área:");
        idAreaLabel.setBounds(150, 100, 66, 14);
        contentPane.add(idAreaLabel);

        idAreaInput = new JTextField();
        idAreaInput.setBounds(250, 97, 150, 20);
        contentPane.add(idAreaInput);
        idAreaInput.setColumns(10);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(150, 140, 66, 14);
        contentPane.add(nameLabel);

        nameInput = new JTextField();
        nameInput.setColumns(10);
        nameInput.setBounds(250, 137, 150, 20);
        contentPane.add(nameInput);

        JLabel idSucursalLabel = new JLabel("ID Sucursal:");
        idSucursalLabel.setBounds(150, 180, 66, 14);
        contentPane.add(idSucursalLabel);

        idSucursalInput = new JTextField();
        idSucursalInput.setColumns(10);
        idSucursalInput.setBounds(250, 177, 150, 20);
        contentPane.add(idSucursalInput);

        JLabel locationLabel = new JLabel("Ubicación:");
        locationLabel.setBounds(150, 220, 66, 14);
        contentPane.add(locationLabel);

        locationInput = new JTextField();
        locationInput.setColumns(10);
        locationInput.setBounds(250, 217, 150, 20);
        contentPane.add(locationInput);

        JButton registrarButton = new JButton("Registrar Área");
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarArea(administrador);
            }
        });
        registrarButton.setBounds(107, 300, 150, 23);
        contentPane.add(registrarButton);

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

    private void registrarArea(Admin administrador) {
        String idArea = idAreaInput.getText();
        String name = nameInput.getText();
        String idSucursal = idSucursalInput.getText();
        String location = locationInput.getText();
        boolean flag = true;

        if (!VerificacionesRepository.SoloEnteros(idArea)) {
            flag = false;
        }

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
            boolean registro = administrador.RegistrarAreas(idArea, name, idSucursal, location);
            if (registro) {
                JOptionPane.showMessageDialog(this, "Área registrada exitosamente!");
                new AreasTabla(administrador);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar el área.");
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
                    RegistrarAreas frame = new RegistrarAreas(admin);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
