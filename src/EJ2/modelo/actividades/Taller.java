package EJ2.modelo.actividades;

import EJ2.modelo.Estudiante;
import EJ2.modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {
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

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return
                 "                CERTIFICADO DE TALLER                   \n"
                + " Otorgado a: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n"
                + " Por su participación en el Taller: " + getTitulo() + "\n"
                + " Entidad emisora: " + ENTIDAD_EMISORA + "\n";
    }
}