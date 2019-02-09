package br.com.mapfood.APIMaps;

import br.com.mapfood.domain.Rotas;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

@Service
public class FindRotasAndTimeAPI {

    private final static String API_KEY = "AIzaSyDHQu5G007T8-ANcovnWyGIZrQ6r9DCssk";

    public Rotas ApiBuscaRota(String coordenadasOrigem, String coordenadasDestino) {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        String[] origem = coordenadasOrigem.split(",");
        LatLng localicazaoOrigem = new LatLng(Double.valueOf(origem[0]), Double.valueOf(origem[1]));

        String[] destino = coordenadasDestino.split(",");
        LatLng localicazaoDestino = new LatLng(Double.valueOf(destino[0]), Double.valueOf(destino[1]));

        DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                .origins(localicazaoOrigem)
                .destinations(localicazaoDestino)
                .awaitIgnoreError();

        DistanceMatrixElement rotaMatrix = matrix.rows[0].elements[0];

        return new Rotas().builder()
                    .distancia(rotaMatrix.distance.humanReadable)
                    .distanciaMetros(rotaMatrix.distance.inMeters)
                    .tempoHorasMinutos(rotaMatrix.duration.humanReadable)
                    .tempoSegundos(rotaMatrix.duration.inSeconds)
                    .build();
    }
}
