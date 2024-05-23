package interfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

public interface VerificacionesRepository {

     static String Sololetras(String texto) {
    	String Texto="";
    	boolean seguro;
    	
    	do {
    		seguro=true;
			Texto=JOptionPane.showInputDialog(texto);
		    for (int i = 0; i < Texto.length(); i++) {
	            char caracter = Texto.charAt(i);
	            if (!Character.isLetter(caracter)) {
	                seguro=false;
	                i=Texto.length();
	            }
	        }
		} while (!seguro);	
    
        return Texto;
    }
    
     static int SoloEnteros(String texto) {
         int numero = 0;
         boolean valido = false;

         do {
             try {
                 String input = JOptionPane.showInputDialog(texto);
                 numero = Integer.parseInt(input);
                 valido = true;
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Error: Ingresa solo números enteros.");
             }
         } while (!valido);

         return numero;
     }
     
     static LocalDate pedirFecha(String texto) {
         LocalDate fecha = null;
         boolean fechaValida = false;

         do {
             String input = JOptionPane.showInputDialog(texto);

             try {
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 fecha = LocalDate.parse(input, formatter);
                 if (fecha.isBefore(LocalDate.now())) {
                     fechaValida = true;
                 } else {
                     JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede estar en el futuro.");
                 }
             } catch (DateTimeParseException e) {
                 JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Por favor, ingresa la fecha en el formato yyyy-MM-dd.");
             }
         } while (!fechaValida);

         return fecha;
     }
    
    
}
