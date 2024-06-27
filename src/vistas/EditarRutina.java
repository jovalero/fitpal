package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.RutinaControlador;
import modelo.Admin;
import modelo.Rutina;

public class EditarRutina extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textEstado;
    private JTextField textDescripcion;
    private JTextField textObjetivo;
    private RutinaControlador controlador;

    public EditarRutina(Rutina rutina, Admin administrador) {
        controlador = new RutinaControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(30, 30, 80, 14);
        contentPane.add(lblEstado);

        textEstado = new JTextField(rutina.getEstado());
        textEstado.setBounds(120, 27, 200, 20);
        contentPane.add(textEstado);
        textEstado.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 70, 80, 14);
        contentPane.add(lblDescripcion);

        textDescripcion = new JTextField(rutina.getDescripcion());
        textDescripcion.setBounds(120, 67, 200, 20);
        contentPane.add(textDescripcion);
        textDescripcion.setColumns(10);

        JLabel lblObjetivo = new JLabel("Objetivo:");
        lblObjetivo.setBounds(30, 110, 80, 14);
        contentPane.add(lblObjetivo);

        textObjetivo = new JTextField(rutina.getObjetivo());
        textObjetivo.setBounds(120, 107, 200, 20);
        contentPane.add(textObjetivo);
        textObjetivo.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rutina.setEstado(textEstado.getText());
                rutina.setDescripcion(textDescripcion.getText());
                rutina.setObjetivo(textObjetivo.getText());

                if (controlador.updateRutina(rutina)) {
                    JOptionPane.showMessageDialog(null, "Rutina actualizada");
                    new RutinaTabla(administrador);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar la rutina");
                }
            }
        });
        btnGuardar.setBounds(120, 150, 100, 23);
        contentPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RutinaTabla(administrador);
                dispose();
            }
        });
        btnCancelar.setBounds(230, 150, 100, 23);
        contentPane.add(btnCancelar);
    }
}
