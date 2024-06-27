package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.EntrenadorControlador;
import interfaces.VerificacionesRepository;
import modelo.Entrenador;


public class ModificarPerfilEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
  
    private JTextField NombreInput;
    private JTextField ApellidoInput;
    private JTextField MailInput;
    private JTextField TelefonoInput;
    private JTextField DniInput;
    private JTextField ContrasenaInput;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ModificarPerfilEntrenador(Entrenador Seleccinado) {
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


                    EntrenadorControlador controlador = new  EntrenadorControlador();
                    controlador.updateEntrenador(Seleccinado);

                    new VerPerfilEntrenador(Seleccinado).setVisible(true);
                    dispose();
                }
            }
        });
        EditarButton.setBounds(84, 269, 150, 23);
        contentPane.add(EditarButton);

        JButton Volverbutton = new JButton("Cancelar");
        Volverbutton.setBounds(269, 269, 117, 23);
        contentPane.add(Volverbutton);
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerPerfilEntrenador(Seleccinado).setVisible(true);
                dispose();
            }
        });
    }

	}

