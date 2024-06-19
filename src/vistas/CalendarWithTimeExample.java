package Vista;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarWithTimeExample extends JFrame {
    public CalendarWithTimeExample() {
        // Configurar el JFrame
        setTitle("Calendario con Hora en JFrame");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Crear el primer JDateChooser
        JDateChooser dateChooser1 = new JDateChooser();
        dateChooser1.setDateFormatString("dd/MM/yyyy");

        // Establecer la fecha mínima a hoy
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, 1); // Agrega un día a la fecha actual
        dateChooser1.setMinSelectableDate(today.getTime());

        // Crear JSpinner para horas
        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        JSpinner hourSpinner = new JSpinner(hourModel);
        JSpinner.NumberEditor hourEditor = new JSpinner.NumberEditor(hourSpinner, "00");
        hourSpinner.setEditor(hourEditor);

        // Crear JSpinner para minutos
        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner minuteSpinner = new JSpinner(minuteModel);
        JSpinner.NumberEditor minuteEditor = new JSpinner.NumberEditor(minuteSpinner, "00");
        minuteSpinner.setEditor(minuteEditor);

        // Crear el segundo JDateChooser
        JDateChooser dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("dd/MM/yyyy");
        dateChooser2.setEnabled(false); // Inicialmente deshabilitado

        // Botón para obtener la fecha y hora seleccionadas
        JButton getDateTimeButton = new JButton("Obtener Fecha y Hora");
        JLabel dateTimeLabel = new JLabel();

        getDateTimeButton.addActionListener(e -> {
            Date selectedDate1 = dateChooser1.getDate();
            Date selectedDate2 = dateChooser2.getDate();
            if (selectedDate1 != null && selectedDate2 != null) {
                int selectedHour = (int) hourSpinner.getValue();
                int selectedMinute = (int) minuteSpinner.getValue();

                Calendar selectedDateTime1 = Calendar.getInstance();
                selectedDateTime1.setTime(selectedDate1);
                selectedDateTime1.set(Calendar.HOUR_OF_DAY, selectedHour);
                selectedDateTime1.set(Calendar.MINUTE, selectedMinute);

                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dateTimeString1 = dateTimeFormat.format(selectedDateTime1.getTime());

                Calendar selectedDateTime2 = Calendar.getInstance();
                selectedDateTime2.setTime(selectedDate2);
                String dateTimeString2 = dateTimeFormat.format(selectedDateTime2.getTime());

                dateTimeLabel.setText("Fecha y hora seleccionadas: " + dateTimeString1 + " y " + dateTimeString2);
            } else {
                dateTimeLabel.setText("No se ha seleccionado ninguna fecha.");
            }
        });

        // Agregar ActionListener al primer JDateChooser
        dateChooser1.addPropertyChangeListener("date", evt -> {
            Date selectedDate = dateChooser1.getDate();
            if (selectedDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDate);
                cal.add(Calendar.DAY_OF_YEAR, 1);
                dateChooser2.setMinSelectableDate(cal.getTime());
                dateChooser2.setEnabled(true);
            }
        });

        // Agregar componentes al panel
        panel.add(new JLabel("Fecha 1:"));
        panel.add(dateChooser1);
        panel.add(new JLabel("Hora:"));
        panel.add(hourSpinner);
        panel.add(new JLabel("Minutos:"));
        panel.add(minuteSpinner);
        panel.add(new JLabel("Fecha 2:"));
        panel.add(dateChooser2);
        panel.add(getDateTimeButton);
        panel.add(dateTimeLabel);

        // Agregar el panel al JFrame
        add(panel);

        // Hacer visible el JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(CalendarWithTimeExample::new);
    }
}
