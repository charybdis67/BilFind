package com.bilfind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HelpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
