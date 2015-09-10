package com.oesia.agarciao.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DestinoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);
//        String[] datos = {"PRIMERO", "SEGUNDO", "TERCERO", "CUARTO"};
//        ListView listView = (ListView) findViewById(R.id.lvDestino);
//        ArrayAdapter<CharSequence> adapter =
//                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, datos);
//        listView.setAdapter(adapter);

        ListView listView = (ListView) findViewById(R.id.lvDestino);
        List<Pelicula> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Pelicula("Pelicula "+i, "Año "+i, "Director "+i));
        }
        PeliculaAdapter adapter = new PeliculaAdapter(list, R.layout.pelicula_list_item, this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_destino, menu);
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
