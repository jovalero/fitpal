package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.AdminControlador;
import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import interfaces.VerificacionesRepository;
import modelo.Admin;
import modelo.Cliente;
import modelo.Entrenador;
import modelo.Persona;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Inicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField emailinput;
    private JPasswordField contrasenainput;

    public static void main(String[] args) {
        ClienteControlador controladorc = new ClienteControlador();
        EntrenadorControlador controladore = new EntrenadorControlador();
        AdminControlador controladora = new AdminControlador();
        LinkedList<Persona> personas = new LinkedList<>();

        for (Cliente cliente : controladorc.getAllClientes()) {
            personas.add(cliente);
        }
        for (Entrenador entrenador : controladore.getAllEntrenadores()) {
            personas.add(entrenador);
        }
        for (Admin admin : controladora.getAllAdmin()) {
            personas.add(admin);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inicio frame = new Inicio(personas);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Inicio(LinkedList<Persona> personas) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Iniciodesesionlabel = new JLabel("Inicio de sesión");
        Iniciodesesionlabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        Iniciodesesionlabel.setBounds(137, 11, 172, 45);
        contentPane.add(Iniciodesesionlabel);

<<<<<<< HEAD
	}

	public Inicio(Admin administrador) {
		// TODO Auto-generated constructor stub
	}
=======
        emailinput = new JTextField();
        emailinput.setBounds(158, 67, 142, 20);
        contentPane.add(emailinput);
        emailinput.setColumns(10);

        JLabel emaillabel = new JLabel("Email: ");
        emaillabel.setBounds(108, 70, 46, 14);
        contentPane.add(emaillabel);

        JLabel contrasenalabel = new JLabel("Contraseña: ");
        contrasenalabel.setBounds(80, 117, 88, 14);
        contentPane.add(contrasenalabel);

        contrasenainput = new JPasswordField();
        contrasenainput.setBounds(158, 114, 142, 20);
        contentPane.add(contrasenainput);

        JLabel Errormail = new JLabel("Error al ingresar email");
        Errormail.setForeground(Color.RED);
        Errormail.setBounds(158, 89, 107, 14);
        contentPane.add(Errormail);
        Errormail.setVisible(false);

        JLabel Errorcontrasena = new JLabel("Error al ingresar contraseña");
        Errorcontrasena.setForeground(Color.RED);
        Errorcontrasena.setBounds(158, 145, 142, 14);
        contentPane.add(Errorcontrasena);
        Errorcontrasena.setVisible(false);

        JLabel noencontradolabel = new JLabel("Usuario no encontrado");
        noencontradolabel.setForeground(Color.RED);
        noencontradolabel.setBounds(166, 196, 114, 14);
        contentPane.add(noencontradolabel);
        noencontradolabel.setVisible(false);

        JButton botoningresar = new JButton("Ingresar");
        botoningresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                Persona[] arraypersona = personas.toArray(new Persona[0]);

                if (!VerificacionesRepository.MailInicio(emailinput.getText())) {
                    Errormail.setVisible(true);
                    flag = false;
                } else {
                    Errormail.setVisible(false);
                }

                if (contrasenainput.getText().isEmpty()) {
                    Errorcontrasena.setVisible(true);
                    flag = false;
                } else {
                    Errorcontrasena.setVisible(false);
                }
                if (flag) {
                    int indice = Persona.Iniciarsesion(emailinput.getText(), contrasenainput.getText(), personas);
                    if (indice != -1) {
                        if (arraypersona[indice] instanceof Entrenador) {
                            HomeEntrenador homeEntrenador = new HomeEntrenador((Entrenador) arraypersona[indice]);
                            homeEntrenador.setVisible(true);
                        } else if (arraypersona[indice] instanceof Admin) {
                            HomeAdmin homeAdmin = new HomeAdmin((Admin) arraypersona[indice]);
                            homeAdmin.setVisible(true);
                        } else if (arraypersona[indice] instanceof Cliente) {
                            Cliente cliente = (Cliente) arraypersona[indice];
                            if (cliente.getEstado_sus().equalsIgnoreCase("Premium")) {
                                HomeClientePremium homeClientePremium = new HomeClientePremium(cliente);
                                homeClientePremium.setVisible(true);
                            } else {
                                HomeCliente homeCliente = new HomeCliente(cliente);
                                homeCliente.setVisible(true);
                            }
                        }
                        dispose();
                    } else {
                        noencontradolabel.setVisible(true);
                    }
                }
            }
        });
        botoningresar.setBounds(176, 162, 89, 23);
        contentPane.add(botoningresar);

        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        botonSalir.setBounds(176, 196, 89, 23);
        contentPane.add(botonSalir);
    }
>>>>>>> origin/mar2
}



