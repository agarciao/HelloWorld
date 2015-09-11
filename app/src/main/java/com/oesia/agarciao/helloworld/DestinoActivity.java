package com.oesia.agarciao.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DestinoActivity extends AppCompatActivity {

    private List<Pelicula> list;
    private PeliculaAdapter adapter;

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
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Pelicula("Pelicula " + i, "Año " + i, "Director " + i));
        }
        adapter = new PeliculaAdapter(list, R.layout.pelicula_list_item, this);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int id = v.getId();
        switch (id) {
            case R.id.lvDestino:
                AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                menu.setHeaderTitle(list.get(contextMenuInfo.position).getTitulo());

                getMenuInflater().inflate(R.menu.lv_context_menu, menu);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_borrar_ctx_mn_lst_destino:
                AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                adapter.borrarItem(contextMenuInfo.position);
                Toast.makeText(this, "Borrando en posición: " + contextMenuInfo.position, Toast.LENGTH_LONG).show();
                break;
            case R.id.action_crear_ctx_mn_lst_destino:
                AdapterView.AdapterContextMenuInfo contextMenuInfo2 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                adapter.crearItem(contextMenuInfo2.position);
                Toast.makeText(this, "Creando en posición: " + contextMenuInfo2.position, Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
