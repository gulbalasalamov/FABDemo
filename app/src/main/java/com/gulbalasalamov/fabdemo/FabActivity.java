package com.gulbalasalamov.fabdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FabActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
                Snackbar.make(view, "Item listeye eklendi", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", undoOnclickListener).show();
            }
        });
    }

    View.OnClickListener undoOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listItems.remove(listItems.size() - 1);
            adapter.notifyDataSetChanged();
            Snackbar.make(view, "Item listeden çıkarıldı.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    };

    private void addListItem() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.ENGLISH);
        listItems.add(dateFormat.format(new Date()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab, menu);
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
