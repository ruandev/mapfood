package br.com.mapfood.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity(name = "estabelecimento")
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String codigoRestaurante;
    private String restaurant;
    private String addressCity;
    private String longitude;
    private String latitude;
    private String dishDescription;
}


