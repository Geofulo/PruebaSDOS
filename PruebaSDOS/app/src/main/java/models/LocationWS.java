package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by geovanni on 21/03/17.
 */

//@JsonIgnoreProperties(ignoreUnknown = true)

public class LocationWS implements Serializable
{
    String human_address;
    String latitude;
    String longitude;
    boolean needs_recoding;

    public LocationWS(String human_address, String latitude, String longitude, boolean needs_recording) {
        this.human_address = human_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.needs_recoding = needs_recording;
    }

    public LocationWS() {}

    public String getHuman_address() {
        return human_address;
    }

    public void setHuman_address(String human_address) {
        this.human_address = human_address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean getNeeds_recording() {
        return needs_recoding;
    }

    public void setNeeds_recording(boolean needs_recording) {
        this.needs_recoding = needs_recording;
    }
}
