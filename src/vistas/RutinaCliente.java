package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import controlador.RutinaControlador;
import modelo.Cliente;
import modelo.Rutina;

public class RutinaCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private RutinaControlador controlador;
    private JButton btnVolver;

    public RutinaCliente(Cliente cliente) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new RutinaControlador();

        String[] columnNames = { "ID Rutina", "Estado", "Descripción", "Objetivo", "Creada" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 300);
        contentPane.add(scrollPane);

        btnVolver = new JButton("Volver al Home");
        btnVolver.setBounds(235, 321, 258, 30);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeCliente(cliente).setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);

        actualizarTabla();
    }

    public void actualizarTabla() {
        model.setRowCount(0); // Limpiar filas existentes
        List<Rutina> rutinas = controlador.getAllRutinasPredeterminadas(); // Obtener rutinas predeterminadas

        for (Rutina rutina : rutinas) {
            model.addRow(new Object[] {
                rutina.getIdRutina(),
                rutina.getEstado(),
                rutina.getDescripcion(),
                rutina.getObjetivo(),
                rutina.getCreada() // Si necesitas mostrar la fecha de creación
            });
        }
    }

        
        private void filtrar(String criterio) {
            model.setRowCount(0); // Limpiar filas existentes
            List<Rutina> rutinas = controlador.getAllRutinasPredeterminadas(); // Obtener rutinas predeterminadas
            String criterioLower = criterio.toLowerCase();

            for (Rutina rutina : rutinas) {
                if (rutina.getDescripcion().toLowerCase().contains(criterioLower)
                        || rutina.getEstado().toLowerCase().contains(criterioLower)) {
                    model.addRow(new Object[] {
                        rutina.getIdRutina(),
                        rutina.getEstado(),
                        rutina.getDescripcion(),
                        rutina.getObjetivo(),
                        rutina.getCreada() // Si necesitas mostrar la fecha de creación
                    });
                }
            }
        

    }




}
