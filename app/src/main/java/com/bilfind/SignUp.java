package com.bilfind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This class provides a place for user to Sign Up for the application
 * @author Masna Ahmed
 * @version 12/05/2017
 */

public class SignUp extends AppCompatActivity {

    @Override
    /**
     * This methods sets up the main page of the application on start up
     * @param savedInstanceState - the bundle that holds the prior information (if any) of the application otherwise being null
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                 //make toggle visible
    }

    /**
     * This method determines what to do if back toggle is pressed
     * @param item - referring to the toggle item
     * @return boolean value after performing tasks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            this.finish();                                                                          //finish after pressing toggle
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
        }

    /**
     * This method saves data entered
     * @param view
     * @throws FileNotFoundException
     */
    public void saveData(View view) throws FileNotFoundException {
        EditText name_tf = (EditText) findViewById(R.id.editText3);
        EditText bilkentID_tf = (EditText) findViewById(R.id.editText4);
        EditText password_tf= (EditText) findViewById(R.id.editText5);
        String name = name_tf.getText().toString();                                                //find name text field and get is data
        String bilkentID = bilkentID_tf.getText().toString();                                      //find bilID text field and get is data
        String password = password_tf.getText().toString();                                        //find password text field and get is data

        if (!name.equals("") && !password.equals("") && !bilkentID.equals(""))
        {
            File file = new File(this.getFilesDir(), "userFile.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));                    //create new appendable file

            pw.append(password + "," + bilkentID + "," + name + "," + ((CheckBox) findViewById(R.id.checkBox2)).isChecked() + "\r\n"); //add data as comma separated list and whether the acc is remembered or not
            pw.close();                                                                            //close file
            Toast.makeText(this, "Ready to go!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SignUp.this, LoginScreen.class));                             //now login
            this.finish();
        }
        else
            Toast.makeText(this, "Oops! Finish Sign Up first.", Toast.LENGTH_LONG).show();         //invalid data then complete
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
