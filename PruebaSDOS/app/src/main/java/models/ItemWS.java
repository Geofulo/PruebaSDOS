package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by geovanni on 21/03/17.
 */

//@JsonIgnoreProperties(ignoreUnknown = true)

public class ItemWS implements Serializable
{
    String item;
    String business;
    String category;
    String l;
    String phone1;
    String zipcode;
    LocationWS location_1;
    String farmer_id;
    WebsiteWS website;
    String farm_name;
    String suite;

    public ItemWS(String item, String business, String category, String l, String phone1, String zipcode, LocationWS location_1, WebsiteWS website, String farm_name, String suite) {
        this.item = item;
        this.business = business;
        this.category = category;
        this.l = l;
        this.phone1 = phone1;
        this.zipcode = zipcode;
        this.location_1 = location_1;
        this.website = website;
        this.farm_name = farm_name;
        this.suite = suite;
    }

    public ItemWS(){}

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public LocationWS getLocation_1() {
        return location_1;
    }

    public void setLocation_1(LocationWS location_1) {
        this.location_1 = location_1;
    }

    public WebsiteWS getWebsite() {
        return website;
    }

    public void setWebsite(WebsiteWS website) {
        this.website = website;
    }

    public String getFarm_name() {
        return farm_name;
    }

    public void setFarm_name(String farm_name) {
        this.farm_name = farm_name;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

}
