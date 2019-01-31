package br.com.mapfood.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "motoboy")
public class Motoboy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String longitude;

    private String latitude;

    public Motoboy() {
    }

//    public Motoboy(Long id, String longitude, String latitude) {
//        this.id = id;
//        this.Longitude = longitude;
//        this.Latitude = latitude;
//    }

    public Motoboy(Map<String, String> row) {
        this.id = row.get("id");
        this.longitude = row.get("Longitude");
        this.latitude = row.get("Latitude");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Motoboy { "
                + " id: " + id
                + ", longitude: '" + longitude + '\''
                + ", latitude: '" + latitude + '\''
                + " }";
    }
}
