package br.com.mapfood.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "cliente")
public class Cliente  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String longitude;

    private String latitude;

    public Cliente() {
    }

    public Cliente (Map<String, String> row){
        this.id = row.get("id");
        this.longitude = row.get("Longitude");
        this.latitude = row.get("Latitude");

    }

//    public Cliente(Long id, String longitude, String latitude) {
//        this.id = id;
//        this.Longitude = longitude;
//        this.Latitude = latitude;
//    }


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Cliente { "
                + " id: '" + id + '\''
                + ", longitude: '" + longitude + '\''
                + ", latitude: '" + latitude + '\''
                + " }";
    }
}
