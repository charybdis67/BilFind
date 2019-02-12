package com.bilfind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class is used to for the activity to view events of a user.
 * @author Aliyu Saifullah Vandana
 */
public class ViewEvents extends AppCompatActivity {
    //objects
    ArrayList<String> events;
    String str = "";
    ListView lv;
    //methods
    @Override
    /**
     *This method creates ViewEvents Activity by initialising certain things.
     *@param savedInstanceState bundle of values
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_view_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String user = this.getIntent().getExtras().getString("user");          //gets user name
        events = new ArrayList<>();
        try {
            File file = new File(this.getFilesDir(), "eventFile.txt");         //gets the events file.
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {                                           //scans the file for events
                str = scan.nextLine();
                if (Data.extractString(str, 0).equals(user))                   //if usernames in the event matches the user's username, add the event to arraylist.
                    events.add(str);
            }                                                                  //adds the events arraylist to an arayadapter to be displayed on the screen
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, events);
            lv = (ListView) findViewById(R.id.showDataLV);                     //creates a listview object which will contain the events data.
            lv.setAdapter(adapter);                                            //adds the adapter with the events data to the list view such that events are displayed.
            //set click listener for an event.
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String event = (String) lv.getItemAtPosition(position);     //gets the event where the user clicks
                    String location = Data.extractString(event, 5);             //extract location data from that event.
                    for (int i = 0; i < Data.locate.length; i++) {              //looks for the coordinates of that location.
                        if (location.equals(Data.locate[i])) {
                            PointDouble point = Data.coordinates[i];            //opens the location in google maps.
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("http://maps.google.com/maps?q=" + point.getX() + "," + point.getY()));
                            startActivity(intent);
                        }
                    }

                }
            });
            registerForContextMenu(lv);                                          //allow this list to open the context menu
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found", Toast.LENGTH_LONG);
        }

    }

    /**
     * This method determines what to do if back toggle is pressed
     * @param item - referring to the toggle item
     * @return boolean value after performing tasks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method specifies what to do in case the user presses back
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * This method determines how to create the context menu
     * @param menu - the menu to create
     * @param v - the view in question, in this case the listview
     * @param menuInfo - additional info regarding the menu
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.showDataLV) {                                //if the listview invoked the menu
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);                      //inflate the menu
        }
    }

    /**
     * This method determines what to do when a context menu item is selected
     * @param item - the selected item e.g. "delete"
     * @return - a boolean value after the action has been done
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;                                         //get the index of selected item that caused the context menu
        String dataToDelete = events.get(index);                           //get data from events arraylist to be deleted
        if (item.getItemId() == R.id.delete) {                             //if delete invoked
            // remove stuff here
            try {
                File file = new File(this.getFilesDir(), "eventFile.txt");
                File temp = new File(this.getFilesDir(), "temp.txt");       //create a dummy files
                PrintWriter pw = new PrintWriter(new FileOutputStream(temp, true));
                Scanner scanner = new Scanner(file);                        //scan the events file
                String line = "";
                while (scanner.hasNext()) {
                    line = scanner.nextLine();
                    if (!line.equals(dataToDelete)) {
                        pw.append(line);                                    //append all data except one to delete
                    }
                }
                scanner.close();
                pw.close();
                temp.renameTo(file);
                temp.delete();                                              //rename dummy file to eventFile and delete dummy

                Intent intent = this.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);                                      //refresh activity without animation
                this.overridePendingTransition(0, 0);                       //removing after animation

                return true;
            } catch (FileNotFoundException e) {                             //catch any exception
            }
        }
        return super.onContextItemSelected(item);

    }
}