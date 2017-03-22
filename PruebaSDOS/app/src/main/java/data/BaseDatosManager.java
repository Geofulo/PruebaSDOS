package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import models.ItemWS;
import models.LocationWS;
import models.Tarea;
import models.TipoUsuario;
import models.Usuario;
import models.WebsiteWS;

/**
 * Created by geovanni on 16/03/17.
 */

public class BaseDatosManager extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Prueba_SDOS_DB10.db";

    public static final String TABLA_USUARIOS = "Usuarios";
    public static final String TABLA_TIPOS_USUARIO = "TiposUsuario";
    public static final String TABLA_TAREAS = "Tareas";
    public static final String TABLA_ITEMS_WS = "ItemsWS";

    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_APELLIDO = "apellido";
    public static final String COLUMNA_USERNAME = "username";
    public static final String COLUMNA_PASSWORD = "password";
    public static final String COLUMNA_TIPO_USUARIO = "tipo_usuario";
    public static final String COLUMNA_ID_USUARIO = "id_usuario";
    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_COMPLETADA = "completada";

    public static final String COLUMNA_ITEM = "item";
    public static final String COLUMNA_CATEGORY = "category";
    public static final String COLUMNA_BUSINESS = "business";
    public static final String COLUMNA_L = "l";
    public static final String COLUMNA_PHONE_1 = "phone_1";
    public static final String COLUMNA_ZIPCODE = "zipcode";
    public static final String COLUMNA_LATITUDE = "latitude";
    public static final String COLUMNA_LONGITUDE = "longitude";
    public static final String COLUMNA_HUMAN_ADDRESS = "human_address";
    public static final String COLUMNA_NEEDS_RECORDING = "needs_recording";
    public static final String COLUMNA_WEBSITE = "website";
    public static final String COLUMNA_FARM_NAME = "farm_name";
    public static final String COLUMNA_SUITE = "suite";


    public BaseDatosManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLA_TIPOS_USUARIO + "(" +
                COLUMNA_ID + " integer primary key autoincrement, " +
                COLUMNA_NOMBRE + " text not null);");

        db.execSQL("create table " + TABLA_USUARIOS + "(" +
                COLUMNA_ID + " integer primary key autoincrement, " +
                COLUMNA_NOMBRE + " text not null, " +
                COLUMNA_APELLIDO + " text not null, " +
                COLUMNA_USERNAME + " text not null, " +
                COLUMNA_PASSWORD + " text not null, " +
                COLUMNA_TIPO_USUARIO + " integer, " +
                "foreign key (" + COLUMNA_TIPO_USUARIO + ") references " + TABLA_TIPOS_USUARIO + " (" + COLUMNA_ID + "));");

        db.execSQL("create table " + TABLA_TAREAS + "(" +
                COLUMNA_ID + " integer primary key autoincrement, " +
                COLUMNA_NOMBRE + " text not null, " +
                COLUMNA_DESCRIPCION + " text not null, " +
                COLUMNA_COMPLETADA + " bool not null, " +
                COLUMNA_ID_USUARIO + " integer, " +
                "foreign key (" + COLUMNA_ID_USUARIO + ") references " + TABLA_USUARIOS + " (" + COLUMNA_ID + "));");

        db.execSQL("create table " + TABLA_ITEMS_WS + "(" +
                COLUMNA_ID + " integer primary key autoincrement, " +
                COLUMNA_ITEM + " text not null, " +
                COLUMNA_CATEGORY + " text, " +
                COLUMNA_BUSINESS + " text, " +
                COLUMNA_L + " text, " +
                COLUMNA_PHONE_1 + " text, " +
                COLUMNA_ZIPCODE + " text, " +
                COLUMNA_LATITUDE + " text, " +
                COLUMNA_LONGITUDE + " text, " +
                COLUMNA_HUMAN_ADDRESS + " text, " +
                COLUMNA_NEEDS_RECORDING + " bool, " +
                COLUMNA_WEBSITE + " text, " +
                COLUMNA_FARM_NAME + " text, " +
                COLUMNA_SUITE + " text" +
                ");");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String agregarUsuario(String username, String password, String nombre, String apellido, String tipoUsuarioId)
    {
        SQLiteDatabase db = this.getWritableDatabase();;

        ContentValues datos = new ContentValues();

        datos.put(COLUMNA_USERNAME, username);
        datos.put(COLUMNA_PASSWORD, password);
        datos.put(COLUMNA_NOMBRE, nombre);
        datos.put(COLUMNA_APELLIDO, apellido);
        datos.put(COLUMNA_TIPO_USUARIO, tipoUsuarioId);

        long id = db.insert(TABLA_USUARIOS, null, datos);

        db.close();

        System.out.println("Usuario " + id + " creado");

        return String.valueOf(id);
    }



    public Usuario obtenerUsuario(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO, COLUMNA_USERNAME, COLUMNA_TIPO_USUARIO};

        Cursor cursor = db.query(TABLA_USUARIOS, columnas,
                        COLUMNA_ID + " = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

        System.out.println(cursor.toString());

        db.close();

        return new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), obtenerTipoUsuario(cursor.getString(4)), obtenerTareasByUsuarioId(id));
    }

    public Usuario obtenerUsuarioByUsername(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO, COLUMNA_USERNAME, COLUMNA_PASSWORD, COLUMNA_TIPO_USUARIO};

        Cursor cursor = db.query(TABLA_USUARIOS, columnas,
                COLUMNA_USERNAME + " = ?",
                new String[] { username },
                null,
                null,
                null,
                null);

        System.out.println("obtenerUsuarioByUsername: " + cursor.toString());

        if (cursor != null)
            cursor.moveToFirst();
        else
        {
            db.close();
            return null;
        }

        Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), obtenerTipoUsuario(cursor.getString(5)), obtenerTareasByUsuarioId(cursor.getString(0)));

        db.close();

        return usuario;
    }

    public Usuario[] obtenerUsuariosExceptAdministrador()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO, COLUMNA_USERNAME, COLUMNA_TIPO_USUARIO};

        Cursor cursor = db.query(TABLA_USUARIOS, columnas,
                COLUMNA_TIPO_USUARIO + " != ?",
                new String[] { "1" },
                null,
                null,
                null,
                null);

        System.out.println(cursor.toString());

        if (cursor != null)
            cursor.moveToFirst();
        else
            return new Usuario[0];

        Usuario[] usuarios = new Usuario[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            usuarios[i] = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), obtenerTipoUsuario(cursor.getString(4)), obtenerTareasByUsuarioId(cursor.getString(0)));
            cursor.moveToNext();
        }

        db.close();

        return usuarios;
    }


    public String agregarTipoUsuario(String nombre)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues datos = new ContentValues();

        datos.put(COLUMNA_NOMBRE, nombre);

        long id = db.insert(TABLA_TIPOS_USUARIO, null, datos);
        db.close();

        System.out.println("Tipo de Usuario " + id + " creado");

        return String.valueOf(id);
    }

    public TipoUsuario obtenerTipoUsuario(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE};

        Cursor cursor = db.query(TABLA_TIPOS_USUARIO, columnas,
                COLUMNA_ID + " = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        System.out.println(cursor.toString());

        db.close();

        return new TipoUsuario(id, cursor.getString(1));
    }


    public String agregarTarea(String nombre, String descripcion, String id_usuario, boolean completada)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues datos = new ContentValues();

        datos.put(COLUMNA_NOMBRE, nombre);
        datos.put(COLUMNA_DESCRIPCION, descripcion);
        datos.put(COLUMNA_COMPLETADA, completada);
        datos.put(COLUMNA_ID_USUARIO, id_usuario);

        long id = db.insert(TABLA_TAREAS, null, datos);
        db.close();

        System.out.println("Tarea " + id + " creada");

        return String.valueOf(id);
    }

    public Tarea obtenerTarea(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO, COLUMNA_ID_USUARIO, COLUMNA_COMPLETADA};

        Cursor cursor = db.query(TABLA_TAREAS, columnas,
                COLUMNA_ID + " = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        System.out.println(cursor.toString());

        db.close();

        boolean completada = false;
        if(cursor.getString(4).equals("1"))
            completada = true;
        return new Tarea(id, cursor.getString(1), cursor.getString(2), cursor.getString(3), completada);
    }

    public Tarea[] obtenerTareasByUsuarioId(String id_usuario)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_DESCRIPCION, COLUMNA_ID_USUARIO, COLUMNA_COMPLETADA};

        Cursor cursor = db.query(TABLA_TAREAS, columnas,
                COLUMNA_ID_USUARIO + " = ?",
                new String[] { id_usuario },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;

        System.out.println(cursor.toString());

        db.close();

        Tarea[] tareas = new Tarea[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++)
        {
            boolean completada = false;
            if(cursor.getString(4).equals("1"))
                completada = true;
            tareas[i] = new Tarea(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), completada);
            cursor.moveToNext();
        }

        return tareas;
    }

    public Tarea[] obtenerTareas()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_DESCRIPCION, COLUMNA_ID_USUARIO, COLUMNA_COMPLETADA};

        Cursor cursor = db.query(TABLA_TAREAS, columnas,
                null,
                new String[] { },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;

        System.out.println(cursor.getCount() + " tareas encontradas");

        Tarea[] tareas = new Tarea[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++)
        {
            boolean completada = false;
            if(cursor.getString(4).equals("1"))
                completada = true;
            tareas[i] = new Tarea(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), completada);
            cursor.moveToNext();
        }

        db.close();

        return tareas;
    }

    public boolean actualizarTarea(String id, boolean completada)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues datos = new ContentValues();

        datos.put(COLUMNA_COMPLETADA, completada);

        boolean isUpdated = db.update(TABLA_TAREAS, datos, COLUMNA_ID + "= ?", new String[] { id }) > 0;

        db.close();

        return isUpdated;
    }

    public boolean eliminarTarea(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isDeleted = db.delete(TABLA_TAREAS, COLUMNA_ID + "= ?", new String[] { id }) > 0;

        db.close();

        return isDeleted;
    }

    public String agregarItemWS(String item, String business, String category, String l, String phone1, String zipcode, String latitude, String longitude, String human_address, boolean needs_recording, String website, String farm_name, String suite)
    {
        SQLiteDatabase db = this.getWritableDatabase();;

        ContentValues datos = new ContentValues();

        datos.put(COLUMNA_ITEM, item);
        datos.put(COLUMNA_BUSINESS, business);
        datos.put(COLUMNA_CATEGORY, category);
        datos.put(COLUMNA_L, l);
        datos.put(COLUMNA_PHONE_1, phone1);
        datos.put(COLUMNA_ZIPCODE, zipcode);
        datos.put(COLUMNA_LATITUDE, latitude);
        datos.put(COLUMNA_LONGITUDE, longitude);
        datos.put(COLUMNA_HUMAN_ADDRESS, human_address);
        datos.put(COLUMNA_NEEDS_RECORDING, needs_recording);
        datos.put(COLUMNA_WEBSITE, website);
        datos.put(COLUMNA_FARM_NAME, farm_name);
        datos.put(COLUMNA_SUITE, suite);

        long id = db.insert(TABLA_ITEMS_WS, null, datos);

        db.close();

        System.out.println("ItemWS " + id + " creado");

        return String.valueOf(id);
    }

    public ItemWS[] obtenerItemsWS()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = {COLUMNA_ITEM, COLUMNA_BUSINESS, COLUMNA_CATEGORY, COLUMNA_L, COLUMNA_PHONE_1, COLUMNA_ZIPCODE, COLUMNA_LATITUDE, COLUMNA_LONGITUDE, COLUMNA_HUMAN_ADDRESS, COLUMNA_NEEDS_RECORDING, COLUMNA_WEBSITE, COLUMNA_FARM_NAME, COLUMNA_SUITE};

        Cursor cursor = db.query(TABLA_ITEMS_WS, columnas,
                null,
                null,
                null,
                null,
                null,
                null);

        System.out.println(cursor.toString());

        if (cursor != null)
            cursor.moveToFirst();
        else
            return new ItemWS[0];

        ItemWS[] items = new ItemWS[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            items[i] = new ItemWS(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), new LocationWS(cursor.getString(8), cursor.getString(6), cursor.getString(7), Boolean.parseBoolean(cursor.getString(8))), new WebsiteWS(cursor.getString(9)), cursor.getString(10), cursor.getString(11));
            cursor.moveToNext();
        }

        db.close();

        return items;
    }

}
