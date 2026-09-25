package EJ2.modelo.actividades;

public class Charla extends Actividad {
    private String disertante;

    public Charla(int id, String titulo, int cupo, String disertante) {
        super(id, titulo, cupo);
        this.disertante = disertante;
    }

    public double calcularCostoMateriales() {
        return 0.0;
    }

    public String getTipo() {
        return "Charla (Disertante: " + disertante + ")";
    }

    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }
}