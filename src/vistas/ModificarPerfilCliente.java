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
        
        JLabel nombreerror = new JLabel("Ingresa  un nombre porfavor\r\n");
		nombreerror.setForeground(Color.RED);
		nombreerror.setBounds(292, 76, 202, 14);
		nombreerror.setVisible(false);
		contentPane.add(nombreerror);
		
		JLabel ApellidoError = new JLabel("Ingresa Un Apellido");
		ApellidoError.setForeground(Color.RED);
		ApellidoError.setBounds(294, 113, 182, 14);
		ApellidoError.setVisible(false);
		contentPane.add(ApellidoError);
		
		JLabel MailError = new JLabel("Ingresa un mail valido");
		MailError.setForeground(Color.RED);
		MailError.setBounds(294, 144, 166, 14);
		MailError.setVisible(false);
		contentPane.add(MailError);
		
		JLabel TelefonoError = new JLabel("Ingresa un  telefono  valido");
		TelefonoError.setForeground(Color.RED);
		TelefonoError.setBounds(294, 172, 200, 14);
		TelefonoError.setVisible(false);
		contentPane.add(TelefonoError);
		
		JLabel lblNewLabel = new JLabel("Ingresa DNI");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(294, 200, 136, 14);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		JLabel Errorcontra = new JLabel("Ingresa contraseña valida");
		Errorcontra.setForeground(Color.RED);
		Errorcontra.setBounds(294, 227, 166, 14);
		Errorcontra.setVisible(false);
		contentPane.add(Errorcontra);
		
        JComboBox ObjetivoInput = new JComboBox();
        ObjetivoInput.addItem(Seleccinado.getObjetivo());
        ObjetivoInput.addItem("Perder Peso");
        ObjetivoInput.addItem("Ganar musculo");
        ObjetivoInput.setBounds(198, 255, 86, 22);
        contentPane.add(ObjetivoInput);
        JButton EditarButton = new JButton("Guardar cambios");
        EditarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = "";
                String apellido = "";
                String mail = "";
                String contrasena = "";
                int telefono = 0;
                int dni = 0;
                int puntos = 0;
                boolean flag = true;

                if (!VerificacionesRepository.Sololetras(NombreInput.getText())) {
                    flag = false;
                    nombreerror.setVisible(true);
                } else {
                    nombre = NombreInput.getText();
                    nombreerror.setVisible(false);
                }
                if (!VerificacionesRepository.Sololetras(ApellidoInput.getText())) {
                    flag = false;
                    ApellidoError.setVisible(true);
                } else {
                    apellido = ApellidoInput.getText();
                    ApellidoError.setVisible(false);
                }
                if (!Seleccinado.getUsuario().equalsIgnoreCase(MailInput.getText()) && !VerificacionesRepository.Mail(MailInput.getText())) {
                    flag = false;
                	MailError.setVisible(true);
                } else {
                    mail = MailInput.getText();
                	MailError.setVisible(false);
                }
                if (!VerificacionesRepository.SoloEnteros(TelefonoInput.getText())) {
                    flag = false;
                    TelefonoError.setVisible(true);
                } else {
                    telefono = Integer.parseInt(TelefonoInput.getText());
                    TelefonoError.setVisible(false);
                }
                if (!VerificacionesRepository.SoloEnteros(DniInput.getText())) {
                    flag = false;
                    lblNewLabel.setVisible(true);
                } else {
                    if (Seleccinado.getDNI() != Integer.parseInt(DniInput.getText()) && VerificacionesRepository.DNIExistente(Integer.parseInt(DniInput.getText()))) {
                        flag = false;
                        lblNewLabel.setVisible(true);
                    } else {
                        dni = Integer.parseInt(DniInput.getText());
                        lblNewLabel.setVisible(false);
                    }
                }
                if (ContrasenaInput.getText().isEmpty()) {
                    flag = false;
                    lblNewLabel.setVisible(true);
                } else {
                    contrasena = ContrasenaInput.getText();
                    lblNewLabel.setVisible(false);
                }
        
                if (flag) {
                	Seleccinado.setObjetivo(ObjetivoInput.getSelectedItem().toString());
                    Seleccinado.setNombre(nombre);
                    Seleccinado.setApellido(apellido);
                    Seleccinado.setTelefono(telefono);
                    Seleccinado.setDNI(dni);
                    Seleccinado.setUsuario(mail);
                    Seleccinado.setContrasena(contrasena);
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
        

        
      
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerPerfilCliente(Seleccinado).setVisible(true);
                dispose();
            }
        });
    }
}
