package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.DietaControlador;
import modelo.Dieta;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarDieta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldDescripcion;

    private DietaControlador dietaControlador;

    public AgregarDieta() {
        this.dietaControlador = new DietaControlador();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAgregarDieta = new JLabel("Agregar Nueva Dieta");
        lblAgregarDieta.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAgregarDieta.setBounds(130, 11, 184, 20);
        contentPane.add(lblAgregarDieta);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(47, 66, 62, 14);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(119, 63, 263, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(29, 105, 80, 14);
        contentPane.add(lblDescripcion);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBounds(119, 102, 263, 20);
        contentPane.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarDieta();
            }
        });
        btnGuardar.setBounds(162, 175, 126, 23);
        contentPane.add(btnGuardar);
    }

    private void agregarDieta() {
        String nombre = textFieldNombre.getText();
        String descripcion = textFieldDescripcion.getText();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return;
        }

        Dieta nuevaDieta = new Dieta(nombre, descripcion);
        dietaControlador.addDieta(nuevaDieta);
        JOptionPane.showMessageDialog(null, "Nueva dieta agregada correctamente.");

        // Cerrar la ventana después de agregar la dieta
        dispose();

        DietaEntrenador dietaEntrenador = new DietaEntrenador(null);
		// Si tienes una referencia a la instancia de DietaEntrenador, puedes hacerla visible nuevamente
        // Suponiendo que dietaEntrenador es la instancia existente de DietaEntrenador que ya está abierta
        dietaEntrenador.setVisible(true); // Asegúrate de tener un método setVisible en DietaEntrenador para hacerlo visible
    }
}

