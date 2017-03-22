package models;

import android.app.Application;
import android.os.Parcelable;

import com.geoapps.pruebasdos.MyApp;

import java.io.Serializable;

import data.DataManager;

/**
 * Created by geovanni on 16/03/17.
 */

public class Tarea implements Serializable {

    String id;
    String nombre;
    String descripcion;
    String usuario_id;
    boolean completada;
    //public int duracion;

    public Tarea(String id, String nombre, String descripcion, boolean completada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public Tarea(String id, String nombre, String descripcion, String usuario_id, boolean completada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario_id = usuario_id;
        this.completada = completada;
    }

    public Usuario getEncargado()
    {
        if (usuario_id != null)
            return MyApp.dataManager.bdm.obtenerUsuario(this.usuario_id);
        else
            return null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
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

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
