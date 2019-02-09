package br.com.mapfood.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String codigoRestaurante;
    private String restaurant;
    private String addressCity;
    private String longitude;
    private String latitude;
    private String dishDescription;

    public Estabelecimento(Long id) {
        this.id = id;
    }
}


