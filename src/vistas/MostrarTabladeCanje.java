package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.IncentivoControlador;
import modelo.Admin;
import modelo.Incentivo;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MostrarTabladeCanje extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private IncentivoControlador controlador;

    public MostrarTabladeCanje(Admin administrador) {
        this.setVisible(true);
        controlador = new IncentivoControlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 365);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        try {
            controlador = new IncentivoControlador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        if (controlador.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
            return;
        }

        
        String[] columnNames = {"ID Incentivo", "Recompensa", "Costo", "Dirigido"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        table.setBounds(10, 37, 532, 204);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 37, 911, 190);
        contentPane.add(scrollPane);

        JLabel seleccionadolabel = new JLabel("Seleccionado: ");
        seleccionadolabel.setBounds(5, 5, 911, 14);
        contentPane.add(seleccionadolabel);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeAdmin(administrador).setVisible(true);
                dispose();
            }
        });
        btnAtras.setBounds(258, 275, 389, 40);
        contentPane.add(btnAtras);
        
        JLabel lblholaRecuerdaQue = new JLabel("¡Hola! Recuerda que los puntos de gym se pueden comprar en recepción. \r\n¡Aprovecha al máximo los beneficios del gimnasio y alcanza tus metas de salud y bienestar!\r\n\r\n");
        lblholaRecuerdaQue.setForeground(new Color(255, 0, 0));
        lblholaRecuerdaQue.setBounds(60, 238, 796, 37);
        contentPane.add(lblholaRecuerdaQue);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int idIncentivo = (int) table.getValueAt(selectedRow, 0);
                        String recompensa = (String) table.getValueAt(selectedRow, 1);
                        double costo = (double) table.getValueAt(selectedRow, 2);
                        String dirigido = (String) table.getValueAt(selectedRow, 3);

                        seleccionadolabel.setText("Seleccionado: ID Incentivo=" + idIncentivo + ", Recompensa=" + recompensa + ", Costo=" + costo + ", Dirigido=" + dirigido);
                    }
                }
            }
        });
    }

    public void actualizarTabla() {
        model.setRowCount(0);
        LinkedList<Incentivo> incentivos = controlador.getAllIncentivo();
        for (Incentivo incentivo : incentivos) {
            model.addRow(new Object[]{
                incentivo.getIdIncentivo(), incentivo.getRecompensa(), incentivo.getCosto(), incentivo.getDirigido()
            });
        }
    }
}
