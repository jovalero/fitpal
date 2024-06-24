package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import modelo.Progreso;
import controlador.ProgresoControlador;

public class AgregarProgreso extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdCliente;
    private JDateChooser dateChooser;
    private JLabel lblRutaImagen;
    private ProgresoControlador controlador;
    private TablaProgreso tablaProgreso;

    public AgregarProgreso(ProgresoControlador controlador, TablaProgreso tablaProgreso) {
        this.controlador = controlador;
        this.tablaProgreso = tablaProgreso;
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Agregar Progreso");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(tablaProgreso);
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

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 52, 150, 14);
        contentPane.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(160, 49, 120, 20);
        contentPane.add(dateChooser);

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
                    // Validación de campos
                    String idClienteStr = txtIdCliente.getText();
                    String pesoStr = txtPeso.getText();
                    LocalDate fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String rutaImagen = lblRutaImagen.getText().substring("Ruta de la Imagen: ".length());

                    if (idClienteStr.isEmpty() || pesoStr.isEmpty() || rutaImagen.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int idCliente = Integer.parseInt(idClienteStr);
                    double peso = Double.parseDouble(pesoStr);

                    Progreso progreso = new Progreso(0, idCliente, fecha, rutaImagen, peso);
                    controlador.addProgreso(progreso);
                    JOptionPane.showMessageDialog(null, "Progreso agregado correctamente");
                    tablaProgreso.actualizarTabla();
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de número para el peso o ID de cliente", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Use el formato YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar progreso", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnGuardar.setBounds(160, 200, 89, 23);
        contentPane.add(btnGuardar);
    }
}


