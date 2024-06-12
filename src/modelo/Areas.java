package modelo;

import java.util.List;

import javax.swing.JOptionPane;

import controlador.AreasControlador;

public class Areas {
    private int ID_Area;
    private String Nombre;
    private int ID_Sucursal;
    private String Ubicacion;

    public Areas(int ID_Area, String Nombre, int ID_Sucursal, String Ubicacion) {
        this.ID_Area = ID_Area;
        this.Nombre = Nombre;
        this.ID_Sucursal = ID_Sucursal;
        this.Ubicacion = Ubicacion;
        
    }  public Areas() {
        
       
        
    }

    public int getIdArea() {
        return ID_Area;
    }

    public void setIdArea(int ID_Area) {
        this.ID_Area = ID_Area;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdSucursal() {
        return ID_Sucursal;
    }

    public void setIdSucursal(int ID_Sucursal) {
        this.ID_Sucursal = ID_Sucursal;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
    public static void crearArea() {
        AreasControlador controlador = new AreasControlador();
        
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del área:");
        int idSucursal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la sucursal:"));
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación del área:");
        
        Areas nuevaArea = new Areas(0, nombre, idSucursal, ubicacion); // El ID se generará automáticamente en la BDD
        controlador.addArea(nuevaArea);
    }

    public static void modificarArea() {
        AreasControlador controlador = new AreasControlador();
        
        int idArea = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del área a modificar:"));
        Areas areaAModificar = controlador.getAreaById(idArea);
        
        if (areaAModificar != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del área:", areaAModificar.getNombre());
            int nuevoIdSucursal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID de la sucursal:", areaAModificar.getIdSucursal()));
            String nuevaUbicacion = JOptionPane.showInputDialog("Ingrese la nueva ubicación del área:", areaAModificar.getUbicacion());
            
            areaAModificar.setNombre(nuevoNombre);
            areaAModificar.setIdSucursal(nuevoIdSucursal);
            areaAModificar.setUbicacion(nuevaUbicacion);
            
            controlador.updateArea(areaAModificar);
        } else {
            JOptionPane.showMessageDialog(null, "Área no encontrada :(");
        }
    }

    public static void mostrarAreas() {
        AreasControlador controlador = new AreasControlador();
        List<Areas> listaAreas = controlador.getAllAreas();
        
        StringBuilder nota = new StringBuilder("Lista de áreas:\n");
        for (Areas area : listaAreas) {
            nota.append(area.toString()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, nota.toString());
    }

    public static void borrarArea() {
        AreasControlador controlador = new AreasControlador();
        
        int idArea = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del área que desea borrar:"));
        Areas areaABorrar = controlador.getAreaById(idArea);
        
        if (areaABorrar != null) {
            controlador.deleteArea(idArea);
        } else {
            JOptionPane.showMessageDialog(null, "Área no encontrada :(");
        }
    }
}
    