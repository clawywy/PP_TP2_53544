package EJ2;

import EJ2.excepciones.CupoExcedidoException;
import EJ2.modelo.Inscripcion;
import EJ2.modelo.actividades.Actividad;
import EJ2.modelo.Estudiante;
import EJ2.modelo.EventoUniversitario;
import EJ2.modelo.Sala;
import EJ2.modelo.certificacion.Certificable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        List<Estudiante> listaEstudiantes = new ArrayList<>();
        System.out.println("===  REGISTRO DE ESTUDIANTES ===");
        System.out.print("¿Cuántos estudiantes querés registrar?: ");
        int cantEstudiantes = teclado.nextInt();
        teclado.nextLine();

        for (int i = 0; i < cantEstudiantes; i++) {
            System.out.println("\n--- Estudiante " + (i + 1) + " ---");
            System.out.print("Ingresá el legajo: ");
            String legajo = teclado.nextLine();

            System.out.print("Ingresá el nombre completo: ");
            String nombre = teclado.nextLine();

            Estudiante est = new Estudiante(legajo, nombre);
            listaEstudiantes.add(est);
        }

        System.out.println("\n>>> LISTA DE ESTUDIANTES CARGADOS <<<");
        for (Estudiante e : listaEstudiantes) {
            System.out.println(e);
        }

        System.out.println("\n===  REGISTRO DE EVENTO ===");
        System.out.print("ID del Evento: ");
        String idEv = teclado.nextLine();
        System.out.print("Título del Evento: ");
        String titEv = teclado.nextLine();
        System.out.print("Costo Base: ");
        double costoEv = teclado.nextDouble();
        System.out.print("¿Es gratuito? (true/false): ");
        boolean esGratis = teclado.nextBoolean();
        teclado.nextLine();

        EventoUniversitario evento = new EventoUniversitario(idEv, titEv, costoEv, esGratis);

        System.out.println("\n===  ASIGNACIÓN DE SALA ===");
        System.out.print("ID de la sala: ");
        int idSala = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Nombre de la sala: ");
        String nomSala = teclado.nextLine();

        Sala sala = new Sala(idSala, nomSala);
        evento.asignarSala(sala);

        System.out.println("\n===  CREACIÓN DE ACTIVIDADES ===");
        System.out.print("¿Cuántas actividades querés crear?: ");
        int cantAct = teclado.nextInt();
        teclado.nextLine();

        for (int i = 0; i < cantAct; i++) {
            System.out.println("\n--- Actividad " + (i + 1) + " ---");
            System.out.print("Tipo de actividad ('Charla' o 'Taller' o 'Curso'): ");
            String tipo = teclado.nextLine();

            System.out.print("ID de la actividad: ");
            int idA = teclado.nextInt();
            teclado.nextLine();

            System.out.print("Título: ");
            String titA = teclado.nextLine();

            System.out.print("Cupo máximo: ");
            int cupoA = teclado.nextInt();
            teclado.nextLine();

            if (tipo.equalsIgnoreCase("Charla")) {
                System.out.print("Nombre del Disertante: ");
                String disertante = teclado.nextLine();
                evento.crearActividad("Charla", idA, titA, cupoA, disertante, false, 0);

            } else if (tipo.equalsIgnoreCase("Taller")) {
                System.out.print("¿Requiere uso de Notebook? (true/false): ");
                boolean reqNotebook = teclado.nextBoolean();
                teclado.nextLine(); // Limpieza de buffer
                evento.crearActividad("Taller", idA, titA, cupoA, null, reqNotebook, 0);

            } else if (tipo.equalsIgnoreCase("Curso")) {
                System.out.print("Nivel del curso: ");
                int nivel = teclado.nextInt();
                teclado.nextLine(); // Limpieza de buffer
                evento.crearActividad("Curso", idA, titA, cupoA, null, false, nivel);

            } else {
                System.out.println("Tipo de actividad no reconocido.");
            }
        }

        System.out.println("\n===  INSCRIPCIÓN DE ESTUDIANTES ===");
        for (Actividad act : evento.getActividades()) {
            System.out.println("\nInscribiendo para la actividad: " + act.getTitulo());
            for (Estudiante est : listaEstudiantes) {
                System.out.print("¿Inscribir a " + est.getNombre() + "? (true/false): ");
                boolean anota = teclado.nextBoolean();
                if (anota) {
                    try {
                        act.inscribir(est);
                    } catch (CupoExcedidoException e) {
                        System.out.println("Error detectado: "+ e.getMessage());
                    }
                }
            }
        }
        teclado.nextLine();

        System.out.println("=========================================");
        System.out.println("    RESUMEN DE DATOS DEL EVENTO (POLIMÓRFICO)");
        System.out.println("==========================================");
        evento.mostrarDatos();

        System.out.println("==========================================");
        System.out.println(" Total de eventos registrados en memoria: " + EventoUniversitario.getCantidadEventos());
        System.out.println("==========================================");

        try {
            System.out.println("\n--- PERSISTENCIA DEL EVENTO ---");
            evento.persistirEvento();
            System.out.println("Evento persistido correctamente.");

            EventoUniversitario copiaDesdeArchivo = EventoUniversitario.recuperarEvento(evento.getId());

            System.out.println("\nDATOS DEL EVENTO RECUPERADO DESDE ARCHIVO:");
            copiaDesdeArchivo.mostrarDatos();

        } catch (FileNotFoundException e) {
            System.out.println("Error 01: No se encontró el archivo del evento: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("No fue posible reconstruir el objeto almacenado: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Se produjo un error de entrada/salida: " + e.getMessage());

        } finally {
            System.out.println("\n[FINALLY] Ciclo de persistencia concluido exitosamente.");
        }
        // f y g. EMISIÓN Y MUESTRA DE CERTIFICADOS DE ASISTENCIA
        System.out.println("\n\nEMISIÓN DE CERTIFICADOS");
        System.out.println("=======================");

        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable) {
                Certificable cert = (Certificable) act;
                System.out.println("\n--- Certificados para la actividad: " + act.getTitulo() + " (" + act.getTipo() + ") ---");

                for (Inscripcion ins : act.getInscripciones()) {
                    System.out.println(cert.generarCertificado(ins.getEstudiante()));
                }
            } else {
                System.out.println("\n[AVISO] La actividad '" + act.getTitulo() + "' (" + act.getTipo() + ") no emite certificados.");
            }
        }

        // h. MUESTRA DE DATOS DEL EVENTO
        System.out.println("\n\nRESUMEN FINAL DEL EVENTO");
        evento.mostrarDatos();


        teclado.close();
    }
}


