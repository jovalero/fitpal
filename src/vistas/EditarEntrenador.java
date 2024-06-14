package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Admin;
import modelo.Entrenador;
import controlador.EntrenadorControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarEntrenador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField NombreInput;
    private JTextField ApellidoInput;
    private JTextField MailInput;
    private JTextField TelefonoInput;
    private JTextField DniInput;
    private JTextField ContrasenaInput;
    private JTextField NumEntrenadosInput;


    public EditarEntrenador(Admin administrador, Entrenador entrenador) {
  
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 542, 465);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel EditarEntrenadorLabel = new JLabel("Editar Entrenador");
        EditarEntrenadorLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        EditarEntrenadorLabel.setBounds(120, 0, 300, 62);
        contentPane.add(EditarEntrenadorLabel);

        NombreInput = new JTextField(entrenador.getNombre());
        NombreInput.setBounds(198, 73, 86, 20);
        contentPane.add(NombreInput);
        NombreInput.setColumns(10);

        JLabel ApellidoLabel = new JLabel("Apellido: ");
        ApellidoLabel.setBounds(150, 113, 46, 14);
        contentPane.add(ApellidoLabel);

        ApellidoInput = new JTextField(entrenador.getApellido());
        ApellidoInput.setColumns(10);
        ApellidoInput.setBounds(198, 110, 86, 20);
        contentPane.add(ApellidoInput);

        JLabel NombreLabel = new JLabel("Nombre: ");
        NombreLabel.setBounds(150, 76, 46, 14);
        contentPane.add(NombreLabel);

        MailInput = new JTextField(entrenador.getEmail());
        MailInput.setColumns(10);
        MailInput.setBounds(198, 141, 86, 20);
        contentPane.add(MailInput);

        JLabel MailLabel = new JLabel("Mail: ");
        MailLabel.setBounds(170, 144, 46, 14);
        contentPane.add(MailLabel);

        JLabel TelefonoLabel = new JLabel("Telefono: ");
        TelefonoLabel.setBounds(150, 172, 66, 14);
        contentPane.add(TelefonoLabel);

        TelefonoInput = new JTextField(String.valueOf(entrenador.getTelefono()));
        TelefonoInput.setColumns(10);
        TelefonoInput.setBounds(198, 169, 86, 20);
        contentPane.add(TelefonoInput);

        DniInput = new JTextField(String.valueOf(entrenador.getDNI()));
        DniInput.setColumns(10);
        DniInput.setBounds(198, 197, 86, 20);
        contentPane.add(DniInput);

        JLabel DNILabel = new JLabel("DNI:");
        DNILabel.setBounds(170, 200, 66, 14);
        contentPane.add(DNILabel);

        ContrasenaInput = new JTextField(entrenador.getContrasena());
        ContrasenaInput.setColumns(10);
        ContrasenaInput.setBounds(198, 224, 86, 20);
        contentPane.add(ContrasenaInput);

        JLabel ContrasenaLabel = new JLabel("Contraseña: ");
        ContrasenaLabel.setBounds(130, 227, 66, 14);
        contentPane.add(ContrasenaLabel);

        NumEntrenadosInput = new JTextField(String.valueOf(entrenador.getNumentrenados()));
        NumEntrenadosInput.setColumns(10);
        NumEntrenadosInput.setBounds(198, 255, 86, 20);
        contentPane.add(NumEntrenadosInput);

        JLabel NumEntrenadosLabel = new JLabel("Num Entrenados: ");
        NumEntrenadosLabel.setBounds(100, 255, 100, 14);
        contentPane.add(NumEntrenadosLabel);

        JButton EditarButton = new JButton("Guardar Cambios");
        EditarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = NombreInput.getText();
                String apellido = ApellidoInput.getText();
                String mail = MailInput.getText();
                String contrasena = ContrasenaInput.getText();
                int telefono = 0;
                int dni = 0;
                int numEntrenados = 0;
                boolean flag = true;

                try {
                    telefono = Integer.parseInt(TelefonoInput.getText());
                    dni = Integer.parseInt(DniInput.getText());
                    numEntrenados = Integer.parseInt(NumEntrenadosInput.getText());
                } catch (NumberFormatException ex) {
                    flag = false;
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos en Telefono, DNI y Num Entrenados");
                }

                if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || contrasena.isEmpty()) {
                    flag = false;
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                }

                if (flag) {
                    entrenador.setNombre(nombre);
                    entrenador.setApellido(apellido);
                    entrenador.setTelefono(telefono);
                    entrenador.setDNI(dni);
                    entrenador.setEmail(mail);
                    entrenador.setContrasena(contrasena);
                    entrenador.setNumentrenados(numEntrenados);

                    EntrenadorControlador controlador = new EntrenadorControlador();
                    controlador.updateEntrenador(entrenador);

                    // Actualizar la tabla de entrenadores y cerrar la ventana de edición
                    new TablaEntrenadores(administrador).setVisible(true);
                    dispose();
                }
            }
        });
        EditarButton.setBounds(179, 338, 150, 23);
        contentPane.add(EditarButton);
    }
}
