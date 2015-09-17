package com.oesia.agarciao.helloworld;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Fragmento1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private FragmentoListado fragmentoListado;
    private FragmentoDetalle fragmentoDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento1);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentoListado = (FragmentoListado)fragmentManager.findFragmentById(R.id.fragment_listado);
        fragmentoDetalle = (FragmentoDetalle)fragmentManager.findFragmentById(R.id.fragment_detalle);
        fragmentoListado.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragmento1, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (fragmentoDetalle == null) {
            Toast.makeText(this, "ESTOY EN EL MOVIL", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ESTOY EN LA TABLET", Toast.LENGTH_SHORT).show();
        }
    }
}
