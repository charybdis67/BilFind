package com.bilfind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
/**
 * This class presents user with a list of bus routes to select from, after which the user is directed to other respective classes.
 * @author Kasymbek Tashbaev
 * @version 14/05/2017
 */
public class BusActivity extends AppCompatActivity {

    @Override
    /**
     *This method creates BusActivity Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Buttons below open a new page that contains bus schedule accordingly
        //for Main Campus
        Button goMainCamp = (Button)findViewById(R.id.buttonMainCamp);
        goMainCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_mainCamp = new Intent(BusActivity.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main Campus");
                i_mainCamp.putExtras(bundle);
                startActivity(i_mainCamp);
            }
        });
        //for east campus
        Button goEastCamp = (Button)findViewById(R.id.buttonEastCamp);
        goEastCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_EastCamp = new Intent(BusActivity.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "East Campus");
                i_EastCamp.putExtras(bundle);
                startActivity(i_EastCamp);
            }
        });

        //for main campus ring
        Button goMainRing = (Button)findViewById(R.id.buttonMainRing);
        goMainRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_MainRing = new Intent(BusActivity.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main Ring");
                i_MainRing.putExtras(bundle);
                startActivity(i_MainRing);
            }
        });
        //main-east ring
        Button goMainEast = (Button)findViewById(R.id.buttonMainEast);
        goMainEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_MainEast = new Intent(BusActivity.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main East");
                i_MainEast.putExtras(bundle);
                startActivity(i_MainEast);
            }
        });
        //for regional bus services
        Button goRegional = (Button)findViewById(R.id.buttonRegional);
        goRegional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_Regional = new Intent(BusActivity.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Regional");
                i_Regional.putExtras(bundle);
                startActivity(i_Regional);
            }
        });

        //this button opens a search page for bus stop search
        Button goSearchBus = (Button)findViewById(R.id.buttonSearchBus);
        goSearchBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_SearchBus = new Intent(BusActivity.this, BusSearchBus.class);       //start SearchBus activity
                startActivity(i_SearchBus);
            }
        });
    }

    /**
     * This method determines what to do if back toggle is pressed
     * @param item - referring to the toggle item
     * @return boolean value after performing tasks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
        }
    }