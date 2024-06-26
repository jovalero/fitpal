package interfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import modelo.Cliente;
import modelo.Entrenador;

public interface VerificacionesRepository {

     static Boolean Sololetras(String textoaverificar) {
    	boolean seguro;    	
    		seguro=true;
			if (!textoaverificar.isEmpty()) {
			    for (int i = 0; i < textoaverificar.length(); i++) {
		            char caracter = textoaverificar.charAt(i);
		            if (!Character.isLetter(caracter)) {
		                seguro=false;
		                i=textoaverificar.length();
		            }
		        }
			}
			else {
				JOptionPane.showMessageDialog(null, "Dejaste vacio el recuadro!");
				seguro=false;
				
			}

    
        return seguro;
    }
    
     static boolean SoloEnteros(String input) {
         int numero = 0;
         boolean valido;
         valido=true;
             try {
                 if (!input.isEmpty() ) {
                     numero=Integer.parseInt(input);
         		}
                 else {
                	 valido=false;
				}
             
             } catch (NumberFormatException e) {
            	 return false;
             }
         
         return valido;
     }
     static boolean SoloDoubles(String input) {
         double numero = 0;
         boolean valido;
         valido=true;
             try {
                 if (!input.isEmpty() ) {
                     numero=Double.parseDouble(input);
         		}
                 else {
                	 valido=false;
				}
             
             } catch (NumberFormatException e) {
            	 return false;
             }
         
         return valido;
     }
     
     static LocalDate pedirFecha(String texto) {
         LocalDate fecha = null;
         boolean fechaValida = false;

         do {
             String input = JOptionPane.showInputDialog(texto);
             fechaValida=false;
             try {
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 fecha = LocalDate.parse(input, formatter);
                 if (fecha.isAfter(LocalDate.now())) {
                     fechaValida = true;
                 } else {
                     JOptionPane.showMessageDialog(null, "La fecha de vencimiento no puede ser en el pasado");
                 }
             } catch (DateTimeParseException e) {
                 JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Por favor, ingresa la fecha en el formato yyyy-MM-dd.");
             }
         } while (!fechaValida);
         return fecha;
     }
    

     static boolean Mail(String email) {
         final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
         Pattern pattern = Pattern.compile(EMAIL_PATTERN);
         boolean emailvalido;
             emailvalido=true;
             if (!email.isEmpty()) {  
                 Matcher matcher = pattern.matcher(email);
                 if (!matcher.matches()) {          
                     emailvalido=false;
                 }
                 else if (MailExistente(email)) {
					emailvalido=false;
					JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese correo");
				}
             } else {
                 emailvalido=false;
             }
         return emailvalido;
     }
     static boolean MailInicio(String email) {
         final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
         Pattern pattern = Pattern.compile(EMAIL_PATTERN);
         boolean emailvalido=true;
             if (email != null) {  
                 Matcher matcher = pattern.matcher(email);
                 if (!matcher.matches() && !email.equalsIgnoreCase("Admin")) {          
                     emailvalido=false;
                     JOptionPane.showMessageDialog(null, "Email invalido");
                 }
  
             } else {
                 JOptionPane.showMessageDialog(null, "No se ingresó ningún email.");
                 emailvalido=false;
                 
             }
         return emailvalido;
     }
     static boolean MailExistente(String email) {
    	 ClienteControlador clientecontrolador= new ClienteControlador();
    	 EntrenadorControlador entrenadorcontrolador= new EntrenadorControlador();
    	 LinkedList<Cliente> clientes= clientecontrolador.getAllClientes();
    	 LinkedList<Entrenador> Entrenadores= entrenadorcontrolador.getAllEntrenadores();
    	 
    	 for (int i = 0; i < clientes.size(); i++) {
			if (email.equalsIgnoreCase(clientes.get(i).getUsuario())) {
				return true;
			}
		}
    	 for (int i = 0; i < Entrenadores.size(); i++) {
			if (email.equalsIgnoreCase(Entrenadores.get(i).getUsuario())) {
				return true;
			}
		}
    	 
    	 return false;
     }
     static boolean DNIExistente(int DNI) {
    	 ClienteControlador clientecontrolador= new ClienteControlador();
    	 LinkedList<Cliente> clientes= clientecontrolador.getAllClientes();
    	 for (int i = 0; i < clientes.size(); i++) {
			if (DNI==clientes.get(i).getDNI()) {
				JOptionPane.showMessageDialog(null, "El DNI Ya existe en otra cuenta");
				return true;
			}
    	 }
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
    static String solicitarConfirmacion(String mensaje) {
         
         Object[] opciones = {"Sí", "No"};
         int seleccion = JOptionPane.showOptionDialog(null, 
                                                      mensaje, 
                                                      "Confirmación", 
                                                      JOptionPane.YES_NO_OPTION, 
                                                      JOptionPane.QUESTION_MESSAGE, 
                                                      null, 
                                                      opciones, 
                                                      opciones[0]);

         if (seleccion == JOptionPane.YES_OPTION) {
             return "Sí";
         } else if (seleccion == JOptionPane.NO_OPTION) {
             return "No";
         } else {
             return null; 
         }
     }
}
