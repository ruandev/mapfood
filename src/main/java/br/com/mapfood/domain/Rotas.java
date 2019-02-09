package br.com.mapfood.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rotas {

    private String distancia ;
    private Long distanciaMetros ;
    private String tempoHorasMinutos;
    private Long tempoSegundos;

}
