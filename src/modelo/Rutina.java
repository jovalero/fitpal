package modelo;

public class Rutina {

    private int idRutina;
    private String estado;
    private String descripcion;
    private String objetivo;

    public Rutina(int idRutina, String estado, String descripcion, String objetivo) {
        this.idRutina = idRutina;
        this.estado = estado;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String toString() {
        return "Rutina [idRutina=" + idRutina + ", estado=" + estado + ", descripcion=" + descripcion + ", objetivo=" + objetivo + "]";
    }
 
}
