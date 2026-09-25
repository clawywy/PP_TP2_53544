package EJ3.modelo.certificacion;

import EJ3.modelo.Estudiante;

public interface Certificable {
     String ENTIDAD_EMISORA="UTN-Facultad Regional Mendoza";
     String generarCertificado(Estudiante estudiante);
}
