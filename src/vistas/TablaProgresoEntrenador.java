package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import controlador.ProgresoControlador;
import modelo.Entrenador;
import modelo.Progreso;

public class TablaProgresoEntrenador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ProgresoControlador controlador;
    private Entrenador entrenador;
    private JLabel seleccionadoLabel;

    public TablaProgresoEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            controlador = new ProgresoControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        String[] columnNames = { "ID Progreso", "ID Cliente", "Fecha", "Imagen", "Peso" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 73, 921, 173);
        contentPane.add(scrollPane);
        
        JLabel lblFiltro = new JLabel("Buscador:");
        lblFiltro.setBounds(20, 31, 67, 20);
        contentPane.add(lblFiltro);

        JTextField textFieldFiltro = new JTextField();
        textFieldFiltro.setBounds(85, 31, 126, 20);
        contentPane.add(textFieldFiltro);
        textFieldFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(227, 29, 81, 25);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrar(textFieldFiltro.getText());
            }
        });
        contentPane.add(btnFiltrar);

        seleccionadoLabel = new JLabel("SECCIÓN DE PROGRESO DE CLIENTES");
        seleccionadoLabel.setBounds(10, 11, 911, 14);
        contentPane.add(seleccionadoLabel);

        JButton Volverbutton = new JButton("Volver al menú");
        Volverbutton.setBounds(750, 270, 150, 50);
        Volverbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeEntrenador(entrenador);
                dispose();
            }
        });
        contentPane.add(Volverbutton);
    }

    public void actualizarTabla() {
        model.setRowCount(0); 
        LinkedList<Progreso> progresos = controlador.getAllProgresos();
        for (Progreso progreso : progresos) {
            model.addRow(new Object[] { 
                progreso.getIdProgreso(), 
                progreso.getIdCliente(), 
                progreso.getFecha().toString(), 
                progreso.getImagen(), 
                progreso.getPeso() 
            });
        }
    }
    
    private void filtrar(String criterio) {
        model.setRowCount(0);
        LinkedList<Progreso> progresos = controlador.getAllProgresos();
        String criterioLower = criterio.toLowerCase();

        for (Progreso progreso : progresos) {
            if (progreso.getFecha().toString().toLowerCase().contains(criterioLower) ||
                progreso.getImagen().toLowerCase().contains(criterioLower) ||
                String.valueOf(progreso.getIdProgreso()).contains(criterioLower) ||
                String.valueOf(progreso.getIdCliente()).contains(criterioLower) ||
                String.valueOf(progreso.getPeso()).contains(criterioLower)) {
                model.addRow(new Object[] { 
                    progreso.getIdProgreso(), 
                    progreso.getIdCliente(), 
                    progreso.getFecha().toString(), 
                    progreso.getImagen(), 
                    progreso.getPeso() 
                });
            }
        }
    }
}
