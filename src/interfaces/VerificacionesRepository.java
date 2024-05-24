package interfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;

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
      static double SoloDoubles(String texto) {
         double numero = 0;
         boolean valido = false;

         do {
             try {
                 String input = JOptionPane.showInputDialog(texto);
                 if (input != null) {
                     numero = Double.parseDouble(input);
                     valido = true;
                 } else {
                     JOptionPane.showMessageDialog(null, "No se ingresó ningún número.");
                 }
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Error: Ingresa solo números decimales.");
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
    

     static String Mail() {
         final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
         Pattern pattern = Pattern.compile(EMAIL_PATTERN);
         boolean emailvalido=false;
         String email;
         do {
             email = JOptionPane.showInputDialog("Ingrese su email:");
             emailvalido=true;
             if (email != null) {  
                 Matcher matcher = pattern.matcher(email);
                 if (!matcher.matches()) {          
                     emailvalido=false;
                     JOptionPane.showMessageDialog(null, "Email invalido");
                 } 
             } else {
            	 emailvalido=false;
                 JOptionPane.showMessageDialog(null, "No se ingresó ningún email.");
             }
		} while (!emailvalido);
         return email;
  
     }
     static boolean MailExistente() {
    	 ClienteControlador clientecontrolador= new ClienteControlador();
    	 
    	 return false;
     }

     static String Password() {
         final String PASSWORD_PATTERN = 
             "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
         Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
         boolean passwordValido = false;
         String password = null;

         do {
             password = JOptionPane.showInputDialog("Ingrese su contraseña (mínimo 6 caracteres, al menos 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial):");
             if (password != null) { 
                 Matcher matcher = pattern.matcher(password);
                 if (matcher.matches()) {
                     passwordValido = true;
                 } else {
                     JOptionPane.showMessageDialog(null, "Contraseña inválida. Debe contener al menos 1 mayúscula, 1 minúscula, 1 número, 1 carácter especial y tener al menos 6 caracteres.");
                 }
             } else {
                 JOptionPane.showMessageDialog(null, "No se ingresó ninguna contraseña.");
             }
         } while (!passwordValido);

         return password;
     }
    
}
