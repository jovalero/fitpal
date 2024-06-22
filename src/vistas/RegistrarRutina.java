package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controlador.RutinaControlador;
import modelo.Admin;

public class RegistrarRutina extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEstado;
    private JTextField txtDescripcion;
    private JTextField txtObjetivo;
    private RutinaControlador controlador;

    public RegistrarRutina(Admin administrador) {
        controlador = new RutinaControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 11, 86, 14);
        contentPane.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setBounds(106, 8, 318, 20);
        contentPane.add(txtEstado);
        txtEstado.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(10, 42, 86, 14);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(106, 39, 318, 20);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JLabel lblObjetivo = new JLabel("Objetivo:");
        lblObjetivo.setBounds(10, 73, 86, 14);
        contentPane.add(lblObjetivo);

        txtObjetivo = new JTextField();
        txtObjetivo.setBounds(106, 70, 318, 20);
        contentPane.add(txtObjetivo);
        txtObjetivo.setColumns(10);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String estado = txtEstado.getText();
            String descripcion = txtDescripcion.getText();
            String objetivo = txtObjetivo.getText();

            if (controlador.RegistrarRutina(estado, descripcion, objetivo)) {
                JOptionPane.showMessageDialog(null, "Rutina registrada exitosamente");
                new RutinaTabla(administrador);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar rutina");
            }
        });
        btnRegistrar.setBounds(335, 227, 89, 23);
        contentPane.add(btnRegistrar);
    }
}
