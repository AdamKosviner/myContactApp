package com.example.kosvinera9166.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editAge;
    EditText searchName;
    //editname can be extendended
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//no
        myDb = new DataBaseHelper(this);
        editName=(EditText) findViewById(R.id.editText_name);
        editAddress=(EditText) findViewById(R.id.editText_address);
        editAge=(EditText) findViewById(R.id.editText_age);
        searchName=(EditText) findViewById(R.id.editText_search);
    }




    public void addData (View v){

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editAge.getText().toString());
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, "Data insertion worked!", duration);
        Toast toastUnsuccessful = Toast.makeText(this, "Data insertion failed!", duration);
        if (isInserted  == true){
            Log.d("My Contact", "Data insertion successful");
            //text telling user data inserteded
            toastSuccessful.show();
        }
        else{
            Log.d("My Contact", "Data insertion unsuccessful");
            //toast nessage indicating data wrongly imputed
            toastUnsuccessful.show();
        }
    }
    public void viewData(View v){
        Cursor res =myDb.getAllData();
        if (res.getCount() == 0){
            showMessage("Error", "No data found in database");
            Log.d("MyContact", "No Data found in database");

            int duration =Toast.LENGTH_SHORT;
            Toast noData = Toast.makeText(this, "No Data Found In Database", duration);
            noData.show();


            return;
        }
        StringBuffer buffer = new StringBuffer();
        //set up while loop with Cursor move to next method por favor
        //append each column to the buffer
        //use getString method
        while(res.moveToNext())
        {
            buffer.append("ID:" + res.getString(0));
            buffer.append("NAME:" + res.getString(1));
            buffer.append("    ADDRESS:" + res.getString(2));
            buffer.append("    AGE:" + res.getString(3));
            buffer.append("\n\n");




        }
        showMessage("Data", buffer.toString());
        Log.d("MyContact", buffer.toString());
    }

    private String returnSearchContact() {
    String search = searchName.getText().toString();
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            showMessage("Error", "NO DATA");
            Log.d("MyContact", "NO DATA");

            int duration =Toast.LENGTH_SHORT;
            Toast noData = Toast.makeText(this, "No Data Found In Database", duration);
            noData.show();
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            if(res.getString(1).toLowerCase().equals(search.toLowerCase())){
                return("NAME:" + res.getString(1)
                 + "    ADDRESS:" + res.getString(2)
                         + "    AGE:" + res.getString(3)


                );

            }

        }


    return "Not Found";
    }

    public void searchContact(View V){
        showMessage("RESULT", returnSearchContact());
        Log.d("RESULT", returnSearchContact());

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
