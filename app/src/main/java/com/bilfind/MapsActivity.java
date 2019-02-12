package com.bilfind;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class will act as the main page of the application where the locating will actually take place
 * @author Masna Ahmed
 * @version 12/05/2017
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    GoogleMap mMap;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    String[] items = new String[]{"Details","Name Masna Ahmed", "Dept CS", "BilkentId 21603228", "Groups CS102-D"};
    String user;
    Bundle bundle;

    @Override
    /**
     * This methods sets up the main page of the application on start up
     * @param savedInstanceState - the bundle that holds the prior information (if any) of the application otherwise being null
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);                  //setting view of the application to corresponding xml file
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);                     // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment.getMapAsync(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);      //finding drawer layout in xml file
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);    //initialising a toggle button for navigation drawer

        mDrawerLayout.addDrawerListener(mToggle);                           //connecting toggle with drawer
        mToggle.syncState();                                                //syncing the toggle with current drawer state

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);         //providing a back button

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);             //setting up the navigation view and connecting to activity

        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, Data.locate);
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.TFaddress);      //finding search bar
        acTextView.setThreshold(0);                                         //for minimum number of characters input before suggestions show
        acTextView.setAdapter(aAdapter);                                    //setting up Bilkent Locations with suggestions

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);                             //finding navigation view header
        Spinner spinner = (Spinner) header.findViewById(R.id.spinner1);     //finding details spinner
        String[] items = new String[]{"Details","Name " + Data.extractString(this.getIntent().getExtras().getString("data"),2),"BilkentID " + Data.extractString(this.getIntent().getExtras().getString("data"),1)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setEnabled(true);
        spinner.setAdapter(adapter);                                        //setting spinner with current user data

        user = Data.extractString(this.getIntent().getExtras().getString("data"), 2);
        bundle = new Bundle();
        bundle.putString("user",user);                                      //defining user from data retrieved from login screen
    }

    /**
     * This method determines what to do if back toggle is pressed
     * @param item - referring to the toggle item
     * @return boolean value after performing tasks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method specifies what to do in case the user presses back
     */
    public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);             //create an alert dialog builder
            builder.setTitle("Exit?");                                               //setting its title
            builder.setMessage("Do you want to exit?");                              //setting appropriate message
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {  //adding listener for ok button
                        public void onClick(DialogInterface dialog, int id) {
                            finish();                                                //finishes the current activity
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {        //adding listener for cancel button
                            dialog.cancel();                                         //cancel the dialog
                        }
                    });

            AlertDialog alertDialog = builder.create();                              //creating alert dialog with builder
            alertDialog.show();                                                      //display dialog
    }

    /**
     * This method provides functionality to the navigation drawer items
     * @param item - the item that is selected (tapped on)
     * @return a boolean value after the following statements are executed
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();                                                 //get id of the selected item

        if (id == R.id.nav_VCMUP) {
            Intent i = new Intent(MapsActivity.this, ViewEvents.class);
            i.putExtras(bundle);
            startActivity(i);                                                      //start view events activity if selected
        } else if (id == R.id.nav_GetDirections) {
            startActivity(new Intent(MapsActivity.this, GetDirections.class));    //start get directions activity when selected
        }   else if (id == R.id.nav_BusInfo) {
            startActivity(new Intent(MapsActivity.this, BusActivity.class));       //start bus info activity when selected
        } else if (id == R.id.nav_Help) {
            startActivity(new Intent(MapsActivity.this, HelpScreen.class));        //start help activity when selected
        } else if (id == R.id.nav_Exit) {
            startActivity(new Intent(MapsActivity.this,LoginScreen.class));        //logout if selected
            this.finish();
        } else if (id == R.id.nav_SetEvent) {
            Intent i = new Intent(MapsActivity.this, CreateEvent.class);
            i.putExtras(bundle);
            startActivity(i);                                                      //set an event taking user data when selected
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);                                   //close the drawer pushing it inside
        return true;
    }

    /**
     * This method searches for a particular location on campus
      * @param view - takes the current view
     */
    public void onSearch(View view) {
        AutoCompleteTextView location_tf = (AutoCompleteTextView) findViewById(R.id.TFaddress);     //find the search bar
        final String location = location_tf.getText().toString();                                   //find text in search bar
        mMap.setMaxZoomPreference(20);
        mMap.clear();                                  //clear the map
        if (!location.equals("")) {
            if (!(location.charAt(1) == '-')) {                                          //if not searching for a room
                for (int i = 0; i < Data.locate.length; i++) {                           //search for every location on campus
                    if (location.equals(Data.locate[i])) {                               //if location found...
                        LatLng latLng = new LatLng(Data.coordinates[i].getX(), Data.coordinates[i].getY());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(Data.locate[i]));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));       //add marker and animate camera
                        i = Data.locate.length;                                          //end loop
                    }
                }
            }
            else if (location.charAt(0) == 'r' && location.charAt(1) == '-') {           //if to find a room
                for (int i = 0; i < Data.locate.length; i++) {                           //find its building
                    if ((location.charAt(2) + " building").equals(Data.locate[i]) || (location.charAt(2) + "" + location.charAt(3) + " building").equals(Data.locate[i])) {
                        LatLng latLng = new LatLng(Data.coordinates[i].getX(), Data.coordinates[i].getY());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(Data.locate[i]));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));       //set marker at building
                        i = Data.locate.length;
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {       //set listener for marker
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            //int position = (int) (marker.getTag());
                            //Using position get Value from arraylist
                            if (location.charAt(2) == 'B' || (location.charAt(2) == 'E' && (location.charAt(3) == 'E'))) {
                                Intent i = new Intent(MapsActivity.this, ViewFloor.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("location", location);
                                i.putExtras(bundle);
                                startActivity(i);                                                   //start the ViewFloor activity
                            } else
                                Toast.makeText(MapsActivity.this, "Sorry! Floor Plan not available", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    });
                }
            }
            }
            else {
                Toast.makeText(this, "Enter a place to search", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * This method initialises the map fragment
     * @param googleMap - the google map fragment we are using
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLngBounds BILKENT = new LatLngBounds(
                new LatLng(39.866190, 32.743340), new LatLng(39.877980, 32.755558));                //setting bounds to Bilkent
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BILKENT.getCenter(), 15));
        mMap.setLatLngBoundsForCameraTarget(BILKENT);                                               //applying bounds on fragment
        mMap.addMarker(new MarkerOptions().position(new LatLng(39.874617, 32.747513)).title("Bilkent University"));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true); //opening own location
    }
}
