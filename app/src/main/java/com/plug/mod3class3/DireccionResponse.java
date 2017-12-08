package com.plug.mod3class3;

import java.util.ArrayList;

/**
 * Created by OSKAR on 04/08/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class DireccionResponse {
    private String status;
    private ArrayList<DetalleResponse> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<DetalleResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<DetalleResponse> results) {
        this.results = results;
    }
}
