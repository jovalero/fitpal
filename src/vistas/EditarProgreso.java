package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import modelo.Progreso;
import controlador.ProgresoControlador;

public class EditarProgreso extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdCliente;
    private JDateChooser dateChooser;
    private JLabel lblRutaImagen;
    private JTextField txtPeso;
    private ProgresoControlador controlador;
    private Progreso progreso;
    private TablaProgreso tablaProgreso;

    public EditarProgreso(ProgresoControlador controlador, Progreso progreso, TablaProgreso tablaProgreso) {
        this.controlador = controlador;
        this.progreso = progreso;
        this.tablaProgreso = tablaProgreso;
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Editar Progreso");
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
        txtIdCliente.setText(String.valueOf(progreso.getIdCliente()));
        contentPane.add(txtIdCliente);
        txtIdCliente.setColumns(10);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 52, 150, 14);
        contentPane.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(java.util.Date.from(progreso.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
                int returnVal = fileChooser.showOpenDialog(EditarProgreso.this);
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

        txtPeso = new JTextField();
        txtPeso.setBounds(100, 142, 86, 20);
        txtPeso.setText(String.valueOf(progreso.getPeso()));
        contentPane.add(txtPeso);
        txtPeso.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = Integer.parseInt(txtIdCliente.getText());
                    LocalDate fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String imagen = lblRutaImagen.getText().substring("Ruta de la Imagen: ".length());
                    double peso = Double.parseDouble(txtPeso.getText());

                    progreso.setIdCliente(idCliente);
                    progreso.setFecha(fecha);
                    progreso.setImagen(imagen);
                    progreso.setPeso(peso);

                    controlador.updateProgreso(progreso);
                    JOptionPane.showMessageDialog(null, "Progreso actualizado correctamente");
                    tablaProgreso.actualizarTabla();
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar progreso");
                }
            }
        });
        btnGuardar.setBounds(160, 200, 89, 23);
        contentPane.add(btnGuardar);
    }
}


