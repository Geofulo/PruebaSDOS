package com.geoapps.pruebasdos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.ExecutionException;

import data.DataManager;

/**
 * Created by geovanni on 18/03/17.
 */

public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    public static DataManager dataManager;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        dataManager = new DataManager(this);

        SharedPreferences preferences = getSharedPreferences("INITIAL_DATA", 0);
        boolean isFirst = preferences.getBoolean("INITIAL_DATA", true);
        if (isFirst)
        {
            try {
                dataManager.cargarDatosFromWebService();
                dataManager.cargarBaseDatos();
                preferences.edit().putBoolean("INITIAL_DATA", false).commit();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
