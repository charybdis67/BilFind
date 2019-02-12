package com.bilfind;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used for the activity of searching bus stops in Bilkent
 * @author Kasymbek Tashbaev
 */
public class BusSearchBus extends Activity {
    //variables
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    AutoCompleteTextView editText;

    //methods
    /**
     *This method creates BusSearchBus Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);
        listView = (ListView) findViewById(R.id.listview);                  //creates list view for different bus routes
        editText = (AutoCompleteTextView) findViewById(R.id.txtsearch);     //creates autocomplete textview for bus search
        initList();                                                         //creates list of all bus stops
    }

    /**
     * This method takes search text and opens corresponding bus timing chart
     * @param v - the cuurent view (search button)
     */
    public void onTextChanged(View v) {
        String s = ( (AutoCompleteTextView) findViewById(R.id.txtsearch)).getText().toString();
        if(s.toString().equals("")){
            initList();
        }
        //opens an activity which contains an image for the bus timings.
        else{
            searchItem(s.toString().toUpperCase());
            //if search contains one of the following stops, open activity with the image displaying the timings
            if(s.toString().toUpperCase().equals("TUNUS") | s.toString().toUpperCase().equals("SIHIYE") | s.toString().toUpperCase().equals("BAHCELIEVLER") | s.toString().toUpperCase().equals("TANDOGAN") | s.toString().toUpperCase().equals("ASTI") )
            {
                Intent i_mainCamp = new Intent(BusSearchBus.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main Campus");
                i_mainCamp.putExtras(bundle);
                startActivity(i_mainCamp);
            }
            //if search contains these stops, open another activity with another image
            if(s.toString().toUpperCase().equals("FACULTY HOUSING BLOCK 31") | s.toString().toUpperCase().equals("PLAYGROUND") | s.toString().toUpperCase().equals("FACULTY HOUSING BLOCK 11") | s.toString().toUpperCase().equals("LIBRARY") | s.toString().toUpperCase().equals("ADMINISTRATION") | s.toString().toUpperCase().equals("DORM 77") | s.toString().toUpperCase().equals("DORM 72") )
            {
                Intent i_MainRing = new Intent(BusSearchBus.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main Ring");
                i_MainRing.putExtras(bundle);
                startActivity(i_MainRing);
            }
            //if search contains these stops, open another activity with another image
            if(s.toString().toUpperCase().equals("NIZAMIYE") | s.toString().toUpperCase().equals("KTPH") | s.toString().toUpperCase().equals("IISBF") | s.toString().toUpperCase().equals("103") | s.toString().toUpperCase().equals("DOGU") | s.toString().toUpperCase().equals("TOMYO") )
            {
                Intent i_MainEast = new Intent(BusSearchBus.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Main East");
                i_MainEast.putExtras(bundle);
                startActivity(i_MainEast);
            }
            //if search contains these stops, open another activity with another image
            if(s.toString().toUpperCase().equals("UMITKOY") | s.toString().toUpperCase().equals("KONUTKENT") | s.toString().toUpperCase().equals("KORU SITESI") | s.toString().toUpperCase().equals("CAYYOLU") | s.toString().toUpperCase().equals("BEYSUKENT") | s.toString().toUpperCase().equals("DIKMEN"))
            {
                Intent i_Regional = new Intent(BusSearchBus.this, ViewBusTimings.class);
                Bundle bundle = new Bundle();
                bundle.putString("whichTimings", "Regional");
                i_Regional.putExtras(bundle);
                startActivity(i_Regional);
            }
        }
    }


    /**
     *This method searches for a string in the array containing bus stop names, removes the name for listitems arraylist if the text to search is not contained in those stops
     *In other words, removes those items from the arraylist which donot contain the text to search
     *@param textToSearch the bus stop beings searched
     */
    public void searchItem(String textToSearch){

        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);             //removes that item if it does not contain the desired string.
            }
        }
        adapter.notifyDataSetChanged();

    }

    /**
     *This method creates a list of bus stops in an array and adds them to an array adapter, in turn adds it to autocomplete text view.
     */
    public void initList(){

        items = new String[]{"TUNUS","SIHIYE", "BAHCELIEVLER", "TANDOGAN", "ASTI",
                "FACULTY HOUSING BLOCK 31", "PLAYGROUND","FACULTY HOUSING BLOCK 11","LIBRARY","ADMINISTRATION", "DORM 77", "DORM 72",
                "NIZAMIYE", "KTPH", "IISBF", "103", "DOGU", "TOMYO",
                "UMITKOY ","KONUTKENT", "KORU SITESI", "CAYYOLU", "BEYSUKENT", "DIKMEN"};

        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
        editText.setAdapter(adapter);
        editText.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            /**
             *This method
             *@param
             *@param
             *@return false
             */
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                // TODO Auto-generated method stub
                editText.showDropDown();                         //show drop down animation
                editText.requestFocus();                         //focus to the current view
                return false;
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