package modelo;

public class EjercicioRutina {
    private int ID_Ejercicio_Rutina;
    private int ID_Ejercicio;
    private int ID_Rutina;
    private int Num_Series;
    private int Repeticiones;
    

    public EjercicioRutina(int ID_Ejercicio_Rutina, int ID_Ejercicio, int ID_Rutina, int Num_Series, int Repeticiones) {
        this.ID_Ejercicio_Rutina = ID_Ejercicio_Rutina;
        this.ID_Ejercicio = ID_Ejercicio;
        this.ID_Rutina = ID_Rutina;
        this.Num_Series = Num_Series;
        this.Repeticiones = Repeticiones;
    }
 
    public int getID_Ejercicio_Rutina() {
        return ID_Ejercicio_Rutina;
    }

    public int getID_Ejercicio() {
        return ID_Ejercicio;
    }

    public int getID_Rutina() {
        return ID_Rutina;
    }

    public int getNum_Series() {
        return Num_Series;
    }

    public int getRepeticiones() {
        return Repeticiones;
    }

   
    public void setID_Ejercicio_Rutina(int ID_Ejercicio_Rutina) {
        this.ID_Ejercicio_Rutina = ID_Ejercicio_Rutina;
    }

    public void setID_Ejercicio(int ID_Ejercicio) {
        this.ID_Ejercicio = ID_Ejercicio;
    }

    public void setID_Rutina(int ID_Rutina) {
        this.ID_Rutina = ID_Rutina;
    }

    public void setNum_Series(int Num_Series) {
        this.Num_Series = Num_Series;
    }

    public void setRepeticiones(int Repeticiones) {
        this.Repeticiones = Repeticiones;
    }


    
}
