package EJ1.modelo;

import java.io.Serializable;

public class Sala implements Serializable {
private int id;
private String nombre;

public Sala(int id, String nombre){
    this.id=id;
    this.nombre=nombre;
}

    public String getNombre() {
        return nombre;
    }
    public int getid(){ return id;}

    public int getId() {
        return id;
    }
    public String toString(){
        return "Sala:[ Nombre:"+ nombre + "(ID:"+id+"]";

    }


}
