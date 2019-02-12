package com.bilfind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * This class is used to show the locations of specific rooms in Bilkent University Buildings
 * @author Masna Ahmed
 * @version 12/05/2017
 */

public class ViewFloor extends AppCompatActivity {

    String location;

    @Override
    /**
     * This methods sets up the main page of the application on start up
     * @param savedInstanceState - the bundle that holds the prior information (if any) of the application otherwise being null
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_floor);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        location = this.getIntent().getExtras().getString("location");                              //get location from map activity

        ImageView img = (ImageView) findViewById(R.id.img);                                         //find image view to set image
        if (location.charAt(2) == 'B'){                                                             //if B-building
            if (location.charAt(3) == 'Z')
                img.setImageResource(R.mipmap.bz);                                                  //set image according to floor
            else if (location.charAt(3) == '1')
                img.setImageResource(R.mipmap.b1);                                                  //,, ,, ,,
            else if (location.charAt(3) == '2')
                img.setImageResource(R.mipmap.b2);                                                  //,, ,, ,,
            else if (location.charAt(3) == '3')
                img.setImageResource(R.mipmap.b3);                                                  //,, ,, ,,
            else if (location.charAt(3) == 'B')
                img.setImageResource(R.mipmap.bb);                                                  //,, ,, ,,
        }
        else if (location.charAt(2) == 'E' && location.charAt(3) == 'E') {
            if (location.charAt(4) == '0')
                img.setImageResource(R.mipmap.ee_basement);
            else if (location.charAt(4) == '1')
                img.setImageResource(R.mipmap.ee_first);
            else if (location.charAt(4) == '2')
                img.setImageResource(R.mipmap.ee_second);
            else if (location.charAt(4) == '3')
                img.setImageResource(R.mipmap.ee_third);
            else if (location.charAt(4) == '4')
                img.setImageResource(R.mipmap.ee_fourth);
            else if (location.charAt(4) == '5')
                img.setImageResource(R.mipmap.ee_fifth);
        }
    }

    /*
    * onOptionsItemSelected boolean method which is basic activity for going to main menu
    * if item is home, activity will finish and return to home page
    * @param item MenuItem which is defined by activities
    * @return true
    * @return super.onOptionItemSelected(item)
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

    /*
    * onBackPressed void method
    * It is for button of going back to main page
    */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
