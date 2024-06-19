package vistas;

import java.awt.EventQueue;

import javax.management.ValueExp;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;

import javax.swing.JLabel;
import java.awt.Font;

public class FormularioClienteNuevo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NombreInput;
	private JTextField ApellidoInput;
	private JTextField MailInput;
	private JTextField TelefonoInput;
	private JTextField DNIInput;
	private JTextField ContrasenaInput;
	private JTextField PesoInput;
	private JTextField AlturaInput;
	
	public FormularioClienteNuevo(Cliente Cliente) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NombreInput = new JTextField(Cliente.getNombre());
		NombreInput.setBounds(198, 73, 86, 20);
		contentPane.add(NombreInput);
		NombreInput.setColumns(10);
		
		JLabel ApellidoLabel = new JLabel("Apellido: ");
		ApellidoLabel.setBounds(150, 113, 66, 14);
		contentPane.add(ApellidoLabel);
		
		ApellidoInput = new JTextField(Cliente.getApellido());
		ApellidoInput.setColumns(10);
		ApellidoInput.setBounds(198, 110, 86, 20);
		contentPane.add(ApellidoInput);
		
		JLabel NombreLabel = new JLabel("Nombre: ");
		NombreLabel.setBounds(150, 76, 66, 14);
		contentPane.add(NombreLabel);
		
		MailInput = new JTextField(Cliente.getUsuario());
		MailInput.setColumns(10);
		MailInput.setBounds(198, 141, 86, 20);
		contentPane.add(MailInput);
		
		JLabel MailLabel = new JLabel("Mail: ");
		MailLabel.setBounds(160, 144, 66, 14);
		contentPane.add(MailLabel);
		
		JLabel TelefonoLabel = new JLabel("Telefono: ");
		TelefonoLabel.setBounds(140, 172, 86, 14);
		contentPane.add(TelefonoLabel);
		
		TelefonoInput = new JTextField(String.valueOf(Cliente.getTelefono()) );
		TelefonoInput.setColumns(10);
		TelefonoInput.setBounds(198, 169, 86, 20);
		contentPane.add(TelefonoInput);
		
		DNIInput = new JTextField(String.valueOf(Cliente.getDNI()));
		DNIInput.setColumns(10);
		DNIInput.setBounds(198, 197, 86, 20);
		contentPane.add(DNIInput);
		
		JLabel DNILabel = new JLabel("DNI:");
		DNILabel.setBounds(164, 200, 86, 14);
		contentPane.add(DNILabel);
		
		ContrasenaInput = new JTextField(Cliente.getContrasena());
		ContrasenaInput.setColumns(10);
		ContrasenaInput.setBounds(198, 224, 86, 20);
		ContrasenaInput.setText("PrimeraContraseña!");
		contentPane.add(ContrasenaInput);
		
		JLabel ContrasenaLabel = new JLabel("Contraseña: ");
		ContrasenaLabel.setBounds(122, 227, 104, 14);
		contentPane.add(ContrasenaLabel);
		
		PesoInput = new JTextField(String.valueOf(Cliente.getPeso()));
		PesoInput.setColumns(10);
		PesoInput.setBounds(198, 255, 86, 20);
		PesoInput.setText("0");
		contentPane.add(PesoInput);
		
		JLabel PesoLabel = new JLabel("Peso: ");
		PesoLabel.setBounds(150, 255, 76, 14);
		contentPane.add(PesoLabel);
		
		JLabel AlturaLabel = new JLabel("Altura: ");
		AlturaLabel.setBounds(150, 286, 81, 14);
		contentPane.add(AlturaLabel);
		
		AlturaInput = new JTextField(String.valueOf(Cliente.getAltura()));
		AlturaInput.setColumns(10);
		AlturaInput.setBounds(198, 286, 86, 20);
		AlturaInput.setText("0");
		contentPane.add(AlturaInput);
		
		JLabel lblNewLabel = new JLabel("Formulario Nuevo Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(64, 11, 308, 50);
		contentPane.add(lblNewLabel);
	}

}
