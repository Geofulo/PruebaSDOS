package models;

import java.io.Serializable;

/**
 * Created by geovanni on 21/03/17.
 */

public class WebsiteWS implements Serializable
{
    String url;

    public WebsiteWS(String url) {
        this.url = url;
    }

    public WebsiteWS() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
