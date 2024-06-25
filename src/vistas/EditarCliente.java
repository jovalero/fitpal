package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.ClienteControlador;
import interfaces.VerificacionesRepository;
import modelo.Admin;
import modelo.Cliente;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class EditarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NombreInput;
	private JTextField ApellidoInput;
	private JTextField MailInput;
	private JTextField TelefonoInput;
	private JTextField DniInput;
	private JTextField ContrasenaInput;
	private JTextField PuntosInput;
	private boolean segunda=true;

	/**
	 * Create the frame.
	 */
	public EditarCliente(Cliente Seleccinado, Admin administrador) {
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel EditarSeleccinadoLabel = new JLabel("Editar Cliente");
		EditarSeleccinadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		EditarSeleccinadoLabel.setBounds(120, 0, 300, 62);
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
		
		
		JDateChooser dateChooser1 = new JDateChooser();
	    dateChooser1.setDateFormatString("dd/MM/yyyy");
	    
      
        Calendar today = Calendar.getInstance();
        dateChooser1.setMinSelectableDate(today.getTime());
		
		JPanel panel = new JPanel();
		
		JLabel Fecha_vencLabel = new JLabel("Fecha de vencimiento");
		panel.add(Fecha_vencLabel);
		panel.add(dateChooser1);
		panel.setBounds(150, 316, 182, 52);
		contentPane.add(panel);
		panel.setVisible(false);
		panel.setLayout(new FlowLayout());
		
		
		JComboBox Estado_sus = new JComboBox();
		Estado_sus.addItem(Seleccinado.getEstado_sus());
		Estado_sus.addItem("Activa");
		Estado_sus.addItem("Desactivada");
		Estado_sus.addItem("Suspendida");
		Estado_sus.setBounds(198, 255, 100, 22);
		contentPane.add(Estado_sus);
		if (Estado_sus.getSelectedItem().toString().equalsIgnoreCase("Activa")) {
			panel.setVisible(true);
			dateChooser1.setDate(Date.from(Seleccinado.getFechavenc().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
		}
		Estado_sus.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       
		        String estadoSeleccionado = Estado_sus.getSelectedItem().toString();
		        if (estadoSeleccionado.equalsIgnoreCase("Activa")) {
		 
		        		dateChooser1.setDate(new Date());
					
		            panel.setVisible(true);
		        } else {
		            panel.setVisible(false);
		        }
		    }
		});
		
		JLabel PuntosLabel = new JLabel("Puntos: ");
		PuntosLabel.setBounds(150, 284, 188, 14);
		contentPane.add(PuntosLabel);
		
		PuntosInput = new JTextField(String.valueOf(Seleccinado.getPuntos()));
		PuntosInput.setColumns(10);
		PuntosInput.setBounds(198, 284, 100, 20);
		contentPane.add(PuntosInput);

		JLabel Estado_susLabel = new JLabel("Estado de suscripcion:");
		Estado_susLabel.setBounds(81, 259, 188, 14);
		contentPane.add(Estado_susLabel);

		JButton EditarButton = new JButton("Guardar Cambios");
		EditarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = "";
				String apellido ="";
				String mail = "";
				String contrasena ="";
				String estado_sus="";
				int telefono = 0;
				int dni = 0;
				int puntos = 0;
				boolean flag = true;
				

				if (!VerificacionesRepository.Sololetras(NombreInput.getText())) {
					flag=false;
			
				}
				else {
					nombre=NombreInput.getText();
				}
				if (!VerificacionesRepository.Sololetras(ApellidoInput.getText())) {
					flag=false;
				}
				else {
					apellido=ApellidoInput.getText();
				}
				if (!Seleccinado.getUsuario().equalsIgnoreCase(MailInput.getText()) && !VerificacionesRepository.Mail(MailInput.getText())) {
					flag=false;
				
				}
				else {
					mail=MailInput.getText();
				}
				if (!VerificacionesRepository.SoloEnteros(TelefonoInput.getText())) {
					flag=false;
				}
				else {
					telefono=Integer.parseInt(TelefonoInput.getText());
				}
				if (!VerificacionesRepository.SoloEnteros(DniInput.getText())) {
					flag=false;
					
				}
				else {
					if (Seleccinado.getDNI()!=Integer.parseInt(DniInput.getText()) && VerificacionesRepository.DNIExistente(Integer.parseInt(DniInput.getText()))) {
						flag=false;
				
					}
					else {
						 dni=Integer.parseInt(DniInput.getText());
					}
				}
				if (ContrasenaInput.getText().isEmpty()) {
					flag=false;
					
				}
				else {
					contrasena=ContrasenaInput.getText();
				}
				if (!VerificacionesRepository.SoloEnteros(PuntosInput.getText())) {
					flag=false;
				}
				else {
					puntos=Integer.parseInt(PuntosInput.getText());
				}
				
				
				if (flag) {
					estado_sus=Estado_sus.getSelectedItem().toString();

					        Seleccinado.setFechavenc(dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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

		                    new TablaClientes(administrador).setVisible(true);
		                    dispose();
		                }
				
				}
			
		});
		EditarButton.setBounds(81, 413, 150, 23);
		contentPane.add(EditarButton);
		
	

	    
		
		JButton Volverbutton = new JButton("Cancelar Cambios");
		Volverbutton.setBounds(283, 413, 117, 23);
		contentPane.add(Volverbutton);
		Volverbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TablaClientes(administrador);
				dispose();
			}
		});
		

	}
}
