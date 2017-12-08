package com.plug.mod3class3;

import java.util.ArrayList;

/**
 * Created by OSKAR on 04/08/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class DetalleResponse {
    private ArrayList<ComponenteResponse> address_components;
    private String formatted_address;
    private GeometryResponse geometry;
    private String place_id;
    private String[] types;

    public ArrayList<ComponenteResponse> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(ArrayList<ComponenteResponse> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public GeometryResponse getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryResponse geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
