package com.example.kosvinera9166.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    //editname can be extendended
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        editName=(EditText) findViewById(R.id.editText_name);
    }




    public void addData (View v){

        boolean isInserted = myDb.insertData(editName.getText().toString());

        if (isInserted==true){
            Log.d("My Contact","Data insertion successful");
            //text telling user data inserteded
        }
        else{
            Log.d("My Contact","Data insertion unsuccessful");
            //toast nessage indicating data wrongly imputed
        }
    }
    public void viewData(View v){
        Cursor res =myDb.getAllData();
        if (res.getCount()==0){
            showMessage("Error","No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        //set up while loop with Cursor move to next method por favor
        //append each column to the buffer
        //use getString method
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
