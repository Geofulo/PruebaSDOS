package com.geoapps.pruebasdos.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.geoapps.pruebasdos.R;

import models.ItemWS;
import models.Tarea;

public class VerItemActivity extends AppCompatActivity {

    ItemWS itemWS;

    TextView itemTextView;
    TextView businessTextView;
    TextView categoryTextView;
    TextView lTextView;
    TextView phoneTextView;
    TextView zipcodeTextView;
    TextView addressTextView;
    TextView coorTextView;
    TextView farmTextView;
    TextView websiteTextView;
    TextView suiteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_item);

        itemWS = (ItemWS) getIntent().getSerializableExtra("item");

        this.setTitle("Item \"" + itemWS.getItem() + " \"");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ver_item);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemTextView = (TextView) findViewById(R.id.tv_item_value);
        businessTextView = (TextView) findViewById(R.id.tv_business_value);
        categoryTextView = (TextView) findViewById(R.id.tv_category_value);
        lTextView = (TextView) findViewById(R.id.tv_l_value);
        phoneTextView = (TextView) findViewById(R.id.tv_phone_value);
        zipcodeTextView = (TextView) findViewById(R.id.tv_zipcode_value);
        addressTextView = (TextView) findViewById(R.id.tv_address_value);
        coorTextView = (TextView) findViewById(R.id.tv_coor_value);
        farmTextView = (TextView) findViewById(R.id.tv_farm_name_value);
        websiteTextView = (TextView) findViewById(R.id.tv_website_value);
        suiteTextView = (TextView) findViewById(R.id.tv_suite_value);

        itemTextView.setText(itemWS.getItem());

        if (itemWS.getBusiness() != null)
            businessTextView.setText(itemWS.getBusiness());
        if (itemWS.getCategory() != null)
            categoryTextView.setText(itemWS.getCategory());
        if (itemWS.getL() != null)
            lTextView.setText(itemWS.getL());
        if (itemWS.getPhone1() != null)
            phoneTextView.setText(itemWS.getPhone1());
        if (itemWS.getZipcode() != null)
            zipcodeTextView.setText(itemWS.getZipcode());
        if (itemWS.getLocation_1() != null)
        {
            addressTextView.setText(itemWS.getLocation_1().getHuman_address());
            coorTextView.setText(itemWS.getLocation_1().getLatitude() + " - " + itemWS.getLocation_1().getLongitude());
        }
        if (itemWS.getFarm_name() != null)
            farmTextView.setText(itemWS.getFarm_name());
        if (itemWS.getWebsite() != null)
            websiteTextView.setText(itemWS.getWebsite().getUrl());
        if (itemWS.getSuite() != null)
            suiteTextView.setText(itemWS.getSuite());
    }
}
