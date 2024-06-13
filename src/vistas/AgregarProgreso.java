package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import modelo.Progreso;
import controlador.ProgresoControlador;

public class AgregarProgreso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdCliente;
    private JFormattedTextField txtFecha;
    private JLabel lblRutaImagen;
    private ProgresoControlador controlador;
    private TablaProgreso tablaProgreso;

    public AgregarProgreso(ProgresoControlador controlador, TablaProgreso tablaProgreso) {
    	this.setVisible(true);
        this.controlador = controlador;
        this.tablaProgreso = tablaProgreso;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(10, 14, 80, 14);
        contentPane.add(lblIdCliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(100, 11, 86, 20);
        contentPane.add(txtIdCliente);
        txtIdCliente.setColumns(10);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(10, 52, 150, 14);
        contentPane.add(lblFecha);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            txtFecha = new JFormattedTextField(formatter);
        } catch (IllegalArgumentException e) {
            txtFecha = new JFormattedTextField();
        }
        txtFecha.setBounds(160, 49, 120, 20);
        contentPane.add(txtFecha);
        txtFecha.setColumns(10);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setBounds(10, 90, 80, 14);
        contentPane.add(lblImagen);

        JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(AgregarProgreso.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String rutaImagen = fileChooser.getSelectedFile().getAbsolutePath();
                    lblRutaImagen.setText("Ruta de la Imagen: " + rutaImagen);
                }
            }
        });
        btnSeleccionarImagen.setBounds(160, 87, 150, 20);
        contentPane.add(btnSeleccionarImagen);

        lblRutaImagen = new JLabel("Ruta de la Imagen:");
        lblRutaImagen.setBounds(10, 115, 300, 14);
        contentPane.add(lblRutaImagen);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(10, 145, 80, 14);
        contentPane.add(lblPeso);

        JTextField txtPeso = new JTextField();
        txtPeso.setBounds(100, 142, 86, 20);
        contentPane.add(txtPeso);
        txtPeso.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = Integer.parseInt(txtIdCliente.getText());
                    LocalDate fecha = LocalDate.parse(txtFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String imagen = lblRutaImagen.getText().substring("Ruta de la Imagen: ".length());
                    double peso = Double.parseDouble(txtPeso.getText());

                    Progreso progreso = new Progreso(0, idCliente, fecha, imagen, peso);
                    controlador.addProgreso(progreso);
                    JOptionPane.showMessageDialog(null, "Progreso agregado correctamente");
                    tablaProgreso.actualizarTabla();
                    dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Use el formato YYYY-MM-DD");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de número para el peso o ID de cliente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar progreso");
                }
            }
        });
        btnGuardar.setBounds(160, 200, 89, 23);
        contentPane.add(btnGuardar);
    }
}