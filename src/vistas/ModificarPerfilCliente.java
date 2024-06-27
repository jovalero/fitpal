package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

import interfaces.VerificacionesRepository;
import modelo.Cliente;
import controlador.ClienteControlador;

public class ModificarPerfilCliente extends JFrame {

    private JPanel contentPane;
    private JTextField NombreInput;
    private JTextField ApellidoInput;
    private JTextField MailInput;
    private JTextField TelefonoInput;
    private JTextField DniInput;
    private JTextField ContrasenaInput;
    private JTextField Objetivoinput;

    public ModificarPerfilCliente(Cliente Seleccinado) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 471, 427);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel EditarSeleccinadoLabel = new JLabel("Modificar Perfil");
        EditarSeleccinadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        EditarSeleccinadoLabel.setBounds(141, 0, 300, 62);
        contentPane.add(EditarSeleccinadoLabel);

        NombreInput = new JTextField(Seleccinado.getNombre());
        NombreInput.setBounds(198, 73, 86, 20);
        contentPane.add(NombreInput);
        NombreInput.setColumns(10);

        JLabel ApellidoLabel = new JLabel("Apellido: ");
        ApellidoLabel.setBounds(150, 113, 46, 14);
        contentPane.add(ApellidoLabel);

        ApellidoInput = new JTextField(Seleccinado.getApellido());
        ApellidoInput.setColumns(10);
        ApellidoInput.setBounds(198, 110, 86, 20);
        contentPane.add(ApellidoInput);

        JLabel NombreLabel = new JLabel("Nombre: ");
        NombreLabel.setBounds(150, 76, 46, 14);
        contentPane.add(NombreLabel);

        MailInput = new JTextField(Seleccinado.getUsuario());
        MailInput.setColumns(10);
        MailInput.setBounds(198, 141, 86, 20);
        contentPane.add(MailInput);

        JLabel MailLabel = new JLabel("Mail: ");
        MailLabel.setBounds(170, 144, 46, 14);
        contentPane.add(MailLabel);

        JLabel TelefonoLabel = new JLabel("Telefono: ");
        TelefonoLabel.setBounds(150, 172, 66, 14);
        contentPane.add(TelefonoLabel);

        TelefonoInput = new JTextField(String.valueOf(Seleccinado.getTelefono()));
        TelefonoInput.setColumns(10);
        TelefonoInput.setBounds(198, 169, 86, 20);
        contentPane.add(TelefonoInput);

        DniInput = new JTextField(String.valueOf(Seleccinado.getDNI()));
        DniInput.setColumns(10);
        DniInput.setBounds(198, 197, 86, 20);
        contentPane.add(DniInput);

        JLabel DNILabel = new JLabel("DNI:");
        DNILabel.setBounds(170, 200, 66, 14);
        contentPane.add(DNILabel);

        ContrasenaInput = new JTextField(Seleccinado.getContrasena());
        ContrasenaInput.setColumns(10);
        ContrasenaInput.setBounds(198, 224, 86, 20);
        contentPane.add(ContrasenaInput);

        JLabel ContrasenaLabel = new JLabel("Contraseña: ");
        ContrasenaLabel.setBounds(130, 227, 66, 14);
        contentPane.add(ContrasenaLabel);

        Calendar today = Calendar.getInstance();
        JButton EditarButton = new JButton("Guardar cambios");
        EditarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = "";
                String apellido = "";
                String mail = "";
                String contrasena = "";
                String estado_sus = "";
                int telefono = 0;
                int dni = 0;
                int puntos = 0;
                boolean flag = true;

                if (!VerificacionesRepository.Sololetras(NombreInput.getText())) {
                    flag = false;
                } else {
                    nombre = NombreInput.getText();
                }
                if (!VerificacionesRepository.Sololetras(ApellidoInput.getText())) {
                    flag = false;
                } else {
                    apellido = ApellidoInput.getText();
                }
                if (!Seleccinado.getUsuario().equalsIgnoreCase(MailInput.getText()) && !VerificacionesRepository.Mail(MailInput.getText())) {
                    flag = false;
                } else {
                    mail = MailInput.getText();
                }
                if (!VerificacionesRepository.SoloEnteros(TelefonoInput.getText())) {
                    flag = false;
                } else {
                    telefono = Integer.parseInt(TelefonoInput.getText());
                }
                if (!VerificacionesRepository.SoloEnteros(DniInput.getText())) {
                    flag = false;
                } else {
                    if (Seleccinado.getDNI() != Integer.parseInt(DniInput.getText()) && VerificacionesRepository.DNIExistente(Integer.parseInt(DniInput.getText()))) {
                        flag = false;
                    } else {
                        dni = Integer.parseInt(DniInput.getText());
                    }
                }
                if (ContrasenaInput.getText().isEmpty()) {
                    flag = false;
                } else {
                    contrasena = ContrasenaInput.getText();
                }
        
                if (flag) {
                    Seleccinado.setNombre(nombre);
                    Seleccinado.setApellido(apellido);
                    Seleccinado.setTelefono(telefono);
                    Seleccinado.setDNI(dni);
                    Seleccinado.setUsuario(mail);
                    Seleccinado.setContrasena(contrasena);
                    Seleccinado.setEstado_sus(estado_sus);
                    Seleccinado.setPuntos(puntos);

                    ClienteControlador controlador = new ClienteControlador();
                    controlador.updateCliente(Seleccinado);

                    new VerPerfilCliente(Seleccinado).setVisible(true);
                    dispose();
                }
            }
        });
        EditarButton.setBounds(89, 316, 150, 23);
        contentPane.add(EditarButton);

        JButton Volverbutton = new JButton("Cancelar");
        Volverbutton.setBounds(275, 316, 117, 23);
        contentPane.add(Volverbutton);
        
        JLabel lblObjetivo = new JLabel("Objetivo: ");
        lblObjetivo.setBounds(130, 258, 66, 14);
        contentPane.add(lblObjetivo);
        
        Objetivoinput = new JTextField(Seleccinado.getObjetivo());
        Objetivoinput.setColumns(10);
        Objetivoinput.setBounds(198, 255, 86, 20);
        contentPane.add(Objetivoinput);
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerPerfilCliente(Seleccinado).setVisible(true);
                dispose();
            }
        });
    }
}
