package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.SucursalControlador;
import modelo.Entrenador;
import modelo.Sucursal;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerPerfilEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SucursalControlador sucursal;



	/**
	 * Create the frame.
	 */
	public VerPerfilEntrenador(Entrenador entrenador) {
		sucursal= new SucursalControlador();
	
		Sucursal LaSucursal= sucursal.getSucursalByid(entrenador.getId_sucursal());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tu Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(172, 11, 167, 64);
		contentPane.add(lblNewLabel);
		
		JLabel Nombre = new JLabel("Nombre: " + entrenador.getNombre());
		Nombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		Nombre.setBounds(25, 71, 308, 48);
		contentPane.add(Nombre);
		
		JLabel lblApellido = new JLabel("Apellido: " + entrenador.getApellido());
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblApellido.setBounds(25, 111, 265, 55);
		contentPane.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI: " + String.valueOf(entrenador.getDNI()));
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDni.setBounds(25, 150, 265, 55);
		contentPane.add(lblDni);
		
		JLabel lblMail = new JLabel("Mail: " + entrenador.getUsuario());
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMail.setBounds(25, 194, 265, 55);
		contentPane.add(lblMail);
		
		JLabel lblSucursal = new JLabel("Sucursal:" + String.valueOf(LaSucursal.getId_sucursal()));
		lblSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSucursal.setBounds(25, 237, 412, 55);
		contentPane.add(lblSucursal);
		
		JLabel lblTelefonoSucursal = new JLabel("Telefono Sucursal: " + String.valueOf(LaSucursal.getTelefono()));
		lblTelefonoSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTelefonoSucursal.setBounds(25, 281, 412, 55);
		contentPane.add(lblTelefonoSucursal);
		
		JLabel lblDireccionSucursal = new JLabel("Direccion Sucursal: " + LaSucursal.getDireccion());
		lblDireccionSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDireccionSucursal.setBounds(25, 322, 412, 55);
		contentPane.add(lblDireccionSucursal);
		
		JLabel lblNumentrenados = new JLabel("NumEntrenados: " + String.valueOf(entrenador.getNumentrenados()));
		lblNumentrenados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNumentrenados.setBounds(25, 367, 412, 55);
		contentPane.add(lblNumentrenados);
		
		JButton btnNewButton = new JButton("Modificar Perfil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModificarPerfilEntrenador(entrenador);
			}
		});
		btnNewButton.setBounds(63, 446, 105, 55);
		contentPane.add(btnNewButton);
		
		JButton btnVolverAtras = new JButton("Volver Atras");
		btnVolverAtras.setBounds(243, 446, 105, 55);
		contentPane.add(btnVolverAtras);
	}

}
