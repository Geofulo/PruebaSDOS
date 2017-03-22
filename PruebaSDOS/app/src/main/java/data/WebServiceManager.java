package data;

import android.content.ClipData;
import android.os.AsyncTask;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import models.ItemWS;


/**
 * Created by geovanni on 21/03/17.
 */

public class WebServiceManager extends AsyncTask<String, Void, ItemWS[]>
{
    private final String URL = "https://data.ct.gov/resource/hma6-9xbg.json?category=Fruit&item=Peaches";

    RestTemplate restTemplate;

    public WebServiceManager() {
        restTemplate = new RestTemplate(true);
    }

    @Override
    protected ItemWS[] doInBackground(String... params) {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ItemWS[] items = restTemplate.getForObject(URL, ItemWS[].class);
        System.out.println(items.length + " itemsWS from obtenerItemsWS()");
        return items;
    }

    public ItemWS[] obtenerItemsWS()
    {
        ItemWS[] items = restTemplate.getForObject(URL, ItemWS[].class);
        System.out.println(items.length + " itemsWS from obtenerItemsWS()");
        return items;
    }

}
