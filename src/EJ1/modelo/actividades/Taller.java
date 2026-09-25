package EJ1.modelo.actividades;

public class Taller extends Actividad {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupo, boolean requiereNotebook) {
        super(id, titulo, cupo);
        this.requiereNotebook = requiereNotebook;
    }

    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000.0 : 2000.0;
    }

    public String getTipo() {
        return "Taller (Requiere Notebook: " + (requiereNotebook ? "Sí" : "No") + ")";
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }
}