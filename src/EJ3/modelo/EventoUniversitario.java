package EJ3.modelo;

import EJ3.modelo.actividades.Actividad;
import EJ3.modelo.actividades.Charla;
import EJ3.modelo.actividades.Curso;
import EJ3.modelo.actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades = new ArrayList<>();

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public void crearActividad(String tipo, int id, String titulo, int cupo, String disertante, boolean requiereNotebook, int nivel) {
        if (tipo.equalsIgnoreCase("Charla")) {
            Actividad charla = new Charla(id, titulo, cupo, disertante);
            this.actividades.add(charla);
        } else if (tipo.equalsIgnoreCase("Taller")) {
            Actividad taller = new Taller(id, titulo, cupo, requiereNotebook);
            this.actividades.add(taller);
        } else if (tipo.equalsIgnoreCase("Curso")) {
            Actividad curso= new Curso(id,titulo,cupo,nivel);
            this.actividades.add(curso);
        }else {
            System.out.println("Tipo de actividad no reconocido.");
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double costoTotalActividades = 0.0;
        for (Actividad act : actividades) {
            costoTotalActividades += act.calcularCostoMateriales();
        }
        return (costoBase + costoTotalActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void mostrarDatos() {
        System.out.println("ID Evento: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Costo Base: $" + costoBase);
        System.out.println("Es gratuito: " + (gratuito ? "Sí" : "No"));
        System.out.println("Costo Estimado Final (con 21% IVA): $" + calcularCostoEstimado());

        if (sala != null) {
            System.out.println("Ubicación asignada: " + sala);
        } else {
            System.out.println("Ubicación asignada: Sin sala");
        }

        System.out.println("\n--- Actividades del Evento ---");
        if (actividades.isEmpty()) {
            System.out.println("  (Sin actividades registradas)");
        } else {
            for (Actividad act : actividades) {
                act.mostrarIdentificacion();
                act.mostrarInscripciones();
            }
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public boolean persistirEvento() throws IOException {
        String archivo = this.id + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
            return true;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) throws IOException, ClassNotFoundException {
        String archivo = id + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (EventoUniversitario) ois.readObject();
        }
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad act : this.actividades) {
            if (tipo.isInstance(act)) {
                resultado.add(tipo.cast(act));
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double total = 0.0;
        for (Actividad act : actividades) {
            total += act.calcularCostoMateriales();
        }
        return total;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getId(){
        return id;
    }

}