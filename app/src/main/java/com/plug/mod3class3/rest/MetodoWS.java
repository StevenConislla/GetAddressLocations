package com.plug.mod3class3.rest;

import com.plug.mod3class3.DireccionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by OSKAR on 04/08/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

//https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=YOUR_API_KEY
    //&components=country:PE
    //&latlng=12.4266,54.34243
//en el query "address" lo saco del link, el "key" tambien me lo pide, lo se por el link
public interface MetodoWS  {

    @GET("geocode/json")
    Call<DireccionResponse> buscarDireccion(@Query("address")String address,
                                            @Query("key") String key,
                                            @Query("components") String componente);
}
