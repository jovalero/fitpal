package modelo;

public class DietaComida {
    private int idDietaComida;
    private int idDieta;
    private int idComida;
    private double cantidad;

    public DietaComida(int idDietaComida, int idDieta, int idComida, double cantidad) {
        this.idDietaComida = idDietaComida;
        this.idDieta = idDieta;
        this.idComida = idComida;
        this.cantidad = cantidad;
    }

    public int getIdDietaComida() {
        return idDietaComida;
    }

    public void setIdDietaComida(int idDietaComida) {
        this.idDietaComida = idDietaComida;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DietaComida [idDietaComida=" + idDietaComida + ", idDieta=" + idDieta + ", idComida=" + idComida + ", cantidad=" + cantidad + "]";
    }
}

