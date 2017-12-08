package com.plug.mod3class3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PruebaActivity extends AppCompatActivity {

    @BindView(R.id.svBusqueda)
    SearchView svBusqueda;
    @BindView(R.id.lvLugares)
    ListView lvLugares;
    ArrayList<String> Lista;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        ButterKnife.bind(this);

        Lista=new ArrayList<String>();
        Lista.add("Hola1");
        Lista.add("Hello");
        Lista.add("Hallo");
        Lista.add("Pelo");

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Lista);
        lvLugares.setAdapter(adapter);
        svBusqueda.setIconifiedByDefault(true);
        svBusqueda.setFocusable(true);
        svBusqueda.setIconified(false);
        svBusqueda.requestFocusFromTouch();

        svBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
