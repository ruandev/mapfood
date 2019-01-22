package br.com.mapfood.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "estabelecimento")
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String  codigoRestaurante;
    private String restaurant;
    private String addressCity;
    private String longitude;
    private String latitude;
    private String dishDescription;



    public Estabelecimento() {
    }

    public Estabelecimento(Long id, String codigoRestaurante, String restaurant, String addressCity, String longitude, String latitude, String dishDescription) {
        this.id = id;
        this.codigoRestaurante = codigoRestaurante;
        this.restaurant = restaurant;
        this.addressCity = addressCity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dishDescription = dishDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(String codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
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

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }
}

