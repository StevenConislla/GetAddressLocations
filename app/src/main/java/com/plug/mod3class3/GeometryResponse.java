package com.plug.mod3class3;

/**
 * Created by OSKAR on 04/08/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class GeometryResponse {
    private BoundResponse bounds;
    private CoordenadaResponse location;
    private String location_type;
    private BoundResponse viewport;

    public BoundResponse getBounds() {
        return bounds;
    }

    public void setBounds(BoundResponse bounds) {
        this.bounds = bounds;
    }

    public CoordenadaResponse getLocation() {
        return location;
    }

    public void setLocation(CoordenadaResponse location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public BoundResponse getViewport() {
        return viewport;
    }

    public void setViewport(BoundResponse viewport) {
        this.viewport = viewport;
    }
}
