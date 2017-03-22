package models;

/**
 * Created by geovanni on 16/03/17.
 */

public class TipoUsuario {

    String id;
    String nombre;

    public TipoUsuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
