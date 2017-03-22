package data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import models.ItemWS;

/**
 * Created by geovanni on 16/03/17.
 */

public class DataManager  {

    public BaseDatosManager bdm;
    public WebServiceManager wsm;

    public ItemWS[] items;

    public DataManager(Context context)
    {
        bdm = new BaseDatosManager(context);
        wsm = new WebServiceManager();
    }

    public void cargarDatosFromWebService() throws ExecutionException, InterruptedException {
        items = new WebServiceManager().execute().get();
    }

    public void cargarBaseDatos()
    {
        String tu1 = bdm.agregarTipoUsuario("Administrador");
        String tu2 = bdm.agregarTipoUsuario("Técnico");

        String u1 = bdm.agregarUsuario("ruben_garcia", "123456", "Rubén", "García", tu1);
        String u2 = bdm.agregarUsuario("rafael_martin", "123456", "Rafael", "Martín", tu2);
        String u3 = bdm.agregarUsuario("sarah_lopez", "123456", "Sarah", "López", tu2);

        String t1 = bdm.agregarTarea("Reponedor de productos", "Se encarga de sustituir un producto dañado por otro en buen estado", u3, false);
        String t2 = bdm.agregarTarea("Cobrar", "Administrar el dinero que se recibe en caja", u2, false);
        String t3 = bdm.agregarTarea("Envolver", "Guardar los productos en sus cajas correspondientes", u3, false);

        for (int i = 0; i < items.length; i++)
        {
            String url_website = "";
            if(items[i].getWebsite() != null)
                url_website = items[i].getWebsite().getUrl();
            bdm.agregarItemWS(items[i].getItem(), items[i].getBusiness(), items[i].getCategory(), items[i].getL(), items[i].getPhone1(), items[i].getZipcode(), items[i].getLocation_1().getLatitude(), items[i].getLocation_1().getLongitude(), items[i].getLocation_1().getHuman_address(), items[i].getLocation_1().getNeeds_recording(), url_website, items[i].getFarm_name(), items[i].getSuite());
        }
    }
}
