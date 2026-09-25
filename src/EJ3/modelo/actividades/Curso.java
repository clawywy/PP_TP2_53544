package EJ3.modelo.actividades;

import EJ3.modelo.Estudiante;
import EJ3.modelo.certificacion.Certificable;

public class Curso extends Actividad implements Certificable {
    private int nivel;
    public Curso(int id, String titulo, int cupo, int nivel){
        super(id,titulo,cupo);
        this.nivel=nivel;
    }
    public double calcularCostoMateriales(){
    return  this.nivel*1000;
    }
    public int getNivel(){
        return nivel;
    }
    public void setNivel(int nivel){
        this.nivel=nivel;
    }
    public String getTipo(){
    return "Curso de nivel: "+this.nivel;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return    "                CERTIFICADO DE CURSO                    \n"
                + " Otorgado a: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n"
                + " Por haber completado el Curso: " + getTitulo() + " (Nivel " + this.nivel + ")\n"
                + " Entidad emisora: " + ENTIDAD_EMISORA + "\n";

    }
    }

