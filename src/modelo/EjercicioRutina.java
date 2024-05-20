package modelo;

public class EjercicioRutina {
    private int idEjercicioRutina;
    private int idEjercicio;
    private int idRutina;
    private int numSeries;
    private int repeticiones;

    public EjercicioRutina(int idEjercicioRutina, int idEjercicio, int idRutina, int numSeries, int repeticiones) {
        this.idEjercicioRutina = idEjercicioRutina;
        this.idEjercicio = idEjercicio;
        this.idRutina = idRutina;
        this.numSeries = numSeries;
        this.repeticiones = repeticiones;
    }

    public int getIdEjercicioRutina() {
        return idEjercicioRutina;
    }

    public void setIdEjercicioRutina(int idEjercicioRutina) {
        this.idEjercicioRutina = idEjercicioRutina;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public int getNumSeries() {
        return numSeries;
    }

    public void setNumSeries(int numSeries) {
        this.numSeries = numSeries;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return "EjercicioRutina [idEjercicioRutina=" + idEjercicioRutina + ", idEjercicio=" + idEjercicio + ", idRutina=" + idRutina + ", numSeries=" + numSeries + ", repeticiones=" + repeticiones + "]";
    }
}
