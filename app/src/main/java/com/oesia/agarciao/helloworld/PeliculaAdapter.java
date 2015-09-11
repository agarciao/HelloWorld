package com.oesia.agarciao.helloworld;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Alberto on 09/09/2015.
 */
public class PeliculaAdapter extends BaseAdapter {

    private List<Pelicula> lista;
    private int layout;
    private Activity activity;


    public PeliculaAdapter(List<Pelicula> lista, int layout, Activity activity) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = this.activity.getLayoutInflater().inflate(this.layout, null);
        }

        VistaViewHelper helper = (VistaViewHelper) convertView.getTag();

        if (helper == null) {
            helper = new VistaViewHelper();
            helper.tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
            helper.tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
            helper.tvAnho = (TextView) convertView.findViewById(R.id.tvAnho);
        }

        helper.tvTitulo.setText(this.lista.get(position).getTitulo());
        helper.tvDirector.setText(this.lista.get(position).getDirector());
        helper.tvAnho.setText(this.lista.get(position).getAnho());

        convertView.setTag(helper);
        return convertView;
    }

    public void borrarItem(int position) {
        this.lista.remove(position);
        notifyDataSetChanged();
    }

    public void crearItem(int position) {
        this.lista.add(position, new Pelicula("PELICULA_NUEVA", "DIRECTOR", "AÑO_NUEVO"));
        notifyDataSetChanged();
    }
}

class VistaViewHelper {
    public TextView tvTitulo;
    public TextView tvDirector;
    public TextView tvAnho;
}

