package com.plug.mod3class3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.plug.mod3class3.rest.HelperWS;
import com.plug.mod3class3.rest.MetodoWS;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etDireccion)
    EditText etDireccion;
    @BindView(R.id.tvDireccion)
    TextView tvDireccion;
    @BindView(R.id.tvCoordenadas)
    TextView tvCoordenadas;
    @BindView(R.id.svBusquedaFragment)
    SearchView svBusquedaFragment;
    @BindView(R.id.lvLugares)
    ListView lvLugares;
    private MapFragment mapFragment;
    private GoogleMap googleMaps;
    ArrayList<String> ListaLugares;
    ArrayAdapter<String> adapter;
    private FragmentActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        //Async es un asincrono
        ListaLugares = new ArrayList<String>();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMaps = googleMap;

            }
        });

        svBusquedaFragment.setIconifiedByDefault(true);
        svBusquedaFragment.setFocusable(true);
        svBusquedaFragment.setIconified(false);
        svBusquedaFragment.requestFocusFromTouch();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ListaLugares);

        svBusquedaFragment.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ListaLugares.clear();
                lvLugares.setAdapter(adapter);
                final String direccion = s;
                MetodoWS metodoWS = HelperWS.obtenerConfiguracion().create(MetodoWS.class);
                Call<DireccionResponse> responseCall =
                        metodoWS.buscarDireccion(direccion, "AIzaSyBRhX1iwxzcBkq7Ggd9WaXHTOCeoaevdmw", "country:PE");

                //Le hace una consulta a internet
                responseCall.enqueue(new Callback<DireccionResponse>() {
                    @Override
                    public void onResponse(Call<DireccionResponse> call, Response<DireccionResponse> response) {
                        DireccionResponse direccionResponse = response.body();

                        if (direccionResponse != null) {
                                String dir = direccionResponse.getResults().get(0).getFormatted_address();
                                ListaLugares.add(dir);
                            lvLugares.setAdapter(adapter);
                            //get result del metodo creado, get cero es el primero de a lista, get formmatted adresss, lo pasamos  a addres;
                            /*String dir = direccionResponse.getResults().get(0).getFormatted_address();
                            tvDireccion.setText(dir);
                            CoordenadaResponse coor = direccionResponse.getResults().get(0).getGeometry().getLocation();
                            tvCoordenadas.setText(coor.getLat() + " - " + coor.getLng());
                            googleMaps.addMarker(new MarkerOptions()
                                    .position(new LatLng(coor.getLat(), coor.getLng()))
                                    .title("Titulo")
                                    .snippet("Descripcion"));
                            googleMaps.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coor.getLat(), coor.getLng())));*/
                        }
                    }

                    @Override
                    public void onFailure(Call<DireccionResponse> call, Throwable t) {

                    }



                //adapter.getFilter().filter(s);

                });

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lvLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String LugarEscogido=adapterView.getItemAtPosition(i).toString();
                etDireccion.setText(LugarEscogido);
            }
        });
    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {
        final String direccion = etDireccion.getText().toString();
        MetodoWS metodoWS = HelperWS.obtenerConfiguracion().create(MetodoWS.class);

        //Call<DireccionResponse> responseCall =
         //       metodoWS.buscarDireccion(direccion, "AIzaSyBRhX1iwxzcBkq7Ggd9WaXHTOCeoaevdmw", "country:PE");
        Call<DireccionResponse> responseCall =
                metodoWS.buscarDireccion(direccion, "AIzaSyBRhX1iwxzcBkq7Ggd9WaXHTOCeoaevdmw", "country:PE");


        //Le hace una consulta a internet
        responseCall.enqueue(new Callback<DireccionResponse>() {
            @Override
            public void onResponse(Call<DireccionResponse> call, Response<DireccionResponse> response) {
                DireccionResponse direccionResponse = response.body();
                if (direccionResponse != null) {
                    //get result del metodo creado, get cero es el primero de a lista, get formmatted adresss, lo pasamos  a addres;
                    String dir = direccionResponse.getResults().get(0).getFormatted_address();
                    ArrayList<ComponenteResponse> detalleResponses=direccionResponse.getResults().get(0).getAddress_components();
                    for (ComponenteResponse item:detalleResponses){
                        String[] a={"locality","political"};
                        if (item.getTypes().equals(a)){
                          tvCoordenadas.setText(item.getShort_name()+":"+item.getLong_name()+item.getTypes());
                        }
                        else{
                            Log.d("noe",item.getTypes().toString());
                        }

                    }
                    tvDireccion.setText(dir);
                    CoordenadaResponse coor = direccionResponse.getResults().get(0).getGeometry().getLocation();
                    tvCoordenadas.setText(coor.getLat() + " - " + coor.getLng());
                    googleMaps.addMarker(new MarkerOptions()
                            .position(new LatLng(coor.getLat(), coor.getLng()))
                            .title("Titulo")
                            .snippet("Descripcion"));
                    googleMaps.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coor.getLat(), coor.getLng())));
                }
            }

            @Override
            public void onFailure(Call<DireccionResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btnUbicacion)
    public void onViewClicked2() {

        etDireccion.setText(svBusquedaFragment.getQuery());
    }
}