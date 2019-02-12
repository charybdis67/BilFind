package com.bilfind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This class is used to for activity to create events.
 * @author Aliyu Saifullah Vandana and Masna Ahmed
 * @version 13/05/2017
 */

public class CreateEvent extends AppCompatActivity {
    //objects
    AutoCompleteTextView search_tf;
    String name,group,dept,timePlace,search;
    CheckBox cb;

    //methods
    @Override
    /**
     *This method creates CreateEvent Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);               //sets the layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        search_tf = (AutoCompleteTextView) findViewById(R.id.search);  //creates autocompletetextview object
        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, Data.locate);
        //creates adapter to link the autocompletetextview to the location names data.
        search_tf.setThreshold(1);                                     //sets the min number of characters the user must type for the suggestions to appear
        search_tf.setAdapter(aAdapter);                                //adds the above adapter to the autocompletetextview
        search_tf.setVisibility(View.INVISIBLE);
    }

    /**
     *This method method lets add location to the event if checkbox is checked by making add location textfield visible..
     *@param view the graphics of the activity
     */
    public void onCheckBoxClick(View view){
        cb = (CheckBox) findViewById(R.id.checkBox);
        if (cb.isChecked())                                  //if check box is checked then set the location textfield visible
            search_tf.setVisibility(View.VISIBLE);           //to let the user enter the location.
        else
            search_tf.setVisibility(View.INVISIBLE);
    }

    /**
     *This method lets the user create an event by saving it to a text file.
     *@param view the graphics of the activity
     */
    public void onGo(View view)
    {
        cb = (CheckBox) findViewById(R.id.checkBox);
        try {
            //get the string values from the user from the textfields.
            name = ((EditText) findViewById(R.id.eventName)).getText().toString();
            group = ((EditText) findViewById(R.id.group)).getText().toString();
            dept = ((EditText) findViewById(R.id.department)).getText().toString();
            timePlace= ((EditText) findViewById(R.id.timePlace)).getText().toString();
            search = search_tf.getText().toString();

            File file = new File(this.getFilesDir(), "eventFile.txt");           //create a text file into which events data will be saved.
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));  //attaches a printwriter to the text file.
            //in case an entry is left empty, it is set to be not specified instead of empty string.
            if (name.equals(""))
                name = "Unnamed";
            if (group.equals(""))
                group = "no one";
            if (dept.equals(""))
                dept = "no department";
            if (timePlace.equals(""))
                timePlace = "not specified";

            //if all entries are not empty then adds the event data to the text file.
            if (name.equals("") && group.equals("") && dept.equals("") && timePlace.equals("") && search.equals("") )
            {}
            else {
                if (cb.isChecked())                          //adds location too.
                    pw.append(this.getIntent().getExtras().getString("user") + "," + name.toUpperCase() + "," + group.toUpperCase() + "," + dept.toUpperCase() + "," + timePlace.toUpperCase() + "," + search + "\r\n");
                else
                    pw.append(this.getIntent().getExtras().getString("user") + "," + name.toUpperCase() + "," + group.toUpperCase() + "," + dept.toUpperCase() + timePlace.toUpperCase() + "\r\n");
                pw.close();

                Toast.makeText(this, "Data added", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        catch (FileNotFoundException e)
        {
            Toast.makeText(this, "File Not Found", Toast.LENGTH_LONG).show();    //notify if the file is not found.
        }
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
        Toast.makeText(this, "Event Discarded!",Toast.LENGTH_LONG).show();
    }
}