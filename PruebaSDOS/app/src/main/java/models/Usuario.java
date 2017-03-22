package models;

import java.util.List;

/**
 * Created by geovanni on 16/03/17.
 */

public class Usuario {

    String id;
    String nombre;
    String apellido;
    String username;
    String password;
    TipoUsuario tipoUsuario;
    Tarea[] tareas;

    public Usuario(String id, String nombre, String apellido, String username, TipoUsuario tipoUsuario, Tarea[] tareas)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.tipoUsuario = tipoUsuario;
        this.tareas = tareas;
    }

    public Usuario(String id, String nombre, String apellido, String username, String password, TipoUsuario tipoUsuario, Tarea[] tareas)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.tareas = tareas;
    }

    public String getFullname(){
        return nombre + " " + apellido;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Tarea[] getTareas() {
        return tareas;
    }

    public void setTareas(Tarea[] tareas) {
        this.tareas = tareas;
    }


}
