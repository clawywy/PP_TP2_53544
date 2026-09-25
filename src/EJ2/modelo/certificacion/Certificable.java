package EJ2.modelo.certificacion;

import EJ2.modelo.Estudiante;

public interface Certificable {
     String ENTIDAD_EMISORA="UTN-Facultad Regional Mendoza";
     String generarCertificado(Estudiante estudiante);
}
