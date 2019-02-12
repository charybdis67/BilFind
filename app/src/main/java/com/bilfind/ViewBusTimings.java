package com.bilfind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * This class provides the view to see bus timings
 * @author Masna Ahmed
 * @version 14/05/2017
 */

public class ViewBusTimings extends AppCompatActivity {

    /**
     *This method creates BusActivity Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_bus_timings);

        ImageView img = (ImageView) findViewById(R.id.imgImg);                                       //obtain image view
        if (this.getIntent().getExtras().getString("whichTimings").equals("Main Campus"))
        {
            img.setImageResource(R.mipmap.main_sch);                                                //set to specified timings image
        }
        if (this.getIntent().getExtras().getString("whichTimings").equals("East Campus"))
        {
            img.setImageResource(R.mipmap.east_sch);                                                //set to specified timings image
        }
        if (this.getIntent().getExtras().getString("whichTimings").equals("Main East"))
        {
            img.setImageResource(R.mipmap.mian_east_sch);                                           //set to specified timings image
        }
        if (this.getIntent().getExtras().getString("whichTimings").equals("Regional"))
        {
            img.setImageResource(R.mipmap.reg_sch);                                                //set to specified timings image
        }
        if (this.getIntent().getExtras().getString("whichTimings").equals("Main Ring"))
        {
            img.setImageResource(R.mipmap.mian_ring);                                                //set to specified timings image
        }
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

    /**
     * This method specifies what to do in case the user presses back
     */
    @Override
    public void onBackPressed(){
        this.finish();
        super.onBackPressed();
    }
}
