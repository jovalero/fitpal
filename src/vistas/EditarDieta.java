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

public class EditarDieta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldDescripcion;

    private Dieta dieta;
    private DietaControlador dietaControlador;
    private DietaEntrenador dietaEntrenador; // Referencia a DietaEntrenador

    // Constructor que recibe Dieta y DietaEntrenador
    public EditarDieta(Dieta dieta, DietaEntrenador dietaEntrenador) {
        this.dieta = dieta;
        this.dietaControlador = new DietaControlador();
        this.dietaEntrenador = dietaEntrenador; // Guardar referencia a DietaEntrenador recibida

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEditarDieta = new JLabel("Editar Dieta");
        lblEditarDieta.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEditarDieta.setBounds(162, 11, 105, 20);
        contentPane.add(lblEditarDieta);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(47, 66, 62, 14);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(119, 63, 263, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);
        textFieldNombre.setText(dieta.getNombreDieta());

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(29, 105, 80, 14);
        contentPane.add(lblDescripcion);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBounds(119, 102, 263, 20);
        contentPane.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);
        textFieldDescripcion.setText(dieta.getDescripcionDieta());

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        btnGuardar.setBounds(162, 175, 126, 23);
        contentPane.add(btnGuardar);
    }

    private void guardarCambios() {
        String nombre = textFieldNombre.getText();
        String descripcion = textFieldDescripcion.getText();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return;
        }

        dieta.setNombreDieta(nombre);
        dieta.setDescripcionDieta(descripcion);

        dietaControlador.updateDieta(dieta);
        JOptionPane.showMessageDialog(null, "Dieta actualizada correctamente.");

        // Cerrar la ventana después de guardar cambios
        dispose();

        // Volver a hacer visible la ventana DietaEntrenador
        dietaEntrenador.setVisible(true);
    }
}


