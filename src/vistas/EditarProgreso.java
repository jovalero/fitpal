package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import modelo.Progreso;
import controlador.ProgresoControlador;

public class EditarProgreso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdCliente;
    private JTextField txtFecha;
    private JTextField txtImagen;
    private JTextField txtPeso;
    private ProgresoControlador controlador;
    private Progreso progreso;
    private TablaProgreso tablaProgreso;

    public EditarProgreso(ProgresoControlador controlador, Progreso progreso, TablaProgreso tablaProgreso) {
        this.controlador = controlador;
        this.progreso = progreso;
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
        txtIdCliente.setText(String.valueOf(progreso.getIdCliente()));
        contentPane.add(txtIdCliente);
        txtIdCliente.setColumns(10);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(10, 52, 150, 14);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(160, 49, 86, 20);
        txtFecha.setText(progreso.getFecha().toString());
        contentPane.add(txtFecha);
        txtFecha.setColumns(10);

        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setBounds(10, 90, 80, 14);
        contentPane.add(lblImagen);

        txtImagen = new JTextField();
        txtImagen.setBounds(100, 87, 86, 20);
        txtImagen.setText(progreso.getImagen());
        contentPane.add(txtImagen);
        txtImagen.setColumns(10);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(10, 128, 80, 14);
        contentPane.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(100, 125, 86, 20);
        txtPeso.setText(String.valueOf(progreso.getPeso()));
        contentPane.add(txtPeso);
        txtPeso.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = Integer.parseInt(txtIdCliente.getText());
                    LocalDate fecha = LocalDate.parse(txtFecha.getText());
                    String imagen = txtImagen.getText();
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
