package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.IncentivoControlador;
import modelo.Admin;
import modelo.Incentivo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarIncentivo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField recompensaInput;
    private JTextField costoInput;
    private JTextField dirigidoInput;
    private IncentivoControlador controlador;

    public RegistrarIncentivo(Admin administrador) {
    	controlador=new IncentivoControlador();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblRecompensa = new JLabel("Recompensa:");
        lblRecompensa.setBounds(50, 50, 100, 20);
        contentPane.add(lblRecompensa);
        
        recompensaInput = new JTextField();
        recompensaInput.setBounds(150, 50, 200, 20);
        contentPane.add(recompensaInput);
        recompensaInput.setColumns(10);
        
        JLabel lblCosto = new JLabel("Costo:");
        lblCosto.setBounds(50, 100, 100, 20);
        contentPane.add(lblCosto);
        
        costoInput = new JTextField();
        costoInput.setBounds(150, 100, 200, 20);
        contentPane.add(costoInput);
        costoInput.setColumns(10);
        
        JLabel lblDirigido = new JLabel("Dirigido:");
        lblDirigido.setBounds(50, 150, 100, 20);
        contentPane.add(lblDirigido);
        
        dirigidoInput = new JTextField();
        dirigidoInput.setBounds(150, 150, 200, 20);
        contentPane.add(dirigidoInput);
        dirigidoInput.setColumns(10);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recompensa = recompensaInput.getText();
                String costoStr = costoInput.getText();
                String dirigido = dirigidoInput.getText();
                
                if(recompensa.isEmpty() || costoStr.isEmpty() || dirigido.isEmpty()) {
                    mostrarMensaje("Por favor, complete todos los campos.");
                    return;
                }
                
                try {
                    double costo = Double.parseDouble(costoStr);
                    Incentivo nuevoIncentivo = new Incentivo(0,recompensa, costo, dirigido);
                    controlador.addIncentivo(nuevoIncentivo);
                    
                    mostrarMensaje("Incentivo registrado exitosamente.");
                  
                    dispose();
                    
                } catch (NumberFormatException ex) {
                    mostrarMensaje("El costo debe ser un número válido.");
                }
            }
        });
        btnRegistrar.setBounds(50, 200, 100, 30);
        contentPane.add(btnRegistrar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TablaIncentivo tablaIncentivo = new TablaIncentivo(administrador);
                dispose();
            }
        });
        btnCancelar.setBounds(250, 200, 100, 30);
        contentPane.add(btnCancelar);
        
        JLabel lblNewLabel = new JLabel("Registrar Nuevo Incentivo");
        lblNewLabel.setBounds(131, 11, 142, 20);
        contentPane.add(lblNewLabel);
    }
    
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
