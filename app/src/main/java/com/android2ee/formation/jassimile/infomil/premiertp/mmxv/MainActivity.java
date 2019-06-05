package com.android2ee.formation.jassimile.infomil.premiertp.mmxv;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    /**
     * Constant for the savedInstance and RestoreInstance methods
     */
    public static final String MESSAGE = "MESSAGE";
    /**
     * Constant for the savedInstance and RestoreInstance methods
     */
    public static final String RESULT = "RESULT";
    /**
     * The button Add
     */
    Button btnAdd;
    /**
     * The EditText to write the message
     */
    EditText edtMessage;
    /**
     *The ListView
     */
    ListView lsvResult;
    /**
     * The ArrayAdapter of the listView
     * Manage the list of items and the view to display them
     */
    ArrayAdapter<String> arrayAdpater;
    /**
     * The list opf items to display
     */
    ArrayList items;


    /**
     * ********************************************************
     * Managing Life cycle
     * ********************************************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Instantiate the view
        setContentView(R.layout.activity_main);
        //find my graphical elements
        btnAdd= (Button) findViewById(R.id.btnAdd);
        edtMessage= (EditText) findViewById(R.id.edtMessage);
        //instanciate the listview
        items=new ArrayList<String>();
        arrayAdpater=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        lsvResult= (ListView) findViewById(R.id.lsvResult);
        lsvResult.setAdapter(arrayAdpater);
        //add listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddClicked();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE, edtMessage.getText().toString());
        outState.putStringArrayList(RESULT, items);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edtMessage.setText(savedInstanceState.getString(MESSAGE));
        //ca rate :
        items=savedInstanceState.getStringArrayList(RESULT);
        items.clear();
        for(String item:savedInstanceState.getStringArrayList(RESULT)){
            items.add(item);
        }
        arrayAdpater.notifyDataSetChanged();
    }
/***********************************************************
     *  Business methods
     **********************************************************/

    /**
     * Called when the btnAdd is clicked
     */
    public void btnAddClicked(){
        String message=edtMessage.getText().toString();
        //first choice
//        items.add(message);
//        arrayAdpater.notifyDataSetChanged();
        //second choice
        arrayAdpater.add(message);
        edtMessage.setText("");
    }

    /**
     * ********************************************************
     * Managing menu
     * ********************************************************
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
