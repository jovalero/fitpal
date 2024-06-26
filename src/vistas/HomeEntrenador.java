package vistas;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import modelo.Entrenador;

public class HomeEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeEntrenador(Entrenador entrenador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Bienvenidolabel = new JLabel("Bienvenido " + entrenador.getNombre() + " " + entrenador.getApellido() +". ¿Qué desea hacer?");
		Bienvenidolabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bienvenidolabel.setBounds(43, 10, 691, 31);
		contentPane.add(Bienvenidolabel);

		JButton visualizarDatosButton = new JButton("Visualizar datos de entrenados");
		visualizarDatosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "panchito, 1.54m, 60kg");
			}
		});
		visualizarDatosButton.setBounds(52, 81, 241, 43);
		contentPane.add(visualizarDatosButton);

		JButton asignarRutinasButton = new JButton("Asignar rutinas de entrenamiento");
		asignarRutinasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "No hay rutinas en la base de datos");
			}
		});
		asignarRutinasButton.setBounds(52, 135, 242, 43);
		contentPane.add(asignarRutinasButton);

		JButton visualizarIncentivosButton = new JButton("Visualizar incentivos");
		visualizarIncentivosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "No hay incentivos");
			}
		});
		visualizarIncentivosButton.setBounds(52, 189, 240, 43);
		contentPane.add(visualizarIncentivosButton);

		JButton canjearIncentivosButton = new JButton("Canjear incentivos");
		canjearIncentivosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "No existen incentivos que canjear");
			}
		});
		canjearIncentivosButton.setBounds(52, 243, 239, 43);
		contentPane.add(canjearIncentivosButton);
		
		JButton salirButton = new JButton("Salir");
		salirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "CHAU");
				System.exit(0);
			}
		});
		salirButton.setBounds(53, 296, 240, 43);
		contentPane.add(salirButton);
		
        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar sesión y volver a la ventana de inicio de sesión
                Inicio inicio = new Inicio(null);  // personas es la lista de usuarios
                inicio.setVisible(true);
                dispose();  // Cierra la ventana actual
            }
        });
        botonCerrarSesion.setBounds(500, 300, 130, 23);
        contentPane.add(botonCerrarSesion);
        
        JButton btnAsignarDieta = new JButton("Asignar Dieta");
        btnAsignarDieta.setBounds(330, 82, 242, 43);
        contentPane.add(btnAsignarDieta);
        btnAsignarDieta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DietaEntrenador dietaEntrenador = new DietaEntrenador(entrenador);
                dietaEntrenador.setVisible(true);
                dispose();  // Cierra la ventana actual si es necesario
            }
        });
	}
}

