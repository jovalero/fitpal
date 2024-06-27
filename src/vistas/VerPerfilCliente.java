package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.SucursalControlador;
import modelo.Cliente;
import modelo.Sucursal;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controlador.EntrenadorControlador;

public class VerPerfilCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SucursalControlador sucursal;

	
	public VerPerfilCliente(Cliente cliente) {
		sucursal= new SucursalControlador();
		
		Sucursal LaSucursal= sucursal.getSucursalByid(cliente.getId_sucursal());
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TuPerfilLabel = new JLabel("Tu Perfil");
		TuPerfilLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		TuPerfilLabel.setBounds(186, 24, 117, 55);
		contentPane.add(TuPerfilLabel);
		
		JLabel Nombre = new JLabel("Nombre: " + cliente.getNombre());
		Nombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		Nombre.setBounds(44, 83, 308, 48);
		contentPane.add(Nombre);
		
		JLabel lblApellido = new JLabel("Apellido:" + cliente.getApellido());
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblApellido.setBounds(44, 123, 265, 55);
		contentPane.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI:" +String.valueOf(cliente.getDNI()));
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDni.setBounds(44, 162, 265, 55);
		contentPane.add(lblDni);
		
		JLabel lblMail = new JLabel("Mail:" + cliente.getUsuario());
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMail.setBounds(44, 206, 265, 55);
		contentPane.add(lblMail);
		
		JLabel lblSucursal = new JLabel("Sucursal:" + String.valueOf(LaSucursal.getId_sucursal())  );
		lblSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSucursal.setBounds(44, 249, 412, 55);
		contentPane.add(lblSucursal);
		
		JLabel lblTelefonoSucursal = new JLabel("Telefono Sucursal: " + String.valueOf(LaSucursal.getTelefono()) );
		lblTelefonoSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTelefonoSucursal.setBounds(44, 293, 412, 55);
		contentPane.add(lblTelefonoSucursal);
		
		JLabel lblDireccionSucursal = new JLabel("Direccion Sucursal: " + LaSucursal.getDireccion());
		lblDireccionSucursal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDireccionSucursal.setBounds(44, 334, 412, 55);
		contentPane.add(lblDireccionSucursal);
		
		JLabel lblObjetivo = new JLabel("Objetivo: " + cliente.getObjetivo());
		lblObjetivo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblObjetivo.setBounds(44, 379, 412, 55);
		contentPane.add(lblObjetivo);
		
		JButton btnNewButton = new JButton("Modificar Perfil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModificarPerfilCliente(cliente);
				dispose();
			}
		});
		if (cliente.getEstado_sus().equalsIgnoreCase("Desactivada")) {
			btnNewButton.setBounds(82, 463, 123, 40);
		}
		else {
			btnNewButton.setBounds(79, 536, 123, 40);
		}
	
		contentPane.add(btnNewButton);
		
		JButton btnVolverAtras = new JButton("Volver Atras");
		btnVolverAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cliente.getEstado_sus().equalsIgnoreCase("Desactivada")) {
					new HomeCliente(cliente);
					
				}
				else {
					new HomeClientePremium(cliente);
				}
				dispose();
			}
		});
		if (cliente.getEstado_sus().equalsIgnoreCase("Desactivada")) {
			btnVolverAtras.setBounds(289, 452, 123, 40);
		}
		else {
			btnVolverAtras.setBounds(288, 536, 123, 40);
		}
		contentPane.add(btnVolverAtras);
		
		if (cliente.getEstado_sus().equalsIgnoreCase("Activa")) {
			EntrenadorControlador entrenador= new EntrenadorControlador();
			JLabel lblEntrenador = new JLabel("Entrenador: Ninguno");
			if (entrenador.getEntrenadorByid(cliente.getId_entrenador())!=null) {
				lblEntrenador.setText("Entrenador: " + entrenador.getEntrenadorByid(cliente.getId_entrenador()).getNombre());
			}
			lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblEntrenador.setBounds(44, 431, 412, 55);
			contentPane.add(lblEntrenador);
		}

	}
}
