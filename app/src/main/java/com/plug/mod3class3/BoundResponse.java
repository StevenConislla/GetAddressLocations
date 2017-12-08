package com.plug.mod3class3;

/**
 * Created by OSKAR on 04/08/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class BoundResponse {
    private CoordenadaResponse northeast;
    private CoordenadaResponse sourthwest;

    public CoordenadaResponse getNortheast() {
        return northeast;
    }

    public void setNortheast(CoordenadaResponse northeast) {
        this.northeast = northeast;
    }

    public CoordenadaResponse getSourthwest() {
        return sourthwest;
    }

    public void setSourthwest(CoordenadaResponse sourthwest) {
        this.sourthwest = sourthwest;
    }
}
