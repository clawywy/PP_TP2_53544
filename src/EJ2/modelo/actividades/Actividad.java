package EJ2.modelo.actividades;

import EJ2.excepciones.CupoExcedidoException;
import EJ2.modelo.Estudiante;
import EJ2.modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    private List<Inscripcion> inscripciones;

    public static final int CUPO_MINIMO;

    static {
        CUPO_MINIMO = 5;
    }

    public Actividad(int id, String titulo, int cupo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = (cupo > CUPO_MINIMO) ? cupo : CUPO_MINIMO;
        this.inscripciones = new ArrayList<>();
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public final void mostrarIdentificacion() {
        System.out.println("  --  [" + getTipo() + "] ID: " + id + " - " + titulo +
                " | Cupo máx: " + cupoMaximo +
                " | Costo Materiales: $" + calcularCostoMateriales());
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size() < cupoMaximo) {
            Inscripcion nuevaInscripcion = new Inscripcion(this, estudiante, LocalDate.now(), "REGISTRADA");
            inscripciones.add(nuevaInscripcion);
            return nuevaInscripcion;
        } else {
            throw new CupoExcedidoException("No se puede inscribir al estudiante " + estudiante.getNombre() + ". Cupo máximo alcanzado.");
        }
    }

    public void mostrarInscripciones() {
        if (inscripciones.isEmpty()) {
            System.out.println("     (Sin inscripciones registradas)");
            return;
        }
        for (Inscripcion ins : inscripciones) {
            System.out.println("     - " + ins.getFecha() + " | " + ins.getEstado() + " | " +
                    ins.getEstudiante().getNombre() + " (Legajo: " + ins.getEstudiante().getLegajo() + ")");
        }
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }

}