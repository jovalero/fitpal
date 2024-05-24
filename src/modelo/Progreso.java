package modelo;

import java.time.LocalDate;

public class Progreso {
    private int idProgreso;
    private int idCliente;
    private LocalDate fecha;
    private String imagen;
    private double peso;

    public Progreso(int idProgreso, int idCliente, LocalDate fecha, String imagen, double peso) {
        this.idProgreso = idProgreso;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.imagen = imagen;
        this.peso = peso;
    }

    public int getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(int idProgreso) {
        this.idProgreso = idProgreso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Progreso [idProgreso=" + idProgreso + ", idCliente=" + idCliente + ", fecha=" + fecha + ", imagen=" + imagen + ", peso=" + peso + "]";
    }
}
