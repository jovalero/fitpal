package modelo;

public class Rutina {

    private int ID_Rutina;
    private String estado;
    private String descripcion;
    private String objetivo;
    private String Creada;

    public Rutina(int idRutina, String estado, String descripcion, String objetivo, String string) {
        this.ID_Rutina = idRutina;
        this.estado = estado;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.Creada = this.Creada;
    }

    

	public int getIdRutina() {
        return ID_Rutina;
    }
    public String getCreada() {
        return Creada;
    }


    public void setIdRutina(int idRutina) {
        this.ID_Rutina = idRutina;
    }
    public void setCreada(String Creada) {
        this.Creada = Creada;
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
        return "Rutina [idRutina=" + ID_Rutina + ", estado=" + estado + ", descripcion=" + descripcion + ", objetivo=" + objetivo + "]";
    }

	public Object getID_Rutina() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPredeterminada() {
		// TODO Auto-generated method stub
		return false;
	}
}
