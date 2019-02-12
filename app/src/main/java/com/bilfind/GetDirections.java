package com.bilfind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * This class provides the user, the ability to search for a route between two locations
 * @author Masna Ahmed
 * @version 14/05/2017
 */

public class GetDirections extends AppCompatActivity {

    /**
     *This method creates BusActivity Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_directions);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //display back button

        ArrayAdapter<String> aAdapter1 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, Data.locate);
        AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);      //find from location text view
        acTextView1.setThreshold(1);
        acTextView1.setAdapter(aAdapter1);           //set adapter to list of location

        ArrayAdapter<String> aAdapter2 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, Data.locate);
        AutoCompleteTextView acTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);     //find to location text view
        acTextView2.setThreshold(1);
        acTextView2.setAdapter(aAdapter2);           //set adapter to list of location
    }

    /**
     * This method specifies what to do when user has searched
     * @param view - takes the current view (search button)
     */
    public void onSearch(View view){
        AutoCompleteTextView fromLocationACTW = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        AutoCompleteTextView toLocationACTW = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);           //find from and to search views
        String fromLocation = fromLocationACTW.getText().toString();
        String toLocation = toLocationACTW.getText().toString();
        PointDouble p1 = new PointDouble();
        PointDouble p2 = new PointDouble();
        for (int i = 0; i < Data.locate.length; i++) {
            if (fromLocation.equals(Data.locate[i])) {
                p1 = Data.coordinates[i];                           //find coordinates of first location
            }
        }
        for (int i = 0; i < Data.locate.length; i++) {
            if (toLocation.equals(Data.locate[i]))
            {
                p2 = Data.coordinates[i];                          //find coordinates of second location
            }
        }
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,                  //start activity in Google Maps
        Uri.parse("http://maps.google.com/maps?saddr=" + p1.getX() + "," + p1.getY() + "&daddr=" +  + p2.getX() + "," + p2.getY()));
        startActivity(intent);
    }

    @Override
    /**
     This method ************
     *@param item the MenuItem
     *@return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
        }

    @Override
    /**
     *This method returns the user to the previous screen.
     */
    public void onBackPressed() {
        super.onBackPressed();
    }

}
