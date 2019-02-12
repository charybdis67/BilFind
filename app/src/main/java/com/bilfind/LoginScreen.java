package com.bilfind;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides the user a login screen to log onto the application
 * @author Masna Ahmed
 * @version 12/05/2017
 */

public class LoginScreen extends AppCompatActivity {

    ArrayList<String> ids;

    /**
     * This methods sets up the main page of the application on start up
     * @param savedInstanceState - the bundle that holds the prior information (if any) of the application otherwise being null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();                                             //no need for an action bar here
        AutoCompleteTextView bilkentID_tf = (AutoCompleteTextView) findViewById(R.id.editText);     //finding the id text view
        final EditText password_tf = (EditText) findViewById(R.id.editText2);     //finding password field

        try {
            File f = new File(this.getFilesDir(), "userFile.txt");
            final Scanner scan = new Scanner(f);                                  //open user data file for reading
            String str = "";
            ids = new ArrayList<>();
            while (scan.hasNext())
            {
                str = scan.nextLine();
                ids.add(Data.extractString(str,1));                               //search for all ids add to ids Arraylist
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ids);
            bilkentID_tf.setAdapter(adapter);                                     //this will setup id suggestions for login

            bilkentID_tf.setOnItemClickListener(new AdapterView.OnItemClickListener() {             //setting listener to id
                @Override
                public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                    try {
                        File f2 = new File(LoginScreen.this.getFilesDir(), "userFile.txt");         //scan user file again
                        Scanner scanner = new Scanner(f2);
                        String str2 = "";
                        while (scanner.hasNext()) {
                            str2 = scanner.nextLine();
                            if (Data.extractString(str2,1).equals(LoginScreen.this.ids.get(position)) && Data.extractString(str2,3).equals("true"))
                                password_tf.setText(Data.extractString(str2,0));                    //if a remembered password then the set password in respective field
                        }
                    }
                    catch (FileNotFoundException e){}                                               //do nothing during setup in case of FNFE
                }
            });
        }
        catch (FileNotFoundException e)
        {}
     }

    /**
     * This method starts the Sign Up Activity
     * @param view - Takes current view which is the Sign Up textview
     */
    public void signUp(View view)
    {
        startActivity(new Intent(LoginScreen.this, SignUp.class));
        this.finish();
    }

    /**
     * This method handles the events that take place at sign up
     * @param view - takes the current view which is the login button
     */
    public void onLogin(View view)  {
        boolean found = false;
        AutoCompleteTextView bilkentID_tf = (AutoCompleteTextView) findViewById(R.id.editText);
        EditText password_tf = (EditText) findViewById(R.id.editText2);                             //find password and login plain texts
        String bilkentID = bilkentID_tf.getText().toString();
        String password = password_tf.getText().toString();                                         //get the text held by them

        if (bilkentID.equals(""))
            bilkentID = "none";
        if (password.equals(""))
            password = "nope";                                                                      //initialise if no data entered

        try {
            File file = new File(this.getFilesDir(), "userFile.txt");
            Scanner scan = new Scanner(file);                                                       //open user data file for reading
            String str = "";
            while (scan.hasNext()) {
                str = scan.nextLine();                                                              //scan each line of data and compare with data held by the textviews
                if (str.substring(0, password.length()).equals(password) && str.substring(str.indexOf(',') + 1, str.indexOf(',') + 1 + bilkentID.length()).equals(bilkentID)) {
                    found = true;
                    ProgressDialog prog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
                    prog.setTitle("Please Wait");
                    prog.setMessage("Loading application");
                    prog.show();                                                                    //create progress dialog till main activity loads
                    Intent i = new Intent(LoginScreen.this, MapsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", str);
                    i.putExtras(bundle);
                    startActivity(i);                                                               //start MapActivity with user data
                    this.finish();
                }
            }
            scan.close();
            if (!found)
                Toast.makeText(this, "Unable to Login", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e)
        {
            Toast.makeText(this, "Unable to Login", Toast.LENGTH_LONG).show();                      //otherwise show an error Toast
        }
    }
}
