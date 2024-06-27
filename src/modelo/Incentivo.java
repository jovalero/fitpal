package modelo;

public class Incentivo {
    private int idIncentivo;
    private String recompensa;
    private double costo;
    private String dirigido;
    
    // Constructor con parámetros
    public Incentivo(int idIncentivo, String recompensa, double costo, String dirigido) {
        this.idIncentivo = idIncentivo;
        this.recompensa = recompensa;
        this.costo = costo;
        this.dirigido = dirigido;
    }

    // Constructor vacío
    public Incentivo() {
    }

    // Getters y Setters
    public int getIdIncentivo() {
        return idIncentivo;
    }

    public void setIdIncentivo(int idIncentivo) {
        this.idIncentivo = idIncentivo;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDirigido() {
        return dirigido;
    }

    public void setDirigido(String dirigido) {
        this.dirigido = dirigido;
    }

    @Override
    public String toString() {
        return "Incentivo{" +
                "idIncentivo=" + idIncentivo +
                ", recompensa='" + recompensa + '\'' +
                ", costo=" + costo +
                ", dirigido='" + dirigido + '\'' +
                '}';
    }
}
