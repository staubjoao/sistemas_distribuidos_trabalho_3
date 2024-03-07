package br.uem.geolocalizacao.rabbit;

public class GeolocalizacaoCreateEvent {
    private String id;
    private String lat;
    private String lon;

    public GeolocalizacaoCreateEvent() {
    }

    public GeolocalizacaoCreateEvent(String id, String lat, String lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "GeolocalizacaoCreateEvent{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
