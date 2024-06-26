package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.EjercicioControlador;
import modelo.Ejercicio;

public class RegistrarEjercicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private EjercicioControlador controlador;
    private JTextField txtNombre;
    private JTextField txtMaquina;
    private JTextField txtMusculo;
    private JTextField txtDescripcion;
    private JTextField txtVideo;
    private JTextField txtIdArea;

	public RegistrarEjercicio(JFrame parent) {
		this.controlador = new EjercicioControlador();
	
		setTitle("Registrar Ejercicio");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 80, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 10, 200, 25);
        getContentPane().add(txtNombre);

        JLabel lblMaquina = new JLabel("Máquina:");
        lblMaquina.setBounds(10, 50, 80, 25);
        getContentPane().add(lblMaquina);

        txtMaquina = new JTextField();
        txtMaquina.setBounds(100, 50, 200, 25);
        getContentPane().add(txtMaquina);

        JLabel lblMusculo = new JLabel("Músculo:");
        lblMusculo.setBounds(10, 90, 80, 25);
        getContentPane().add(lblMusculo);

        txtMusculo = new JTextField();
        txtMusculo.setBounds(100, 90, 200, 25);
        getContentPane().add(txtMusculo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 130, 80, 25);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(100, 130, 200, 25);
        getContentPane().add(txtDescripcion);

        JLabel lblVideo = new JLabel("Video:");
        lblVideo.setBounds(10, 170, 80, 25);
        getContentPane().add(lblVideo);

        txtVideo = new JTextField();
        txtVideo.setBounds(100, 170, 200, 25);
        getContentPane().add(txtVideo);

        JLabel lblIdArea = new JLabel("ID Área:");
        lblIdArea.setBounds(10, 210, 80, 25);
        getContentPane().add(lblIdArea);

        txtIdArea = new JTextField();
        txtIdArea.setBounds(100, 210, 200, 25);
        getContentPane().add(txtIdArea);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ejercicio ejercicio = new Ejercicio(0, txtNombre.getText(), txtMaquina.getText(), txtMusculo.getText(), txtDescripcion.getText(), txtVideo.getText(), Integer.parseInt(txtIdArea.getText()));
                controlador.addEjercicio(ejercicio);
                ((EjercicioTabla) parent).actualizarTabla();
                dispose();
            }
        });
        btnGuardar.setBounds(320, 210, 100, 25);
        getContentPane().add(btnGuardar);
	}

}
