package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.VerificacionesRepository;
import modelo.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarCliente extends JFrame {

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




	/**
	 * Create the frame.
	 */
	public RegistrarCliente(Admin administrador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Registrarclientelabel = new JLabel("Registrar Cliente");
		Registrarclientelabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Registrarclientelabel.setBounds(120, 0, 243, 62);
		contentPane.add(Registrarclientelabel);
		
		NombreInput = new JTextField();
		NombreInput.setBounds(198, 73, 86, 20);
		contentPane.add(NombreInput);
		NombreInput.setColumns(10);
		
		JLabel ApellidoLabel = new JLabel("Apellido: ");
		ApellidoLabel.setBounds(150, 113, 66, 14);
		contentPane.add(ApellidoLabel);
		
		ApellidoInput = new JTextField();
		ApellidoInput.setColumns(10);
		ApellidoInput.setBounds(198, 110, 86, 20);
		contentPane.add(ApellidoInput);
		
		JLabel NombreLabel = new JLabel("Nombre: ");
		NombreLabel.setBounds(150, 76, 66, 14);
		contentPane.add(NombreLabel);
		
		MailInput = new JTextField();
		MailInput.setColumns(10);
		MailInput.setBounds(198, 141, 86, 20);
		contentPane.add(MailInput);
		
		JLabel MailLabel = new JLabel("Mail: ");
		MailLabel.setBounds(160, 144, 66, 14);
		contentPane.add(MailLabel);
		
		JLabel TelefonoLabel = new JLabel("Telefono: ");
		TelefonoLabel.setBounds(140, 172, 86, 14);
		contentPane.add(TelefonoLabel);
		
		TelefonoInput = new JTextField();
		TelefonoInput.setColumns(10);
		TelefonoInput.setBounds(198, 169, 86, 20);
		contentPane.add(TelefonoInput);
		
		DNIInput = new JTextField();
		DNIInput.setColumns(10);
		DNIInput.setBounds(198, 197, 86, 20);
		contentPane.add(DNIInput);
		
		JLabel DNILabel = new JLabel("DNI:");
		DNILabel.setBounds(164, 200, 86, 14);
		contentPane.add(DNILabel);
		
		ContrasenaInput = new JTextField();
		ContrasenaInput.setColumns(10);
		ContrasenaInput.setBounds(198, 224, 86, 20);
		ContrasenaInput.setText("PrimeraContraseña!");
		contentPane.add(ContrasenaInput);
		
		JLabel ContrasenaLabel = new JLabel("Contraseña: ");
		ContrasenaLabel.setBounds(122, 227, 104, 14);
		contentPane.add(ContrasenaLabel);
		
		PesoInput = new JTextField();
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
		
		AlturaInput = new JTextField();
		AlturaInput.setColumns(10);
		AlturaInput.setBounds(198, 286, 86, 20);
		AlturaInput.setText("0");
		contentPane.add(AlturaInput);
		
		JButton RegistrarButton = new JButton("Registrar Cliente");
		RegistrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Nombre="";
				String Apellido="";
				String Mail="";
				String Contrasena="";
				int Telefono=0;
				int DNI=0;
				double Peso=0;
				double Altura=0;
				boolean flag=true;
				int sucursal=administrador.getId_sucursal();
				if (!VerificacionesRepository.Sololetras(NombreInput.getText())) {
					flag=false;
			
				}
				else {
					Nombre=NombreInput.getText();
				}
				if (!VerificacionesRepository.Sololetras(ApellidoInput.getText())) {
					flag=false;
				}
				else {
					Apellido=ApellidoInput.getText();
				}
				if (!VerificacionesRepository.Mail(MailInput.getText())) {
					flag=false;
				
				}
				else {
					Mail=MailInput.getText();
				}
				if (!VerificacionesRepository.SoloEnteros(TelefonoInput.getText())) {
					flag=false;
					
				
				}
				else {
					Telefono=Integer.parseInt(TelefonoInput.getText());
				}
				if (!VerificacionesRepository.SoloEnteros(DNIInput.getText())) {
					flag=false;
					
				}
				else {
					if (VerificacionesRepository.DNIExistente(Integer.parseInt(DNIInput.getText()))) {
						flag=false;
				
					}
					else {
						 DNI=Integer.parseInt(DNIInput.getText());
					}
				}
				if (ContrasenaInput.getText().isEmpty()) {
					flag=false;
					
				}
				else {
					Contrasena=ContrasenaInput.getText();
				}
				if (!VerificacionesRepository.SoloDoubles(PesoInput.getText())) {
					flag=false;
					
				}
				else {
					 Peso=Double.parseDouble(PesoInput.getText());
				}
				if (!VerificacionesRepository.SoloDoubles(AlturaInput.getText())) {
					flag=false;
					
				}
				else {
					Altura=Double.parseDouble(AlturaInput.getText());
				}
				
				if (flag) {
					boolean registro=administrador.RegistrarCliente(sucursal,Nombre,Apellido,Mail,DNI,Telefono,Contrasena,Peso,Altura);
					if (registro) {
						TablaClientes nuevatabla= new TablaClientes(administrador);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"Ocurrio un error");    
					}
				}
				else {
					JOptionPane.showMessageDialog(RegistrarButton, "No funciona");
				}
			}
		});
		RegistrarButton.setBounds(107, 339, 113, 23);
		contentPane.add(RegistrarButton);
		
		JButton Volverbutton = new JButton("Cancelar");
		Volverbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TablaClientes(administrador);
				dispose();

			}
		});
		Volverbutton.setBounds(274, 339, 113, 23);
		contentPane.add(Volverbutton);
	}
}
