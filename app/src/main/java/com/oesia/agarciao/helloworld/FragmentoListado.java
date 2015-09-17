package com.oesia.agarciao.helloworld;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Alberto on 16/09/2015.
 */
public class FragmentoListado extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        ListView lista = (ListView) getActivity().findViewById(R.id.listView);
        lista.setOnItemClickListener(listener);
    }
}
