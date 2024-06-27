package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Rutina;
import controlador.RutinaControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarRutina extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idRutinaInput;
    private JTextField estadoInput;
    private JTextField descripcionInput;
    private JTextField objetivoInput;

    public EditarRutina(RutinaControlador controlador, Rutina rutina, RutinaTabla rutinaTabla) {

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambio aquí
        setBounds(100, 100, 542, 361);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel editarLabel = new JLabel("Editar Rutina");
        editarLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        editarLabel.setBounds(120, 0, 300, 62);
        contentPane.add(editarLabel);

        idRutinaInput = new JTextField(String.valueOf(rutina.getIdRutina()));
        idRutinaInput.setEditable(false); // El ID no se puede editar
        idRutinaInput.setBounds(198, 73, 86, 20);
        contentPane.add(idRutinaInput);
        idRutinaInput.setColumns(10);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(150, 113, 46, 14);
        contentPane.add(estadoLabel);

        estadoInput = new JTextField(rutina.getEstado());
        estadoInput.setColumns(10);
        estadoInput.setBounds(222, 110, 140, 20);
        contentPane.add(estadoInput);

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(150, 144, 66, 14);
        contentPane.add(descripcionLabel);

        descripcionInput = new JTextField(rutina.getDescripcion());
        descripcionInput.setColumns(10);
        descripcionInput.setBounds(222, 141, 140, 20);
        contentPane.add(descripcionInput);

        JLabel objetivoLabel = new JLabel("Objetivo:");
        objetivoLabel.setBounds(150, 172, 66, 14);
        contentPane.add(objetivoLabel);

        objetivoInput = new JTextField(rutina.getObjetivo());
        objetivoInput.setColumns(10);
        objetivoInput.setBounds(222, 172, 140, 20);
        contentPane.add(objetivoInput);

        JButton guardarCambiosButton = new JButton("Guardar cambios");
        guardarCambiosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String estado = estadoInput.getText();
                String descripcion = descripcionInput.getText();
                String objetivo = objetivoInput.getText();

                if (estado.isEmpty() || descripcion.isEmpty() || objetivo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                } else {
                    rutina.setEstado(estado);
                    rutina.setDescripcion(descripcion);
                    rutina.setObjetivo(objetivo);

                    controlador.updateRutina(rutina);
                    rutinaTabla.actualizarTabla(); // Actualizar la tabla de rutinas

                    // Cerrar la ventana de edición
                    dispose();
                }
            }
        });
        guardarCambiosButton.setBounds(162, 228, 150, 23);
        contentPane.add(guardarCambiosButton);
    }
}
