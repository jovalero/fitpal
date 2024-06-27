package modelo;

public class Dieta {
    private int idDieta;
    private String nombreDieta;
    private String descripcionDieta;

    public Dieta(int idDieta, String nombreDieta, String descripcionDieta) {
        this.idDieta = idDieta;
        this.nombreDieta = nombreDieta;
        this.descripcionDieta = descripcionDieta;
    }
    public Dieta(String nombreDieta, String descripcionDieta) {
        this.nombreDieta = nombreDieta;
        this.descripcionDieta = descripcionDieta;
    }
	public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public String getNombreDieta() {
        return nombreDieta;
    }

    public void setNombreDieta(String nombreDieta) {
        this.nombreDieta = nombreDieta;
    }

    public String getDescripcionDieta() {
        return descripcionDieta;
    }

    public void setDescripcionDieta(String descripcionDieta) {
        this.descripcionDieta = descripcionDieta;
    }

    @Override
    public String toString() {
        return "Dieta [idDieta=" + idDieta + ", nombreDieta=" + nombreDieta + ", descripcionDieta=" + descripcionDieta + "]";
    }
}
